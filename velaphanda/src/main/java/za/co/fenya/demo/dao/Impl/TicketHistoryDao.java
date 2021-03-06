package za.co.fenya.demo.dao.Impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.bean.DeviceBean;
import za.co.fenya.demo.dao.DeviceDaoInt;
import za.co.fenya.demo.dao.TicketHistoryDaoInt;
import za.co.fenya.demo.model.Device;
import za.co.fenya.demo.model.TicketHistory;
import za.co.fenya.demo.model.Tickets;
import za.co.fenya.demo.reports.initializer.TicketReportBean;


@Repository("ticketHistoryDAO")
@Transactional(propagation=Propagation.REQUIRED)
public class TicketHistoryDao implements TicketHistoryDaoInt{

	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DeviceDaoInt deviceDaoInt;
	private TicketHistory ticketHistory = null;
	private DeviceBean deviceBean = null;
	private DeviceContactPersonDao deviceContactPerson;
	DateFormat dateFormat = null;
	Date date = null;
	
	
	private String newTicketNum = "VTC000";
	List<TicketHistory> ticketHistoryList = null;
	ArrayList<?> aList = null;
	ArrayList list = null;
	private Device device = null;
	private Tickets ticket = null;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void insertTicketHistory(Tickets ticket) {
		ticketHistory = new TicketHistory();
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		date = new Date();
		try{
			
			  ticketHistory.setTicketNo(ticket.getRecordID());
			  ticketHistory.setComment(ticket.getComments());
			  ticketHistory.setEscalatedDate(dateFormat.format(date));
			  ticketHistory.setEmployee(ticket.getEmployee());
			  ticketHistory.setEscalatedReason(ticket.getEscalateReason());
			  ticketHistory.setSolution(ticket.getSolution());
			  ticketHistory.setActionTaken(ticket.getActionTaken());
			  ticketHistory.setStatus(ticket.getStatus());
			  
			  ticketHistory.setMonoReading(ticket.getDevice().getMonoReading());
			  ticketHistory.setColourReading(ticket.getDevice().getColourReading());
			  sessionFactory.getCurrentSession().save(ticketHistory);
			  
			  
			  if(ticketHistory.getStatus().equalsIgnoreCase("SLA Bridged")){
				  sessionFactory.getCurrentSession().beginTransaction().commit();
			  }
			
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	
	@Override
	public Tickets getLoggedTicketsByTicketNumber(Long ticketNumber) {

		return (Tickets) sessionFactory.getCurrentSession().get(Tickets.class,
				ticketNumber);
	}

	@Override
	public List<TicketHistory> getHistoryByTicketNumber(Long ticketNumber) {
		
		List<TicketHistory> newList = null;
		try{
			
			List<TicketHistory> list = getAllTicketHistoryByTicketNumber();
			newList = new ArrayList<TicketHistory>();
			for(TicketHistory ticketHistory:list){
				if(ticketHistory.getTicketNo().equals(ticketNumber)){
					newList.add(ticketHistory);
				}
			}
			
		}catch(Exception e){
			e.getMessage();
		}
		return newList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TicketHistory> getAllTicketHistoryByTicketNumber() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				TicketHistory.class);
		return (List<TicketHistory>) criteria.list();	
	}

	@Override
	public List<TicketHistory>  getAllTicketHistoryByTicketNumber(Long recordID) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				TicketHistory.class);
		return (List<TicketHistory>) criteria.list();
	}	
	
	@Override
	public JRDataSource getDeviceHistoryDataSource(Long recordID) {
		JRDataSource ds = null;
		
		List<TicketReportBean> result = new ArrayList<TicketReportBean>();
		try{
			
			List<TicketHistory> ticketsHistory = getHistoryByTicketNumber(recordID);			
			ticket = getLoggedTicketsByTicketNumber(recordID);
			
						
			for(TicketHistory tickHistory:ticketsHistory){
				
				TicketReportBean ticketBean = new TicketReportBean();
	        	
	        	ticketBean.setCustomerName(ticket.getDevice().getCustomerDevice().getCustomerName());
				ticketBean.setSerialNumber(ticket.getDevice().getSerialNumber());
				ticketBean.setModelNumber(ticket.getDevice().getModelNumber());
				
				ticketBean.setDeviceContactPersonFirstLastName(ticket.getFirstName()+ " "+ ticket.getLastName());
				ticketBean.setDeviceContactPersonCellphone(ticket.getContactCellNumber());
				ticketBean.setDeviceContactPersonTellphone(ticket.getContactTelephoneNumber());
				ticketBean.setDeviceContactPersonEmail(ticket.getContactEmail());				
            	ticketBean.setTicketNo("VTC000"+tickHistory.getTicketNo());
            	ticketBean.setDate(tickHistory.getEscalatedDate());           	
                ticketBean.setStatus(tickHistory.getStatus());
                if(tickHistory.getStatus().equalsIgnoreCase("Open")){
	  				ticketBean.setActionTaken("Log Ticket");
	  		    }else if(tickHistory.getStatus().equalsIgnoreCase("Taken")){
	  				ticketBean.setActionTaken("Ticket Taken");
	  			}else if(tickHistory.getStatus().equalsIgnoreCase("Acknowledged")){
	  				ticketBean.setActionTaken("Ticket Acknowledged");
	  			}else if(tickHistory.getStatus().equalsIgnoreCase("Resolved")){
	  				ticketBean.setActionTaken(tickHistory.getActionTaken());
	  			}else if(tickHistory.getStatus().equalsIgnoreCase("Closed")){
	  				ticketBean.setActionTaken(tickHistory.getActionTaken());
	  			}                               
            	ticketBean.setAssignedTo(tickHistory.getEmployee().getFirstName() +" "+tickHistory.getEmployee().getLastName());
            	ticketBean.setProblemDescription(ticket.getDescription());
            	 if(tickHistory.getStatus().equalsIgnoreCase("Open")){
 	  				ticketBean.setComment("Log Ticket");
 	  		    }else{
 	  		    	ticketBean.setComment(tickHistory.getComment());
 	  		    }
            	ticketBean.setMonoReading(tickHistory.getColourReading());
            	ticketBean.setColourReading(tickHistory.getMonoReading());
            	
    			result.add(ticketBean);			
    			ds = new JRBeanCollectionDataSource(result);
			
			}
			
	}catch(Exception e){
		e.getMessage();
	}
	return ds;
	}
}

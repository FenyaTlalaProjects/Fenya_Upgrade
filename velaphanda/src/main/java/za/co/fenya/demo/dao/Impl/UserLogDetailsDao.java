package za.co.fenya.demo.dao.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;






import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.bean.InvoiceBean;
import za.co.fenya.demo.dao.CustomerDeviceHistoryDaoInt;
import za.co.fenya.demo.dao.InvoiceDaoInt;
import za.co.fenya.demo.dao.UserLogDetailsDaoInt;
import za.co.fenya.demo.model.Employee;
import za.co.fenya.demo.model.InvoiceHeader;
import za.co.fenya.demo.model.UserLogDetails;


@Repository("userLogDetailsDao")
@Transactional(propagation = Propagation.REQUIRED)
public class UserLogDetailsDao implements UserLogDetailsDaoInt {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private HttpSession session;
	private Date currentDate,logoutDateTime;
	private Employee employee;
	private UserLogDetails userLogDetails;
	@Override
	public void saveUserLogDetails(UserLogDetails details) {
		employee = (Employee) session.getAttribute("loggedInUser");
		
		String sessionID = session.getId();
		currentDate = new  Date();
		
		

		
		details = new UserLogDetails();
		try{
			details.setSessionId(sessionID);
			details.setLoginDateTime(currentDate);
			details.setEmployee(employee);
			sessionFactory.getCurrentSession().save(details);
			
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserLogDetails> getLogoutDateTime() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserLogDetails.class);
		return (List<UserLogDetails>)criteria.list();
	}
	@Override
	public List<UserLogDetails> getUserLogDetails() {
		return null;
	}
	/*@Transactional
	@Scheduled(fixedRate=5000 )*/
	public void lougoutTimeStamp(){
		
		currentDate = new  Date();
		
		//employee = (Employee) session.getAttribute("loggedInUser");
		//System.out.print(sessionID);
		
				try{
					String sessionID = getSessionID();
					
					List<UserLogDetails> getUsers = getLogoutDateTime();
					for(UserLogDetails users:getUsers){
						if(users.getSessionId().equalsIgnoreCase(sessionID) && users.getLogoutDateTime()==null){
							
							users.setLogoutDateTime(currentDate);
							sessionFactory.getCurrentSession().update(users);
						}
					}

		}catch(Exception e){
			e.getMessage();
		}
	}
	private String getSessionID(){
		String sessionID = null;
		try{
			sessionID = (String) session.getAttribute("sessionID");
			
		}catch(Exception e){
			e.getMessage();
		}
		return sessionID;
	}


	@Override
	public void updateTimeout(String sessionID) {
		userLogDetails = new UserLogDetails();
		currentDate = new  Date();
		try{
			
			userLogDetails = getUserLogDetails(sessionID);
			userLogDetails.setLogoutDateTime(currentDate);
			sessionFactory.getCurrentSession().update(userLogDetails);
		}catch(Exception e){
			e.getMessage();
		}
	}


	@Override
	public UserLogDetails getUserLogDetails(String sessionID) {
		
		return (UserLogDetails) sessionFactory.getCurrentSession().get(UserLogDetails.class, sessionID);
	}


	@Override
	public List<UserLogDetails> userActivities(String email) {
		List<UserLogDetails> users  = new ArrayList<UserLogDetails>();
		List<UserLogDetails> listUsers  = new ArrayList<UserLogDetails>();
		try{
			users = getLogoutDateTime();
			for(UserLogDetails logDetails:users){
				if(logDetails.getEmployee().getEmail().equalsIgnoreCase(email)){
					listUsers.add(logDetails);
				}
			}
		}catch(Exception e){
			e.getMessage();
		}
		return listUsers;
	}


	@Override
	public void getLastUserLogDetails(String userEmail) {
		try{
			
			List<UserLogDetails> getUsers = getLogoutDateTime();
			for(UserLogDetails users:getUsers){
				if(users.getEmployee().getEmail().equalsIgnoreCase(userEmail) && users.getLogoutDateTime()==null){
					 
					Calendar c = Calendar.getInstance();
				    c.setTime(users.getLoginDateTime());
				    c.add(Calendar.HOUR, 1);
					Date incrementedDate = c.getTime();
					users.setLogoutDateTime(incrementedDate);
					sessionFactory.getCurrentSession().update(users);
				}
			}
		}catch(Exception e){
			
		}
	}

}

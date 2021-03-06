package za.co.fenya.demo.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.dao.CustomerContactDetailsDaoInt;
import za.co.fenya.demo.dao.EmployeeDaoInt;
import za.co.fenya.demo.dao.OrderDetailsDaoInt;
import za.co.fenya.demo.dao.OrdersDaoInt;
import za.co.fenya.demo.model.CustomerContactDetails;
import za.co.fenya.demo.model.Employee;
import za.co.fenya.demo.model.OrderDetails;
import za.co.fenya.demo.model.OrderHeader;
import za.co.fenya.demo.reports.initializer.OrderReportBean;


@Repository("orderDetailsDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class OrderDetailsDao implements OrderDetailsDaoInt {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private OrdersDaoInt orderDaoInt;
	@Autowired
	private EmployeeDaoInt employeeDaoInt;
	@Autowired
	private CustomerContactDetailsDaoInt contactDetailsDaoInt;

	private String retMessage = null;

	private List<OrderDetails> orders = null;
	private List<OrderDetails> retOrder = null;
	private CustomerContactDetails customerContacts;

	@Override
	public String saveOrderDetails(List<OrderDetails> orderDetails) {
		try {

			for (OrderDetails orders : orderDetails) {
				sessionFactory.getCurrentSession().save(orders);
				retMessage = "OK";
			}
		} catch (Exception e) {
			retMessage = e.getMessage();
		}
		return retMessage;
	}

	@Override
	public List<OrderDetails> getOrderDetailsByOrderNum(Long recordID) {

		ArrayList<OrderDetails> pendingList = new ArrayList<OrderDetails>();
		try {
			orders = getAllOrderDetails();
			for (OrderDetails order : orders) {
				if (order.getOrderHeader().getRecordID().equals(recordID)) {
					pendingList.add(order);
				}
			}
		} catch (Exception e) {

		}
		return pendingList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetails> getAllOrderDetails() {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				OrderDetails.class);
		return (List<OrderDetails>) criteria.list();
	}

	@Override
	public List<OrderDetails> getOrderDetailsByTechnician(String email) {
		ArrayList<OrderDetails> pendingList = new ArrayList<OrderDetails>();
		try {
			orders = getAllOrderDetails();

			for (OrderDetails order : orders) {
				if (order.getOrderHeader().getEmployee().getEmail()
						.equalsIgnoreCase(email)) {
					pendingList.add(order);
				}
			}
		} catch (Exception e) {

		}
		return pendingList;
	}

	@Override
	public List<OrderDetails> getAllAvailableOrderDetails(String technician) {
		ArrayList<OrderDetails> availableOrders = new ArrayList<OrderDetails>();
		try {
			orders = getAllOrderDetails();
			for (OrderDetails availableOrds : orders) {
				if (availableOrds.getOrderHeader().getStockType()
						.equalsIgnoreCase("Boot")
						&& availableOrds.getOrderHeader().getStatus()
								.equalsIgnoreCase("Received")
						&& availableOrds.getTechnician().equalsIgnoreCase(
								technician)) {
					availableOrders.add(availableOrds);
				}
			}

		} catch (Exception e) {

		}

		return availableOrders;
	}

	public String incrementStockAvailability(List<OrderDetails> availableStock) {
		OrderDetails orderDetails = null;
		int tempQuantity = 0;
		try {
			for (OrderDetails details : availableStock) {
				orderDetails = getOrderDetails(details.getOrderDertailNumber());

				if (details.getOrderDertailNumber() == orderDetails
						.getOrderDertailNumber()) {
					tempQuantity = orderDetails.getQuantity()
							+ details.getQuantity();
					orderDetails.setQuantity(tempQuantity);
					sessionFactory.getCurrentSession().update(orderDetails);
					retMessage = "OK";
				}
			}
		} catch (Exception e) {
		}
		return retMessage;
	}

	private OrderDetails getOrderDetails(int orderDertailNumber) {
		return (OrderDetails) sessionFactory.getCurrentSession().get(
				OrderDetails.class, orderDertailNumber);
	}

	@Override
	public List<OrderDetails> getOrderDetailsByOrderNum(String key,
			Long recordID) {

		ArrayList<OrderDetails> pendingList = new ArrayList<OrderDetails>();
		try {
			orders = getAllOrderDetails();
			for (OrderDetails order : orders) {
				if (order.getOrderHeader().getRecordID().equals(recordID)) {
					pendingList.add(order);
				}
			}
		} catch (Exception e) {
		}
		return pendingList;
	}

	@Override
	public List<OrderDetails> getAllBootStockOrders() {
		orders = getAllOrderDetails();
		retOrder = new ArrayList<OrderDetails>();
		try {
			for (OrderDetails order : orders) {
				if (order.getOrderHeader().getStockType()
						.equalsIgnoreCase("Boot")) {
					retOrder.add(order);
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return retOrder;
	}

	@Override
	public List<OrderDetails> getAllSiteStockOrders() {
		orders = getAllOrderDetails();
		retOrder = new ArrayList<OrderDetails>();
		try {
			for (OrderDetails order : orders) {
				if (order.getOrderHeader().getStockType()
						.equalsIgnoreCase("Site")) {
					retOrder.add(order);
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return retOrder;
	}

	@Override
	public List<OrderDetails> getAllAvailableOrderDetailsForCustomer(
			String customerName) {
		ArrayList<OrderDetails> availableOrders = new ArrayList<OrderDetails>();
		try {
			orders = getAllOrderDetails();
			for (OrderDetails availableOrds : orders) {
				
				if (availableOrds.getOrderHeader().getStockType()
						.equalsIgnoreCase("Site")
						&& availableOrds.getOrderHeader().getStatus()
								.equalsIgnoreCase("Received")
						&& availableOrds.getOrderHeader().getCustomer()
								.getCustomerName()
								.equalsIgnoreCase(customerName)) {
					availableOrders.add(availableOrds);
				}
			}

		} catch (Exception e) {
			e.getMessage();

		}

		return availableOrders;
	}

	@Override
	public JRDataSource getOrderDetailsDataSource(Long recordID) {
		JRDataSource ds = null;
		try{
			List<OrderDetails> orderDetails = getOrderDetailsByOrderNum(recordID);
			OrderHeader ordHeader = null;
            List<OrderReportBean> orderResults = new ArrayList<OrderReportBean>();
            if(orderDetails.get(0).getOrderHeader().getStockType().equalsIgnoreCase("Site")){
            	ordHeader = orderDaoInt.getOrder(recordID);
            	customerContacts = contactDetailsDaoInt.getContactPerson(ordHeader.getCustomer().getCustomerName());
            }
            
            Employee emp = employeeDaoInt.getEmployeeByEmpNum(orderDetails.get(0).getOrderHeader().getEmployee().getEmail());
			
			for(OrderDetails order:orderDetails){
				OrderReportBean bean = new OrderReportBean();
				
				bean.setItemDescription(order.getItemDescription());
				bean.setModel(order.getModel());
				bean.setPartNumber(order.getPartNumber());
				bean.setQuantity(order.getQuantity());
				
				bean.setDateDelivered("");
				bean.setCustomerOrderNum("ORD00"+order.getOrderHeader().getRecordID());
			
				if(ordHeader !=null){
					
					bean.setCustomerName(ordHeader.getCustomer().getCustomerName());
					bean.setAddress(ordHeader.getCustomer().getStreetNumber()+" "+ordHeader.getCustomer().getStreetName());
					bean.setProvince(ordHeader.getCustomer().getCity_town()+" ,"+ordHeader.getCustomer().getProvince());
					
					String NoteNumber = ordHeader.getCustomer().getCustomerName().substring(0,3)+"/"+ "ORD00"+ordHeader.getRecordID();
					bean.setDeliveryNoteNo(NoteNumber);
				
					
					bean.setTechName(customerContacts.getFirstName()+" "+customerContacts.getLastName());
					bean.setTechEmail(customerContacts.getContactEmail());
					bean.setTechCellNo(customerContacts.getContactTelephoneNumber());
				}
				else{
					
					bean.setTechName(emp.getFirstName()+" "+emp.getLastName());
					bean.setTechEmail(emp.getEmail());
					bean.setTechCellNo(emp.getCellNumber());
					bean.setCustomerName("");
					bean.setAddress("");
					bean.setProvince("");
					
					String NoteNumber = "ORD00"+order.getOrderHeader().getRecordID();
					bean.setDeliveryNoteNo(NoteNumber);
				}
				orderResults.add(bean);
				ds = new JRBeanCollectionDataSource(orderResults);
				
			}
			
		}catch(Exception e){
			e.getMessage();
		}
		return ds;
	}


}

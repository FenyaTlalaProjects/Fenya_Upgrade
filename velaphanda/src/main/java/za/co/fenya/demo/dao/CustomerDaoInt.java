package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.bean.CustomerBean;
import za.co.fenya.demo.model.Customer;
import za.co.fenya.demo.model.CustomerContactDetails;
import net.sf.jasperreports.engine.JRDataSource;


public interface CustomerDaoInt {
	
	Customer getClientByClientName(String clientName);
	List<Customer> getClientList();
	List<Customer> getClientList(String customerName);
	CustomerBean contactDetails(String customerName);
	String updateCustomer(CustomerBean customerBean);
	String saveCustomer(CustomerBean customerBean);
	JRDataSource getCustomerListDataSource();
	JRDataSource getCustomerDetailsDataSource(String customerName);	
	List<CustomerContactDetails> contacts();
	
	
	

}

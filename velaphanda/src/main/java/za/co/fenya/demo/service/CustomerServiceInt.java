package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.bean.CustomerBean;
import za.co.fenya.demo.model.Customer;
import net.sf.jasperreports.engine.JRDataSource;


public interface CustomerServiceInt {
	
	Customer getClientByClientName(String clientName);
	List<Customer> getClientList();
	List<Customer> getClientList(String customerName);
	String updateCustomer(CustomerBean customerBean);
	CustomerBean contactDetails(String customerName);
	String saveCustomer(CustomerBean customerBean);	
	JRDataSource getCustomerDetailsDataSource(String customerName);

}

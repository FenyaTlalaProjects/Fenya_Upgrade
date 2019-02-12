package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.bean.CustomerBean;
import za.co.fenya.demo.model.CustomerContactDetails;
import net.sf.jasperreports.engine.JRDataSource;


public interface CustomerContactDetailsServiceInt {
	CustomerBean contactDetails(String customerName);
	List<CustomerContactDetails> contacts(String customerName);
	List<CustomerContactDetails> contacts();
	CustomerContactDetails getContactPerson(String customerName);
	JRDataSource getCustomerContactDetailsDataSource(String customerName);

}

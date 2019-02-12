package za.co.fenya.demo.service.impl;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.bean.CustomerBean;
import za.co.fenya.demo.dao.CustomerContactDetailsDaoInt;
import za.co.fenya.demo.model.CustomerContactDetails;
import za.co.fenya.demo.service.CustomerContactDetailsServiceInt;


@Service("CustomerContactDetailsService")
public class CustomerContactDetailsService implements CustomerContactDetailsServiceInt{

	
	@Autowired
	private CustomerContactDetailsDaoInt contactDetailsDaoInt;
	
	@Override
	public CustomerBean contactDetails(String customerName) {	
		
		return contactDetailsDaoInt.contactDetails(customerName);
	}

	@Override
	public List<CustomerContactDetails> contacts(String customerName) {
		return null;
	}

	@Override
	public List<CustomerContactDetails> contacts() {
		return null;
	}

	@Override
	public CustomerContactDetails getContactPerson(String customerName) {
	
		return contactDetailsDaoInt.getContactPerson(customerName);
	}

	@Override
	public JRDataSource getCustomerContactDetailsDataSource(String customerName) {
		// TODO Auto-generated method stub
		return contactDetailsDaoInt.getCustomerContactDetailsDataSource(customerName);
	}

}

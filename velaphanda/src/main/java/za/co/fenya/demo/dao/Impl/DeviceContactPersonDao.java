package za.co.fenya.demo.dao.Impl;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.dao.DeviceContactPersonDaoInt;
import za.co.fenya.demo.model.DeviceContactPerson;



@Repository("deviceContactPersonDAO")
@Transactional(propagation=Propagation.REQUIRED)
public class DeviceContactPersonDao implements DeviceContactPersonDaoInt{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private String retMessage;

	@Override
	public String saveContactPerson(DeviceContactPerson contactPerson) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(contactPerson);
			retMessage = "OK";
			
		}catch(Exception e){
			retMessage = e.getMessage();
		}
		
		return retMessage;
	}


}

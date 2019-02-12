package za.co.fenya.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.bean.CustomerDeviceHistoryBean;
import za.co.fenya.demo.dao.CustomerDeviceHistoryDaoInt;
import za.co.fenya.demo.model.CustomerDeviceHistory;
import za.co.fenya.demo.service.CustomerDeviceHistoryServiceInt;



@Service("customerDeviceHistoryService")
@Transactional
public class CustomerDeviceHistoryService implements CustomerDeviceHistoryServiceInt {
	
	@Autowired
	private CustomerDeviceHistoryDaoInt historyDAO;
	private String retMessage = null;
		
	public String saveCustomerDeviceHistory(CustomerDeviceHistoryBean history) {
		retMessage =historyDAO.saveCustomerDeviceHistory(history);
		return retMessage;		
	}	
	@Override
	public List<CustomerDeviceHistory> getAllHistoryByCustomer() {		
		return historyDAO.getAllHistoryByCustomer();
	}		
	@Override
	public List<CustomerDeviceHistory> getHistoryByCustomer(String customerName){		
		return historyDAO.getHistoryByCustomer(customerName);
	}	
	@Override
	public List<CustomerDeviceHistory> getAllHistoryBySerialNumber() {		
		return historyDAO.getAllHistoryBySerialNumber();
	}	
	@Override
	public List<CustomerDeviceHistory> getHistoryBySerialNumber(String serialNumber){	
		return historyDAO.getHistoryBySerialNumber(serialNumber);
	}
			
}

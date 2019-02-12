package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.bean.CustomerDeviceHistoryBean;
import za.co.fenya.demo.model.CustomerDeviceHistory;


public interface CustomerDeviceHistoryDaoInt {

	String saveCustomerDeviceHistory(CustomerDeviceHistoryBean history);
	List<CustomerDeviceHistory> getAllHistoryByCustomer();
	List<CustomerDeviceHistory> getHistoryByCustomer(String customerName);
	List<CustomerDeviceHistory> getAllHistoryBySerialNumber();
	List<CustomerDeviceHistory> getHistoryBySerialNumber(String serialNumber);
	
}

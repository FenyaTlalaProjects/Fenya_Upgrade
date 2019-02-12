package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.bean.HistoryBean;
import za.co.fenya.demo.model.History;


public interface CopyOfHistoryMovementServiceInt {

	String saveHistory(HistoryBean history);
	List<History> getHistoryByCustomer(String customerName);
	List<History> getAllHistoryByCustomer();
	List<History> getHistoryBySerialNumber(String serialNumber);
	List<History> getAllHistoryBySerialNumber();
	List<History> getHistoryByPartNumber(String partNumber);
	List<History> getAllHistoryByPartNumber();
	List<History> getSiteStockHistoryByPartNumber(String partNumber);
	List<History> getAllSiteStockHistoryByPartNumber();
	List<History> getBootStockHistoryByPartNumber(String partNumber);
	List<History> getAllBootStockHistoryByPartNumber();
}

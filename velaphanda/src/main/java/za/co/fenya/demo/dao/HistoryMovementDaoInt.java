package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.bean.HistoryMovementBean;
import za.co.fenya.demo.model.HistoryMovement;


public interface HistoryMovementDaoInt {

	String saveHistoryMovement(HistoryMovementBean historyMovement);
	
	List<HistoryMovement> getHistoryMovementByPartNumber(String partNumber);
	List<HistoryMovement> getAllHistoryMovementByPartNumber();
	List<HistoryMovement> getSiteStockHistoryMovementByPartNumber(String partNumber);
	List<HistoryMovement> getAllSiteStockHistoryMovementByPartNumber();
	List<HistoryMovement> getBootStockHistoryMovementByPartNumber(String partNumber);
	List<HistoryMovement> getAllBootStockHistoryMovementByPartNumber();
}

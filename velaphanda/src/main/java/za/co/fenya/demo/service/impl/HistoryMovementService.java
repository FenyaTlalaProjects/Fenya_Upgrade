package za.co.fenya.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.bean.HistoryMovementBean;
import za.co.fenya.demo.dao.HistoryMovementDaoInt;
import za.co.fenya.demo.model.HistoryMovement;
import za.co.fenya.demo.service.HistoryMovementServiceInt;


@Service("historyMovementService")
@Transactional
public class HistoryMovementService implements HistoryMovementServiceInt {
	
	@Autowired
	private HistoryMovementDaoInt historyMovementDAO;
	private String retMessage = null;
		
	public String saveHistoryMovement(HistoryMovementBean historyMovement) {
		retMessage =historyMovementDAO.saveHistoryMovement(historyMovement);
		return retMessage;		
	}	
	
	@Override
	public List<HistoryMovement> getAllHistoryMovementByPartNumber() {		
		return historyMovementDAO.getAllHistoryMovementByPartNumber();
	}	
	@Override
	public List<HistoryMovement> getHistoryMovementByPartNumber(String partNumber){	
		return historyMovementDAO.getHistoryMovementByPartNumber(partNumber);
	}
	@Override
	public List<HistoryMovement> getSiteStockHistoryMovementByPartNumber(String partNumber) {
		return historyMovementDAO.getSiteStockHistoryMovementByPartNumber(partNumber);
	}
	@Override
	public List<HistoryMovement> getAllSiteStockHistoryMovementByPartNumber() {
		return historyMovementDAO.getAllSiteStockHistoryMovementByPartNumber();
	}
	@Override
	public List<HistoryMovement> getBootStockHistoryMovementByPartNumber(String partNumber) {
		return historyMovementDAO.getBootStockHistoryMovementByPartNumber(partNumber);
	}
	@Override
	public List<HistoryMovement> getAllBootStockHistoryMovementByPartNumber() {
		return historyMovementDAO.getAllBootStockHistoryMovementByPartNumber();
	}
		
}

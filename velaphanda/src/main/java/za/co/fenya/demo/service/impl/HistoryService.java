package za.co.fenya.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.bean.HistoryBean;
import za.co.fenya.demo.dao.HistoryDaoInt;
import za.co.fenya.demo.model.History;
import za.co.fenya.demo.service.HistoryServiceInt;



@Service("historyService")
@Transactional
public class HistoryService implements HistoryServiceInt {
	
	@Autowired
	private HistoryDaoInt historyDAO;
	private String retMessage = null;
		
	public String saveHistory(HistoryBean history) {
		retMessage =historyDAO.saveHistory(history);
		return retMessage;		
	}	
	
	@Override
	public List<History> getAllHistoryByPartNumber() {		
		return historyDAO.getAllHistoryByPartNumber();
	}	
	@Override
	public List<History> getHistoryByPartNumber(String partNumber){	
		return historyDAO.getHistoryByPartNumber(partNumber);
	}
	@Override
	public List<History> getSiteStockHistoryByPartNumber(String partNumber) {
		return historyDAO.getSiteStockHistoryByPartNumber(partNumber);
	}
	@Override
	public List<History> getAllSiteStockHistoryByPartNumber() {
		return historyDAO.getAllSiteStockHistoryByPartNumber();
	}
	@Override
	public List<History> getBootStockHistoryByPartNumber(String partNumber) {
		return historyDAO.getBootStockHistoryByPartNumber(partNumber);
	}
	@Override
	public List<History> getAllBootStockHistoryByPartNumber() {
		return historyDAO.getAllBootStockHistoryByPartNumber();
	}
		
}

package za.co.fenya.demo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.bean.SparePartsBean;
import za.co.fenya.demo.dao.HOStockDaoInt;
import za.co.fenya.demo.model.HOStock;
import za.co.fenya.demo.service.HOStockServeceInt;


@Service("HOStockService")
public class HOStockService implements HOStockServeceInt{

	@Autowired
	private HOStockDaoInt sparePartsDAO;
	
	private String retMessage = null;
	
	
	@Override
	public String saveSpareparts(HOStock spareParts,SparePartsBean sparePartsBean) {
		
		retMessage =sparePartsDAO.saveSpareparts(spareParts,sparePartsBean);
	    return retMessage;
	}


	@Override
	public HOStock getSparePartBySerial(String serialNum) {
		
		return sparePartsDAO.getSparePartBySerial(serialNum);
	}


	@Override
	public List<HOStock> getAllSpareParts() {
		return sparePartsDAO.getAllSpareParts();
	}


	@Override
	public List<HOStock> getAllSparePartsWithoutZero() {
		return sparePartsDAO.getAllSparePartsWithoutZero();
	}


	@Override
	public int countHeadOfficeStock() {
		return sparePartsDAO.countHeadOfficeStock();
	}

}

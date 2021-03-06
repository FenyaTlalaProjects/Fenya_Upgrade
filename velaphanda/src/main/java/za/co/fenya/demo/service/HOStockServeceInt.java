package za.co.fenya.demo.service;


import java.util.List;

import za.co.fenya.demo.bean.SparePartsBean;
import za.co.fenya.demo.model.HOStock;


public interface HOStockServeceInt {
	public String saveSpareparts(HOStock spareParts,SparePartsBean sparePartsBean);
	public HOStock getSparePartBySerial(String serialNum);
	public List<HOStock> getAllSpareParts();
	public List<HOStock> getAllSparePartsWithoutZero();
	int countHeadOfficeStock();

}

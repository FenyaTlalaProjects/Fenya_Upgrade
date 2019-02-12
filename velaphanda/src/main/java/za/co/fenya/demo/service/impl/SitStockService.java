package za.co.fenya.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.dao.SiteStocDaoInt;
import za.co.fenya.demo.model.SiteStock;
import za.co.fenya.demo.service.SiteStockInt;

@Service("siteStockService")
public class SitStockService implements SiteStockInt{

	@Autowired
	private SiteStocDaoInt siteStock;
	@Override
	public List<SiteStock> getAllOrders() {
		return siteStock.getAllOrders();
	}
	@Override
	public List<SiteStock> getOrdersForCustomer(String customerName) {
		return siteStock.getOrdersForCustomer(customerName);
	}
	@Override
	public List<SiteStock> getOrdersByTechnician(String technician) {
		return siteStock.getOrdersByTechnician(technician);
	}
	@Override
	public List<SiteStock> getOrdersForCustomer(String customerName,
			Long ticketID) {
		
		return siteStock.getOrdersForCustomer(customerName, ticketID);
	}
	@Override
	public int countSiteStock() {
		return siteStock.countSiteStock();
	}
	@Override
	public List<SiteStock> getTonerForCustomer(String customerName) {
		return siteStock.getTonerForCustomer(customerName);
	}
	@Override
	public List<SiteStock> getPartsForCustomer(String customerName) {
		return siteStock.getPartsForCustomer(customerName);
	}
	@Override
	public int countTonerForCustomer(String customerName) {
		return siteStock.countTonerForCustomer(customerName);
	}
	@Override
	public int countPartsForCustomer(String customerName) {
		return siteStock.countPartsForCustomer(customerName);
	}
	@Override
	public List<SiteStock> getAllSiteStock() {
		return siteStock.getAllSiteStock();
	}
	@Override
	public int countSiteStock(String technicianName) {
		return siteStock.countSiteStock(technicianName);
	}
	@Override
	public List<SiteStock> getAllSiteStock(String technicianName) {
		return siteStock.getAllSiteStock(technicianName);
	}
	@Override
	public int countTonerForCustomer(String customerName, String technicianName) {
		return siteStock.countPartsForCustomer(customerName, technicianName);
	}
	@Override
	public int countPartsForCustomer(String customerName, String technicianName) {
		return siteStock.countPartsForCustomer(customerName, technicianName);
	}
	@Override
	public List<SiteStock> getOrdersForCustomer(String customerName,
			String compatibleDevice) {
		return siteStock.getOrdersForCustomer(customerName, compatibleDevice);
	}
	@Override
	public String moveStock(String fromCustomerName, String fromTechnicianName,
			String partNumberList, String quantity, String options,
			String customerName, String technicianName, String reasonForSite,
			String reasonForBoot,String headOffice,String reasonForHeadOffice) {
		return siteStock.moveStock(fromCustomerName, fromTechnicianName, partNumberList, quantity, options, customerName, technicianName, reasonForSite, reasonForBoot,headOffice,reasonForHeadOffice);
	}


}

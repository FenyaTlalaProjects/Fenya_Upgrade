package za.co.fenya.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.dao.BootStockDaoInt;
import za.co.fenya.demo.model.BootStock;
import za.co.fenya.demo.service.BootStockInt;


@Service("bootStockService")
public class BootStockService implements BootStockInt{
	
	@Autowired
	private BootStockDaoInt bootStockDaoInt;

	@Override
	public List<BootStock> getAllOrders() {
		return bootStockDaoInt.getAllOrders();
		
	}

	@Override
	public List<BootStock> getAllOrders(String technician) {
		
		return bootStockDaoInt.getAllOrders(technician);
	}

	@Override
	public List<BootStock> getAllOrders(String technician, Long ticketRecordID) {
		
		return bootStockDaoInt.getAllOrders(technician, ticketRecordID);
	}

	@Override
	public int countBootStock() {
		return bootStockDaoInt.countBootStock();
	}

	@Override
	public int countPartsForTechnician(String technicianName) {
		return bootStockDaoInt.countPartsForTechnician(technicianName);
	}

	@Override
	public List<BootStock> getPartsForTechnician(String technicianName) {
		return bootStockDaoInt.getPartsForTechnician(technicianName);
	}

	@Override
	public int countTonerForTechnician(String technicianName) {
		return bootStockDaoInt.countTonerForTechnician(technicianName);
	}

	@Override
	public List<BootStock> getTonerForTechnician(String technicianName) {
		return bootStockDaoInt.getTonerForTechnician(technicianName);
	}

	@Override
	public List<BootStock> getAllBootStock() {
		return bootStockDaoInt.getAllBootStock();
	}

	@Override
	public List<BootStock> getAllBootStockByTechnician(String technicianName) {
		return bootStockDaoInt.getAllBootStockByTechnician(technicianName);
	}

	@Override
	public int countBootStock(String technicianName) {
		return bootStockDaoInt.countBootStock(technicianName);
	}

}

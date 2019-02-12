package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.model.ApprovedOrderStock;
import za.co.fenya.demo.model.OrderDetails;


public interface ApprovedOrderStockDaoInt {
	
	
	public String approveOrderStock(List<OrderDetails> detailsDaos);
	
	public List<ApprovedOrderStock> getApprovedOrdersByOrderNumber(String orderNumber);

}

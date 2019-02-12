package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.model.OrderHeader;
import za.co.fenya.demo.model.OrderHistory;


public interface OrderHistoryDaoInt {
	
	void insetOrderHistory(OrderHeader order);
	List<OrderHistory> getAllOrderHistoryByOrderNumber(Long recordID);
	List<OrderHistory> getAllOrderHistoryByOrderNumber();
	List<OrderHistory> getAllOrderHistoryTicketNumber(Long ticketNumber);

}

package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.model.OrderHistory;


public interface OrderHistoryInt {
	List<OrderHistory> getAllOrderHistoryByOrderNumber(Long recordID);
	List<OrderHistory> getAllOrderHistoryTicketNumber(Long ticketNumber);

}

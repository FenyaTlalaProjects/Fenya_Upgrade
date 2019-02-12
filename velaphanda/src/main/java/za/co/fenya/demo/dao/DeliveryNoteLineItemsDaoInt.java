package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.model.OrderDetails;


public interface DeliveryNoteLineItemsDaoInt {

	void saveDeliveryNoteLineItems(List<OrderDetails> orderDetails);
}

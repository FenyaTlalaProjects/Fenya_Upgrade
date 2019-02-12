package za.co.fenya.demo.dao;

import za.co.fenya.demo.model.DeliveryNoteHeader;
import za.co.fenya.demo.model.OrderHeader;


public interface DeliveryNoteHeaderDaoInt {

	void saveDeliveryNoteHeader(OrderHeader orderHeader);
	DeliveryNoteHeader getDeliveryNoteHeader(Integer recordID);
}

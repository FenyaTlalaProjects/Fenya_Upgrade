package za.co.fenya.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.dao.DeliveryNoteHeaderDaoInt;
import za.co.fenya.demo.model.DeliveryNoteHeader;
import za.co.fenya.demo.service.DeliveryNoteHeaderServiceInt;




@Service("deliveryNoteHeaderService")
public class DeliveryNoteHeaderService implements DeliveryNoteHeaderServiceInt{

	
	@Autowired
	private DeliveryNoteHeaderDaoInt deliveryNodeHeader;
	
	
	@Override
	public DeliveryNoteHeader getDeliveryNoteHeader(Integer recordID) {
		
		return deliveryNodeHeader.getDeliveryNoteHeader(recordID);
	}

}

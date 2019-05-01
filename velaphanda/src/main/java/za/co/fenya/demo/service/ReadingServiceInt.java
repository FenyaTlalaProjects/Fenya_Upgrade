package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.bean.ReadingBean;
import za.co.fenya.demo.model.Reading;


public interface ReadingServiceInt {
	

	String createReading(ReadingBean reading);
	List<Reading> getPreviousReadingForDevice(String serialNumber);	
}

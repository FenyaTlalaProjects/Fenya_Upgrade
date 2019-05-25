package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.bean.ReadingBean;
import za.co.fenya.demo.model.Reading;


public interface ReadingServiceInt {
	

	List<Reading> createReading(ReadingBean reading);
	Reading getPreviousReadingForDevice(String serialNumber);
	List<Reading> getAllReadings();
	String saveReading (ReadingBean reading);
	String submitReading (ReadingBean reading);
	String createDefaultReading(ReadingBean reading);
}

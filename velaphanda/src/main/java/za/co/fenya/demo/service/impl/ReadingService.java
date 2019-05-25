package za.co.fenya.demo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.bean.ReadingBean;
import za.co.fenya.demo.dao.ReadingDaoInt;
import za.co.fenya.demo.model.Reading;
import za.co.fenya.demo.service.ReadingServiceInt;



//Device
@Service("readingService")
public class ReadingService implements ReadingServiceInt {
	
	@Autowired
	private ReadingDaoInt readingDAO;
	
	private String retMessage = null;
	
	@Override
	public Reading createReading(ReadingBean reading) {
		// TODO Auto-generated method stub
		return readingDAO.createReading(reading);	 
	}

	@Override
	public Reading getPreviousReadingForDevice(String serialNumber) {
		// TODO Auto-generated method stub
		return readingDAO.getPreviousReadingForDevice(serialNumber);
	}

	@Override
	public List<Reading> getAllReadings() {
		// TODO Auto-generated method stub
		return readingDAO.getAllReadings();
	}		
	
	public String saveReading(ReadingBean reading) {
		// TODO Auto-generated method stub
		return readingDAO.saveReading(reading);
	}	

	public String submitReading(ReadingBean reading) {
		// TODO Auto-generated method stub
		return readingDAO.saveReading(reading);
}

	@Override
	public String createDefaultReading(ReadingBean reading) {
		// TODO Auto-generated method stub
		return readingDAO.createDefaultReading(reading);
	}
}

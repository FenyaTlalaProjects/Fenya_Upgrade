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
	public String createReading(ReadingBean reading) {
		// TODO Auto-generated method stub
		retMessage = readingDAO.createReading(reading);
		return retMessage;
	}

	@Override
	public List<Reading> getPreviousReadingForDevice(String serialNumber) {
		// TODO Auto-generated method stub
		return readingDAO.getPreviousReadingForDevice(serialNumber);
	}

	@Override
	public List<Reading> getAllReadings() {
		// TODO Auto-generated method stub
		return readingDAO.getAllReadings();
	}		

}

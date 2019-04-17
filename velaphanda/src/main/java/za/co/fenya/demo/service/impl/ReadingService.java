package za.co.fenya.demo.service.impl;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.bean.ReadingBean;
import za.co.fenya.demo.dao.ReadingDaoInt;
import za.co.fenya.demo.service.ReadingServiceInt;



//Device
@Service("readingService")
public class ReadingService implements ReadingServiceInt {
	
	@Autowired
	private ReadingDaoInt readingDAO1;
	private String retMessage = null;
	
	@Override
	public String createReading(ReadingBean reading) {
		// TODO Auto-generated method stub
		retMessage = readingDAO1.createReading(reading);
		return retMessage;
	}
		

}

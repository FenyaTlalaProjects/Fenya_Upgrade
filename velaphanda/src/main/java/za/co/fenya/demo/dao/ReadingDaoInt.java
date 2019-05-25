 package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.bean.ReadingBean;
import za.co.fenya.demo.model.Reading;

public interface ReadingDaoInt {

	
	List<Reading> createReading(ReadingBean reading);

	List<Reading> getAllReadings();
	
	List<Reading> getReadingsByMonthOfYear(String month, String year);

	List<Reading> getReadingsByUser(String user);

	List<Reading> getReadingsByModifyApprover(String approver);

	List<Reading> getReadingsByModifyUser(String user);

	List<Reading> getReadingsBySerialNumber(String serialNumber);

	List<Reading> getReadingsByCustomerName(String customerName);

	List<Reading> selectReadingsForManager(String customer, String dateRange, String user, String serialNumber);

	Reading getPreviousReadingForDevice(String serialNumber);

	List<Reading> confirmReadingExist(String serialNumber, String period);

	String createDefaultReading(ReadingBean reading);	
	String saveReading(ReadingBean reading);
	String submitReading(ReadingBean reading);
	

}

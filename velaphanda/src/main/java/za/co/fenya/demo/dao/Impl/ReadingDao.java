package za.co.fenya.demo.dao.Impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.bean.CustomerDeviceHistoryBean;
import za.co.fenya.demo.bean.DeviceBean;
import za.co.fenya.demo.bean.LeaveBean;
import za.co.fenya.demo.bean.ReadingBean;
import za.co.fenya.demo.dao.AccessoriesDaoInt;
import za.co.fenya.demo.dao.CustomerDaoInt;
import za.co.fenya.demo.dao.CustomerDeviceHistoryDaoInt;
import za.co.fenya.demo.dao.DeviceContactPersonDaoInt;
import za.co.fenya.demo.dao.DeviceDaoInt;
import za.co.fenya.demo.dao.EmployeeDaoInt;
import za.co.fenya.demo.dao.ModelNumbersMasterDaoInt;
import za.co.fenya.demo.dao.ReadingDaoInt;
import za.co.fenya.demo.dao.SiteStocDaoInt;
import za.co.fenya.demo.dao.TicketsDaoInt;
import za.co.fenya.demo.model.Accessories;
import za.co.fenya.demo.model.Customer;
import za.co.fenya.demo.model.Device;
import za.co.fenya.demo.model.DeviceContactPerson;
import za.co.fenya.demo.model.Employee;
import za.co.fenya.demo.model.Leave;
import za.co.fenya.demo.model.ModelNumbers;
import za.co.fenya.demo.model.Reading;
import za.co.fenya.demo.model.SiteStock;
import za.co.fenya.demo.model.TicketHistory;
import za.co.fenya.demo.model.Tickets;
import za.co.fenya.demo.reports.initializer.DeviceReportBean;
import za.co.fenya.demo.service.TicketHistoryInt;

@Repository("readingDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class ReadingDao implements ReadingDaoInt {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private EmployeeDaoInt employeeDao;
	@Autowired
	private HttpSession session = null;
	@Autowired
	private Reading globalReading;
	@Autowired
	private Customer customer;
	@Autowired
	private Device device;
	@Autowired
	private CustomerDaoInt customerDaoInt;
	@Autowired
	private EmployeeDaoInt employeeDaoInt;
	@Autowired
	private DeviceDaoInt deviceDaoInt;

	private String retMessage = null;
	private ReadingBean readingBean = null;

	Employee userName, emp = null;
	DateFormat dateFormat = null;
	Date date = null;
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String timeDeviceAdded = sdfDate.format(now);

	@Override
	public String createReading(ReadingBean reading) {

		Employee employee = (Employee) session.getAttribute("loggedInUser");
		globalReading = new Reading();
		customer = new Customer();
		device = new Device();
		emp = new Employee();

		String userName = null;

		// Get Current Time Stamp
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:s");
		Date currentDate = new Date();
		cal.setTime(new Date());

		currentDate = cal.getTime();
		customer = customerDaoInt.getClientByClientName(reading.getCustomerName());
		emp = employeeDaoInt.getEmployeeByEmpNum(reading.getEmployee());
		device = deviceDaoInt.getDeviceBySerialNumbuer(reading.getSerialNumber());

		try {

			if (customer != null && device != null && employee != null) {
				globalReading.setColorReading(reading.getColorReading());
				globalReading.setCustomerName(customer);
				globalReading.setEmployee(employee);
				globalReading.setMonoReading(reading.getMonoReading());
				globalReading.setPreviousColorReading(reading.getPreviousColorReading());
				globalReading.setPreviousMonoReading(reading.getPreviousMonoReading());
				globalReading.setReadingMonth(reading.getReadingMonth());
				globalReading.setReadingYear(reading.getReadingYear());
				globalReading.setInsertDate(currentDate.toString());

				sessionFactory.getCurrentSession().save(globalReading);
				retMessage = "Reading successfully submited";

			}
		}

		catch (Exception e) {
			retMessage = "Reading for " + device.getSerialNumber() + " not added\n" + e.getMessage() + ".";
		}

		return retMessage;

	}

}

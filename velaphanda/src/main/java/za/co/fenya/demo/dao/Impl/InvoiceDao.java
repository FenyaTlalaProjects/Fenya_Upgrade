package za.co.fenya.demo.dao.Impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.bean.InvoiceBean;
import za.co.fenya.demo.bean.ReadingBean;
import za.co.fenya.demo.dao.CustomerDaoInt;
import za.co.fenya.demo.dao.DeviceDaoInt;
import za.co.fenya.demo.dao.EmployeeDaoInt;
import za.co.fenya.demo.dao.InvoiceDaoInt;
import za.co.fenya.demo.dao.ReadingDaoInt;
import za.co.fenya.demo.model.Customer;
import za.co.fenya.demo.model.Device;
import za.co.fenya.demo.model.Employee;
import za.co.fenya.demo.model.InvoiceHeader;
import za.co.fenya.demo.model.Reading;
import za.co.fenya.demo.model.Tickets;
import za.co.fenya.demo.model.Reading;


@Repository("invoiceDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class InvoiceDao implements InvoiceDaoInt {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private EmployeeDaoInt employeeDao;
	@Autowired
	private HttpSession session = null;
	
	private Reading globalReading;
	private InvoiceHeader globalInvoiceHeader;
	private Customer customer;
	private Device device;
	@Autowired
	private CustomerDaoInt customerDaoInt;
	@Autowired
	private EmployeeDaoInt employeeDaoInt;
	@Autowired
	private DeviceDaoInt deviceDaoInt;

	private String retMessage = null;
	private ReadingBean readingBean = null;
	private InvoiceBean invoiceBean = null;
	Employee userName, emp = null;
	DateFormat dateFormat = null;
	Date date = null;
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String timeDeviceAdded = sdfDate.format(now);
	ArrayList<?> aList = null;
	ArrayList<Reading> readingList = null;
	
	
	@Override
	public String createInvoiceHeader(InvoiceBean invoice) {
		
		Employee employee = (Employee) session.getAttribute("loggedInUser");
		globalReading = new Reading();
		customer = new Customer();
		device = new Device();
		emp = new Employee();
		globalInvoiceHeader = new InvoiceHeader();

		String userName = null;

		// Get Current Time Stamp
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:s");
		Date currentDate = new Date();
		cal.setTime(new Date());

		currentDate = cal.getTime();
		customer = customerDaoInt.getClientByClientName(invoice.getCustomerName());
		emp = employeeDaoInt.getEmployeeByEmpNum(invoice.getCreatedBy());
		device = deviceDaoInt.getDeviceBySerialNumbuer(invoice.getSerialNumber());

		try {

			if (customer != null && device != null && employee != null) {
				
				globalInvoiceHeader.setEmployee(emp);			
				globalInvoiceHeader.setCustomerName(customer);
				globalInvoiceHeader.setDevice(device);
				globalInvoiceHeader.setInvoiceDate(invoice.getInvoiceDate());
				//globalInvoiceHeader.setOrderNumber(invoice.getOrderNumber());
				globalInvoiceHeader.setPeriod(invoice.getPeriod());
				globalInvoiceHeader.setStatus("Draft");
				
				sessionFactory.getCurrentSession().save(globalInvoiceHeader);
				
				String recordID = globalInvoiceHeader.getRecordID().toString();
				
				String padded = String.format("%06d", recordID); 
				retMessage = recordID;
			}
		}

		catch (Exception e) {
			retMessage = "Invoice for " + device.getSerialNumber() + " not added\n" + e.getMessage() + ".";
		}

		return retMessage;

	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceHeader> getAllInvoices() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				InvoiceHeader.class);
		return (List<InvoiceHeader>) criteria.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceHeader> getInvoiceByInvoiceNumber (String invoiceNumber) {
		List<InvoiceHeader> aList = getAllInvoices();
		ArrayList<InvoiceHeader> invoiceList = new ArrayList<InvoiceHeader>();
		
		long invoiceNum = 0;
		if (invoiceNumber.length() == 8) 
		{
			invoiceNum = removeInvoicePadding(invoiceNumber);
		}
		else
		{
			invoiceNum = Long.parseLong(invoiceNumber);
		}

		for (InvoiceHeader invoice : aList) {
			if( invoice.getRecordID() == invoiceNum )
			{
				invoiceList.add(invoice);
			}
		}
		return invoiceList;
	}
	
	public long removeInvoicePadding(String invoiceNumber)
	{
		String subString = invoiceNumber.substring(3,9);
		long convertedNum = Long.parseLong(subString);
		
		return convertedNum;
		
	}

	
	
     /*
	@SuppressWarnings("unchecked")
	@Override
	public List<Reading> getReadingsByMonthOfYear (String month, String year) {
		List<Reading> aList = getAllReadings();
		ArrayList<Reading> readingList = new ArrayList<Reading>();
		

		for (Reading reading : aList) {
			if( reading.getReadingMonth().equalsIgnoreCase(month) && reading.getReadingYear().equalsIgnoreCase(year) )
			{
				readingList.add(reading);
			}
		}
		return readingList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reading> getReadingsByUser (String user) {
		List<Reading> aList = getAllReadings();
		ArrayList<Reading> readingList = new ArrayList<Reading>();
		

		for (Reading reading : aList) {
			if( reading.getEmployee().getEmail().equalsIgnoreCase(user) )
			{
				readingList.add(reading);
			}
		}
		return readingList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reading> getReadingsByModifyApprover (String approver) {
		List<Reading> aList = getAllReadings();
		ArrayList<Reading> readingList = new ArrayList<Reading>();
		
		for (Reading reading : aList) {
			if( reading.getModifyApprover().getEmail().equalsIgnoreCase(approver) )
			{
				readingList.add(reading);
			}
		}
		return readingList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reading> getReadingsByModifyUser (String user) {
		List<Reading> aList = getAllReadings();
		ArrayList<Reading> readingList = new ArrayList<Reading>();
		
		for (Reading reading : aList) {
			if( reading.getModifiedBy().getEmail().equalsIgnoreCase(user) )
			{
				readingList.add(reading);
			}
		}
		return readingList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reading> getReadingsByCustomerName (String customerName) {
		List<Reading> aList = getAllReadings();
		ArrayList<Reading> readingList = new ArrayList<Reading>();
		
		for (Reading reading : aList) {
			if( reading.getCustomerName().getCustomerName().equalsIgnoreCase(customerName) )
			{
				readingList.add(reading);
			}
		}
		return readingList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reading> getPreviousReadingForDevice(String serialNumber) {
		//String name = serialNumber;
		
		List<Reading> aList = new ArrayList<Reading>();
		List<Reading> readingList = null;
		Reading currentReading =  new Reading();
		try {

			readingList = getAllReadings();
			int i = 0;
			for (Reading reading : readingList) {
				if (reading.getSerialNumber().getSerialNumber().equalsIgnoreCase(serialNumber))
				{
				  if (i == 0)
				  {
					 currentReading = reading; 
				  }
				  else 
				  {
					if (currentReading.getRecordID() < reading.getRecordID() )
					{
						currentReading = reading;
					}
				  }
				  i++;
				}
							
			}
			
		if (currentReading.getSerialNumber().getSerialNumber() != null || currentReading.getSerialNumber().getSerialNumber() != "")
			{
				aList.add(currentReading);
			}
		
		} catch (Exception e) {
			return null;
		}
		return aList;
	}

	
	@Override
	public List<Reading> selectReadingsForManager(String customer,
			String dateRange, String user, String serialNumber) {
		
		boolean allCustomers = false;
		boolean allUsers = false;
		boolean dateNotSelected = false;

		List<Reading> readingList = null;
		List<Reading> aList = new ArrayList<Reading>();

		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date myStart = new Date();
		Date myEnd = new Date();
		Date dateData = new Date();
		String convDate = "";
		String normalDate = "";

		try {

			allCustomers = customer.equalsIgnoreCase("All Customers");
			allUsers = user.equalsIgnoreCase("All Users");
			dateNotSelected = dateRange.equalsIgnoreCase("Select Date");
			String startDate = "";
			String endDate = "";
			if (dateNotSelected != true) {
				startDate = dateRange.substring(0, 10);
				endDate = dateRange.substring(13);

				myStart = new Date();
				myEnd = new Date();
				dateData = new Date();

				myStart = myFormat.parse(startDate);
				myEnd = myFormat.parse(endDate);

			}

			if (serialNumber != null) {
				readingList = getReadingsBySerialNumber(serialNumber);				
				}
			else if (serialNumber == null) {
		
				if (allCustomers == true && allUsers == true) {
					if (dateNotSelected == false) {
						readingList = getAllReadings();
						for (Reading reading : readingList) {

							
							convDate = reading.getInsertDate().substring(0, 10);
							normalDate = convDate.replace("/", "-");
							dateData = myFormat.parse(normalDate);
							boolean withinRange = false;
							if (myStart.compareTo(dateData) <= 0
									&& myEnd.compareTo(dateData) >= 0) {
								// withinRange = true;
								aList.add(reading);
							}
						}
					} else if (dateNotSelected == true) {
						readingList = getAllReadings();
						for (Reading reading : readingList) {
							aList.add(reading);
						}
					}

				}

				else if (allCustomers == true && allUsers == false) {
					if (dateNotSelected == false) {
						readingList = getAllReadings();
						for (Reading reading : readingList) {

							convDate = reading.getInsertDate().substring(0, 10);
							normalDate = convDate.replace("/", "-");
							dateData = myFormat.parse(normalDate);
							boolean withinRange = false;
							if (myStart.compareTo(dateData) <= 0
									&& myEnd.compareTo(dateData) >= 0) {
								withinRange = true;
							}
							if (reading.getEmployee().getEmail()
									.equalsIgnoreCase(user)
									&& withinRange == true) {
								aList.add(reading);
							}

						}
					} else if (dateNotSelected == true) {
						readingList = getAllReadings();
						for (Reading reading : readingList) {
							if (reading.getEmployee().getEmail()
									.equalsIgnoreCase(user)) {
								aList.add(reading);
							}
						}
					}
				}
				else if (allCustomers == false && allUsers == true) {
					if (dateNotSelected == false) {
						readingList = getAllReadings();
						for (Reading reading : readingList) {

							convDate = reading.getInsertDate().substring(0, 10);
							normalDate = convDate.replace("/", "-");
							dateData = myFormat.parse(normalDate);
							boolean withinRange = false;
							if (myStart.compareTo(dateData) <= 0
									&& myEnd.compareTo(dateData) >= 0) {
								withinRange = true;
							}
							if (reading.getCustomerName().getCustomerName() != null ) 
							{
								if (reading.getCustomerName()
										.getCustomerName()
										.equalsIgnoreCase(customer)
										&& withinRange == true) {
									aList.add(reading);
								}

							}
							
						}
					} else if (dateNotSelected == true) {
						readingList = getAllReadings();
						for (Reading reading : readingList) {
							if (reading.getCustomerName() != null )
							{
								if (reading.getCustomerName() != null ) 
								{
									if (reading.getCustomerName()
											.getCustomerName()
											.equalsIgnoreCase(customer)) {
										aList.add(reading);
									}
								}
								
							}
							

						}
					}
				}

				else if (allCustomers == false && allUsers == false) {
					ReadingBean reading = null;
					if (dateNotSelected == false) {
						readingList = getAllReadings();
						for (Reading order : readingList) {

							convDate = reading.getInsertDate().substring(0, 10);
							normalDate = convDate.replace("/", "-");
							dateData = myFormat.parse(normalDate);
							boolean withinRange = false;
							if (myStart.compareTo(dateData) <= 0
									&& myEnd.compareTo(dateData) >= 0) {
								withinRange = true;
							}
							if (reading.getCustomerName() != null ) 
							{
								if (reading.getCustomerName()							
										.equalsIgnoreCase(customer)
										&& order.getEmployee().getEmail()
												.equalsIgnoreCase(user)
										&& withinRange == true) {
									aList.add(order);
								}
							}
							
						}
					} else if (dateNotSelected == true) {
						readingList = getAllReadings();
						for (Reading order : readingList) {
							if (reading.getCustomerName() != null ) 
							{
								if (reading.getCustomerName()
										.equalsIgnoreCase(customer)
										&& order.getEmployee().getEmail()
												.equalsIgnoreCase(user)) {
									aList.add(order);
								}

							}
							
						}
					}
				}
			}
		} catch (Exception exception) {
			exception.getMessage();
		}

		return aList;
	}
	
	*/
     
}

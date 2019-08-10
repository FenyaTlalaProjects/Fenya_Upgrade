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
import za.co.fenya.demo.model.InvoiceContactAddress;
import za.co.fenya.demo.model.InvoiceHeader;
import za.co.fenya.demo.model.InvoiceLineItem;
import za.co.fenya.demo.model.InvoicePayment;
import za.co.fenya.demo.model.Reading;
import za.co.fenya.demo.model.Tickets;
//import za.co.fenya.demo.util.StringToDouble;
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
	
	ReadingDao readingDAO = null;
	private Reading globalReading;
	private InvoiceHeader globalInvoiceHeader;
	private InvoiceLineItem globalLineItem;
	private InvoiceContactAddress globalAddress;
	private InvoicePayment globalPayment;
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
	private Reading reading = null;
	Employee userName, emp = null;
	DateFormat dateFormat = null;
	Date date = null;
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String timeDeviceAdded = sdfDate.format(now);
	ArrayList<?> aList = null;
	ArrayList<Reading> readingList = null;
	double rentalValue = 0.00;
	double monoCopyCost = 0.00;
	double colorCopyCost = 0.00;
	double monoTotal = 0.00;
	double colorTotal = 0.00;
	double lineAmount = 0.0;
	double colorLineAmount = 0.0;
	String machineType = null;
	int lineItemNumber = 0;
		
	
	public InvoiceBean createInvoice (InvoiceBean invoice)
	{
		Employee employee = (Employee) session.getAttribute("loggedInUser");
		InvoiceHeader invoiceHeader = new InvoiceHeader();
		InvoiceBean localBean = new InvoiceBean();
		customer = new Customer();
		device = new Device();
		emp = new Employee();
		List<InvoiceHeader> aList = getAllInvoices();
		String rentalValue = "";
		readingDAO =  new ReadingDao();
		// Get Current Time Stamp
		Calendar cal = Calendar.getInstance();
		// SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:s");
		Date currentDate = new Date();
		cal.setTime(new Date());

		currentDate = cal.getTime();
		customer = customerDaoInt.getClientByClientName(invoice.getCustomerName());
		emp = employeeDaoInt.getEmployeeByEmpNum(invoice.getCreatedBy());
		device = deviceDaoInt.getDeviceBySerialNumbuer(invoice.getSerialNumber());
		machineType = device.getMachineType();
		reading = readingDAO.getReadingsForInvoice(device.getSerialNumber(), invoice.getInvoicePeriod());
		
		
		boolean invoiceExist = false;

		try {
			
			if (reading != null)
			{
				if (device != null && invoice.getPeriod() != null) {
					for (InvoiceHeader invoiceValue : aList) {
						if (invoiceValue.getDevice().getSerialNumber().equalsIgnoreCase(invoice.getSerialNumber())
								&& invoiceValue.getPeriod().equalsIgnoreCase(invoice.getPeriod()))
						{
							 globalInvoiceHeader = invoiceValue ;
				//			 invoiceExist = true;
						}
					}
				}
				
				// Ingnore if reading already exist.
				if (invoiceExist == false) {
					
					
					if (device.isRentalMachine() == true)
					{
						rentalValue = device.getRentalCost();					
					}
					
					monoCopyCost = Double.parseDouble(device.getMonoCopyCost());
					colorCopyCost  = Double.parseDouble(device.getMonoCopyCost());
					monoTotal = Double.parseDouble (reading.getMonoTotal());
					colorTotal = Double.parseDouble (reading.getColorTotal());
					
					globalInvoiceHeader.setCustomerName(customer);
					globalInvoiceHeader.setEmployee(employee);
					globalInvoiceHeader.setDevice(device);
					globalInvoiceHeader.setPeriod(invoice.getPeriod());
					globalInvoiceHeader.setInsertDate(currentDate.toString());
					globalInvoiceHeader.setStatus(invoice.getStatus());
					globalInvoiceHeader.setInvoiceDate(invoice.getInvoiceDate());
					sessionFactory.getCurrentSession().save(globalInvoiceHeader);
				
					if (machineType.equalsIgnoreCase("Mono"))
					{
					  lineAmount = 	monoCopyCost * monoTotal;
					  
					  
					  lineItemNumber = lineItemNumber + 1;
					  globalLineItem.setCurrency(invoice.getCurrency());
					  globalLineItem.setItemDescription("Mono Readings" + " " + reading.getPreviousMonoReading() + " " + reading.getMonoReading());
					  globalLineItem.setInvoiceNumber(globalInvoiceHeader);
					  globalLineItem.setLineNumber(Integer.toString(lineItemNumber));
					  globalLineItem.setUnitOfMeasure("Each");
					  globalLineItem.setPrice(device.getMonoCopyCost());
					  globalLineItem.setQuantity(reading.getMonoTotal());
					  globalLineItem.setStartReading(reading.getPreviousMonoReading());
					  globalLineItem.setEndReading(reading.getMonoReading());
					  globalLineItem.setTotal(Double.toString(lineAmount));
					  sessionFactory.getCurrentSession().save(globalInvoiceHeader);
					  
						if (device.isRentalMachine() == true)
						{
							 lineItemNumber = lineItemNumber + 1;
							  globalLineItem.setCurrency(invoice.getCurrency());
							  globalLineItem.setItemDescription("Rental Value");
							  globalLineItem.setInvoiceNumber(globalInvoiceHeader);
							  globalLineItem.setLineNumber(Integer.toString(lineItemNumber));
							  globalLineItem.setUnitOfMeasure("Each");
							  globalLineItem.setTotal(rentalValue);
							  sessionFactory.getCurrentSession().save(globalInvoiceHeader);
						}
					}
					else if (machineType.equalsIgnoreCase("Color"))
					{
						lineAmount = 	monoCopyCost * monoTotal;
						colorLineAmount = colorCopyCost * colorTotal;
						  
						  
						  lineItemNumber = lineItemNumber + 1;
						  globalLineItem.setCurrency(invoice.getCurrency());
						  globalLineItem.setItemDescription("Mono Readings" + " " + reading.getPreviousMonoReading() + " " + reading.getMonoReading());
						  globalLineItem.setInvoiceNumber(globalInvoiceHeader);
						  globalLineItem.setLineNumber(Integer.toString(lineItemNumber));
						  globalLineItem.setUnitOfMeasure("Each");
						  globalLineItem.setPrice(device.getMonoCopyCost());
						  globalLineItem.setQuantity(reading.getMonoTotal());
						  globalLineItem.setStartReading(reading.getPreviousMonoReading());
						  globalLineItem.setEndReading(reading.getMonoReading());
						  globalLineItem.setTotal(Double.toString(lineAmount));
						  sessionFactory.getCurrentSession().save(globalInvoiceHeader);
						  
							
						  //Capture Color reading
						  lineItemNumber = lineItemNumber + 1;
						  globalLineItem.setCurrency(invoice.getCurrency());
						  globalLineItem.setItemDescription("Color Readings"+ " " + reading.getPreviousColorReading() + " " + reading.getColorReading());
						  globalLineItem.setInvoiceNumber(globalInvoiceHeader);
						  globalLineItem.setLineNumber(Integer.toString(lineItemNumber));
						  globalLineItem.setUnitOfMeasure("Each");
						  globalLineItem.setPrice(device.getColourCopyCost());
						  globalLineItem.setQuantity(reading.getColorTotal());
						  globalLineItem.setStartReading(reading.getPreviousColorReading());
						  globalLineItem.setEndReading(reading.getColorReading());
						  globalLineItem.setTotal(Double.toString(lineAmount));
						  sessionFactory.getCurrentSession().save(globalInvoiceHeader);
							
						  if (device.isRentalMachine() == true)
							{
								 lineItemNumber = lineItemNumber + 1;
								  globalLineItem.setCurrency(invoice.getCurrency());
								  globalLineItem.setItemDescription("Rental Value");
								  globalLineItem.setInvoiceNumber(globalInvoiceHeader);
								  globalLineItem.setLineNumber(Integer.toString(lineItemNumber));
								  globalLineItem.setUnitOfMeasure("Each");
								  globalLineItem.setTotal(rentalValue);
								  sessionFactory.getCurrentSession().save(globalInvoiceHeader);
							}
						
					}
					
					
					localBean = retrieveLineItems(invoice);
					//paddedReading = String.format("%06d", globalReading.getRecordID()); 
					
					retMessage = "Reading successfully submited";
				}
				
				
			}
						//if true, refresh line items
		

		}

		catch (

		Exception e) 
		{
			retMessage = "Reading for " + device.getSerialNumber() + " not added\n" + e.getMessage() + ".";
		}
	
		return localBean;
	}
	
	public InvoiceBean retrieveLineItems (InvoiceBean invoice)
	{
		
		String [] lineNumber = null;
		String [] itemDescription = null;
		String [] startReading = null;
		String [] endReading = null;
		String [] unitOfMeasure = null;
		String [] quantity = null;
		String [] price = null;
		String [] lineCurrency = null;
		String [] lineTotal = null;
		InvoiceBean localBean = new InvoiceBean();
		List<InvoiceLineItem> aList = getAllLineItems();
		
		int index = 0;
		
	
		if (aList != null)
		{
			for (InvoiceLineItem invoiceValue : aList) 
			{
				if (invoiceValue.getInvoiceNumber().getRecordID().toString().equalsIgnoreCase(invoice.getInvoiceNumber()))
				{
					String lineNum = invoiceValue.getLineNumber();
					index = Integer.parseInt(lineNum) - 1;

					lineNumber[index] =  lineNum;
					itemDescription [index] = invoiceValue.getItemDescription();
					startReading [index] =  invoiceValue.getStartReading();
					endReading[index] = invoiceValue.getEndReading();
					unitOfMeasure[index] = invoiceValue.getUnitOfMeasure();
					quantity[index] = invoiceValue.getQuantity();
					price[index] = invoiceValue.getPrice();
					lineCurrency [index] = invoiceValue.getCurrency();
					lineTotal [index] = invoiceValue.getTotal();
					
					localBean.setLineNumber(lineNumber);
					localBean.setItemDescription(itemDescription);
					localBean.setStartReading(startReading);
					localBean.setEndReading(endReading);
					localBean.setUnitOfMeasure(unitOfMeasure);
					localBean.setQuantity(quantity);
					localBean.setPrice(price);
					localBean.setLineCurrency(lineCurrency);
					localBean.setLineTotal(lineTotal);
				}
												
		}
		}		return localBean;
	}
		
	
	
	@Override
	public String createInvoiceHeader(InvoiceBean invoice) {
		
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
	public List<InvoiceLineItem> getAllLineItems() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				InvoiceHeader.class);
		return (List<InvoiceLineItem>) criteria.list();
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
     
}

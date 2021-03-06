package za.co.fenya.demo.dao.Impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import za.co.fenya.demo.bean.InvoiceBean;
import za.co.fenya.demo.bean.ReadingBean;
import za.co.fenya.demo.dao.AccessoriesDaoInt;
import za.co.fenya.demo.dao.CustomerDaoInt;
import za.co.fenya.demo.dao.CustomerDeviceHistoryDaoInt;
import za.co.fenya.demo.dao.DeviceContactPersonDaoInt;
import za.co.fenya.demo.dao.DeviceDaoInt;
import za.co.fenya.demo.dao.EmployeeDaoInt;
import za.co.fenya.demo.dao.InvoiceDaoInt;
import za.co.fenya.demo.dao.ModelNumbersMasterDaoInt;
import za.co.fenya.demo.dao.ReadingDaoInt;
import za.co.fenya.demo.dao.SiteStocDaoInt;
import za.co.fenya.demo.dao.TicketsDaoInt;
import za.co.fenya.demo.model.Accessories;
import za.co.fenya.demo.model.Customer;
import za.co.fenya.demo.model.Device;
import za.co.fenya.demo.model.DeviceContactPerson;
import za.co.fenya.demo.model.Employee;
import za.co.fenya.demo.model.ModelNumbers;
import za.co.fenya.demo.model.Reading;
import za.co.fenya.demo.model.SiteStock;
import za.co.fenya.demo.model.TicketHistory;
import za.co.fenya.demo.model.Tickets;
import za.co.fenya.demo.reports.initializer.DeviceReportBean;
import za.co.fenya.demo.service.TicketHistoryInt;



@Repository("productDAO")
@Transactional(propagation=Propagation.REQUIRED)
public class DeviceDao implements DeviceDaoInt {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private CustomerDaoInt customerDaoInt;
	@Autowired
	private AccessoriesDaoInt accessoriesDaoInt;
	@Autowired
	DeviceContactPersonDaoInt contactPersonDaoInt;
	@Autowired
	ReadingDaoInt readingDaoInt;
	@Autowired
	InvoiceDaoInt invoiceDaoInt;
	@Autowired
	private EmployeeDaoInt employeeDao;
	@Autowired
	private SiteStocDaoInt siteStocDaoInt;
	@Autowired
	private TicketsDaoInt ticketsDaoInt;
	@Autowired
	private TicketHistoryInt ticketHistoryInt;
	@Autowired
	private HttpSession session = null;
	@Autowired
	private CustomerDeviceHistoryDaoInt deviceHistoryDaoInt;
	@Autowired
	private ModelNumbersMasterDaoInt modelNumbersMasterDaoInt;
	
	private String retMessage = null;
	ArrayList<Device> productList = null;
	List<Device> deviceList = null; 
	ArrayList<?> aList = null;
	ArrayList<Accessories> list = null;
	Customer customer = null;
	Device device = null;

	Employee userName, emp = null;
	CustomerDeviceHistoryBean historyBean = null;
	TicketHistory ticketHistory = null;
	private ModelNumbers modelNum = null;
	List<Accessories> accessoryList = null;
	private DeviceBean deviceBean = null;
	private ReadingBean readingBean = null;
	private DeviceContactPerson contactPerson;
	Device localdevice = null;
	Accessories accesories = null;
	DateFormat dateFormat = null;
	Date date = null;
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String timeDeviceAdded = sdfDate.format(now);
	@Override
	public String saveDevice(Device device) {
		
		emp = (Employee) session.getAttribute("loggedInUser");
		try {
			 localdevice = getDeviceBySerialNumbuer(device.getSerialNumber());
			if (localdevice == null) {
				historyBean = new CustomerDeviceHistoryBean();
				//Prepare Device Data for History Table
				historyBean.setAction("Create");
				historyBean.setClassification("Device");
				historyBean.setObjectId(device.getSerialNumber());
				historyBean.setUserEmail(emp.getEmail());
				historyBean.setUserName(emp.getFirstName() + " " + emp.getLastName());
				historyBean.setDescription("Initial create of Device");
				device.setDateTime(timeDeviceAdded);			
				sessionFactory.getCurrentSession().saveOrUpdate(device);
				System.err.println("Device History is inserted into DB when installing machine for client");
				deviceHistoryDaoInt.saveCustomerDeviceHistory(historyBean);
				retMessage = "Device "
						+ device.getSerialNumber()
						+ " succefully added. The device belongs to customer : "
						+ device.getCustomerDevice().getCustomerName()+".";

			}else{
				retMessage = "Device "+ localdevice.getSerialNumber()+ " already assigned to customer " +localdevice.getCustomerDevice().getCustomerName()+". One device canot be assigned twice to a customer.";
			}
		} catch (Exception e) {
			retMessage = "Device " + device.getSerialNumber()
					+ " not added\n" + e.getMessage()+".";
		}

		return retMessage;

	}

	@Override
	public Device getDeviceBySerialNumbuer(String serialNumber) {

		return (Device) sessionFactory.getCurrentSession().get(Device.class,
				serialNumber);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getDeviceList() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Device.class);
		return (List<Device>) criteria.list();
	}
	@Override
	public List<Accessories> accessories(Device device) {
		/*
		 * ArrayList list = new ArrayList<Accessories>(); Accessories accessory
		 * = new Accessories();
		 */
		return list;
	}

	@Override
	public String updateDevice(Device device,DeviceBean deviceBean) {
		
		historyBean = new CustomerDeviceHistoryBean();
		
		emp = (Employee) session.getAttribute("loggedInUser");
		
		try {
	
			historyBean = new CustomerDeviceHistoryBean();
			//Prepare Device Data for History Table
			historyBean.setAction("Update");
			historyBean.setClassification("Device");
			historyBean.setObjectId(device.getSerialNumber());
			historyBean.setUserEmail(emp.getEmail());
			historyBean.setUserName(emp.getFirstName() + " " + emp.getLastName());
			historyBean.setDescription(deviceBean.getDescription());
			sessionFactory.getCurrentSession().update(device);			
			System.err.println("Device History is inserted into DB on Update");
			deviceHistoryDaoInt.saveCustomerDeviceHistory(historyBean);
			device.setDateTime(timeDeviceAdded);
			retMessage = "Device " + device.getSerialNumber()
					+ " is successfully updated. Device belongs to customer : "
					+ device.getCustomerDevice().getCustomerName()+".";
		} catch (Exception e) {
			retMessage = "Device " + device.getSerialNumber()
					+ " not updated\n" + e.getMessage();
		}
		return retMessage;
	}

	@Override
	public String prepareDeviceData(DeviceBean deviceBean) {
		// Purpose : This method prepares the device data before inserting into
		// the table
		String retAccessory = null;
		customer = new Customer();
		contactPerson = new DeviceContactPerson();
		device = new Device();
		String oldSerial =null;
		historyBean = new CustomerDeviceHistoryBean();
		readingBean = new ReadingBean();
		emp = (Employee) session.getAttribute("loggedInUser");
		try {

			if(deviceBean.getChkAccessories()==null){
				device.setEndDate(deviceBean.getEndDate());
				
				modelNum = modelNumbersMasterDaoInt.getModelNumbersMaster(deviceBean.getModelNumber());
				if(modelNum != null){
					device.setModelNumber(deviceBean.getModelNumber());
				}else{
					
					retMessage =  deviceBean.getModelNumber()
							+ " does not exist on database. Please make sure that the model number exist.";
					return retMessage;
					
				}
				device.setSerialNumber(deviceBean.getSerialNumber());
				device.setStartDate(deviceBean.getStartDate());
				device.setInstallationDate(deviceBean.getInstallationDate());
				device.setModelBrand(deviceBean.getModelBrand());
				device.setDateTime(timeDeviceAdded);
				device.setMonoReading(deviceBean.getMonoReading());
				device.setColourReading(deviceBean.getColourReading());
				device.setMonoCopyCost(deviceBean.getMonoCopyCost());
				device.setColourCopyCost(deviceBean.getColourCopyCost());

				device.setBuildingName(deviceBean.getBuildingName());
				device.setFloorNumber(deviceBean.getFloorNumber());
				device.setAreaCode(deviceBean.getZipcode());
				device.setCity_town(deviceBean.getCity_town());
				device.setProvince(deviceBean.getProvince());
				device.setStreetName(deviceBean.getStreetName());
				device.setStreetNumber(deviceBean.getStreetNumber());

				contactPerson.setEmail(deviceBean.getEmail());
				contactPerson.setFirstName(deviceBean.getFirstName());
				contactPerson.setLastName(deviceBean.getLastName());
				contactPerson.setCellphone(deviceBean.getCellphone());
				contactPerson.setTelephone(deviceBean.getTelephone());
				//get description on updating
				historyBean.setDescription(deviceBean.getDescription());
				
				customer = customerDaoInt.getClientByClientName(deviceBean.getCustomerName());
				
				
				//Default Readings
				if (deviceBean != null)
				{
					
					readingBean.setCustomerName(deviceBean.getCustomerName());
					readingBean.setSerialNumber(deviceBean.getSerialNumber());
					readingBean.setColorReading(deviceBean.getColourReading());
					readingBean.setMonoReading(deviceBean.getMonoReading());
					readingBean.setPreviousColorReading(deviceBean.getColourReading());
					readingBean.setPreviousMonoReading(deviceBean.getMonoReading());
					readingBean.setEmployee(emp.getEmail());
					retMessage = readingDaoInt.createDefaultReading(readingBean);
				}
				
				if (customer != null) {
					device.setCustomerDevice(customer);

					list = new ArrayList<Accessories>();
					Accessories accessory = new Accessories();
					// addTypeserial
					if (deviceBean.getAddTypeserial() != null) {
						if (deviceBean.getAddTypeserial().length() > 3) {
							oldSerial = deviceBean.getAddTypeserial().replace(",", "");
							//newSerial = oldSerial.substring( 0, oldSerial.indexOf(","));
							accessory.setSerial(oldSerial);
							accessory.setAccessotyType("Additional Paper Trays");
							accessory.setDevice(device);
							list.add(accessory);
						}
					}
					if (deviceBean.getBridgeUnitSerialTypeSerialNo() != null) {
						if (deviceBean.getBridgeUnitSerialTypeSerialNo().length() > 3) {

							Accessories accessory1 = new Accessories();
							
							oldSerial = deviceBean.getBridgeUnitSerialTypeSerialNo().replace(",", "");
							//newSerial = oldSerial.substring( 0, oldSerial.indexOf(","));
							accessory1.setSerial(oldSerial);
							accessory1.setAccessotyType("Bridge Unit");
							accessory1.setDevice(device);
							list.add(accessory1);
						}
					}
					// creTypeserial
					if (deviceBean.getCreTypeserial() != null) {
						if (deviceBean.getCreTypeserial().length() > 3) {

							Accessories accessory2 = new Accessories();
							oldSerial = deviceBean.getCreTypeserial().replace(",", "");
							//newSerial = oldSerial.substring( 0, oldSerial.indexOf(","));
							accessory2.setSerial(oldSerial);
							accessory2.setAccessotyType("Credenza");
							accessory2.setDevice(device);
							list.add(accessory2);
						}

					}

					if (deviceBean.getFaxUnitSerialTypeSerialNo() != null) {
						if (deviceBean.getFaxUnitSerialTypeSerialNo().length() > 3) {
							Accessories accessory3 = new Accessories();
							
							oldSerial = deviceBean.getFaxUnitSerialTypeSerialNo().replace(",", "");
							//newSerial = oldSerial.substring( 0, oldSerial.indexOf(","));
							accessory3.setSerial(oldSerial);
							accessory3.setAccessotyType("Fax Unit");
							accessory3.setDevice(device);
							list.add(accessory3);
						}
					}

					if (deviceBean.getFinisherTypeSerialNo() != null) {
						if (deviceBean.getFinisherTypeSerialNo().length() > 3) {

							Accessories accessory4 = new Accessories();
							oldSerial = deviceBean.getFinisherTypeSerialNo().replace(",", "");
							//newSerial = oldSerial.substring( 0, oldSerial.indexOf(","));
							accessory4.setSerial(oldSerial);
							accessory4.setAccessotyType("Finisher");
							accessory4.setDevice(device);
							list.add(accessory4);
						}
					}

					if (deviceBean.getLtcTypeSerial() != null) {
						if (deviceBean.getLtcTypeSerial().length() > 3) {

							Accessories accessory5 = new Accessories();
							oldSerial = deviceBean.getLtcTypeSerial().replace(",", "");
							//newSerial = oldSerial.substring( 0, oldSerial.indexOf(","));
							accessory5.setSerial(oldSerial);
							accessory5.setAccessotyType("LCT");
							accessory5.setDevice(device);
							list.add(accessory5);
						}
					}

					if (deviceBean.getOneBinTrayTypeSerialNo() != null) {
						if (deviceBean.getOneBinTrayTypeSerialNo().length() > 3) {

							Accessories accessory6 = new Accessories();
							oldSerial = deviceBean.getOneBinTrayTypeSerialNo().replace(",", "");
							//newSerial = oldSerial.substring( 0, oldSerial.indexOf(","));
							accessory6.setSerial(oldSerial);
							accessory6.setAccessotyType("One Bin Tray");
							accessory6.setDevice(device);
							list.add(accessory6);
						}
					}
					if(deviceBean.getMachineType().length()>3 && deviceBean.getSerialNumberOtherAccessory().length()>3){
						List<String> accessoryType = new ArrayList<String>(Arrays.asList(deviceBean.getMachineType().split(",")));
						List<String> accessorySerial = new ArrayList<String>(Arrays.asList(deviceBean.getSerialNumberOtherAccessory().split(",")));
						for(int i =0;i<accessoryType.size();i++){
							for(int x=0;x<accessorySerial.size();x++){
								if(i==x){
									Accessories otherAccessorry = new Accessories();
									otherAccessorry.setAccessotyType(accessoryType.get(i));
									otherAccessorry.setSerial(accessorySerial.get(x));
									otherAccessorry.setDevice(device);
									list.add(otherAccessorry);
								}
							}
						}
					}
					retMessage = contactPersonDaoInt
							.saveContactPerson(contactPerson);
					if (retMessage.equalsIgnoreCase("OK")) {

						device.setContactPerson(contactPerson);
						if(deviceBean.getUpdateFlag()== "YES")
						{
							retMessage= updateDevice(device,deviceBean);
							if(retMessage.startsWith("Device " + device.getSerialNumber()
									+ " is successfully updated.")){
								if(list.size()>0){
									retAccessory = accessoriesDaoInt.saveAccessories(list);
									if (retAccessory.equalsIgnoreCase("Error")) {
										retMessage = "Device not inserted into the table "
												+ retAccessory+".";
									}
								}
							}
						}else if(deviceBean.getUpdateFlag()==null){
							retMessage = saveDevice(device);						
							if(retMessage.startsWith("Device "+ device.getSerialNumber()+ " succefully added.")){
								retAccessory = accessoriesDaoInt.saveAccessories(list);
								if (retAccessory.equalsIgnoreCase("Error")) {
									retMessage = "Device not inserted into the table "
											+ retAccessory+".";
								}
							}
						}

					} else {
						retMessage = "Contact person cannot be captured"
								+ retMessage+".";
					}
				} else {
					retMessage = "Customer "
							+ customer.getCustomerName()
							+ " does not exist on database. Please make sure that the customer exist before assigning a device.";
				}
			}else{
				retMessage = accessoriesDaoInt.removeAccessory(deviceBean.getChkAccessories(), deviceBean);
				
			}

		} catch (Exception e) {
			retMessage = e.getMessage();
		}
		
		
		return retMessage;
	}

	@Override
	public DeviceBean getAccessoriesForUpdate(Long recordID) {

		deviceBean = new DeviceBean();
		try {

			/*accessoryList = accessoriesDaoInt
					.getAccessoriesByDeviceSerial(serialNumber);
			for (Accessories access : accessoryList) {
				if (access.getDevice().getSerialNumber().equalsIgnoreCase(serialNumber)) {
					if (access.getAccessotyType().equalsIgnoreCase(
							"Bridge Unit")) {
						deviceBean.setBridgeUnitSerialTypeSerialNo(access
								.getSerial());
						;
					} else if (access.getAccessotyType().equalsIgnoreCase(
							"Fax Unit")) {
						deviceBean.setFaxUnitSerialTypeSerialNo(access
								.getSerial());
					} else if (access.getAccessotyType().equalsIgnoreCase(
							"Credenza")) {
						deviceBean.setCreTypeserial(access.getSerial());
					} else if (access.getAccessotyType().equalsIgnoreCase(
							"Finisher")) {
						deviceBean.setFinisherTypeSerialNo(access.getSerial());
					} else if (access.getAccessotyType().equalsIgnoreCase(
							"Additional Paper Trays")) {
						deviceBean.setAddTypeserial(access.getSerial());
					} else if (access.getAccessotyType()
							.equalsIgnoreCase("LTC")) {
						deviceBean.setLtcTypeSerial(access.getSerial());
					} else if (access.getAccessotyType().equalsIgnoreCase(
							"One bin tray")) {
						deviceBean
								.setOneBinTrayTypeSerialNo(access.getSerial());
					}
				}
			}
		*/} catch (Exception e) {
			return null;
		}
		return deviceBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getDeviceList(Integer offset, Integer maxResults,
			String clientName) {
		List<Device> dev = sessionFactory.openSession()
				.createCriteria(Device.class, clientName)
				.setFirstResult(offset != null ? offset : 0)
				.setMaxResults(maxResults != null ? maxResults : 10).list();
		return dev;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getDeviceListByClientName(String clientName) {
		String name = clientName;
		try {

			aList = new ArrayList<Object>();
			productList = new ArrayList<Device>();
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(Device.class);
			aList.addAll(criteria.list());
			for (Object pro : aList) {
				if (pro instanceof Device) {
					if (((Device) pro).getCustomerDevice().getCustomerName() != null
							&& ((Device) pro).getCustomerDevice().getCustomerName()
									.startsWith(name)) {
						productList.add((Device) pro);
						customer = ((Device) pro).getCustomerDevice();
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return productList;
	}

	@Override
	public Integer count() {
		return (Integer) sessionFactory.getCurrentSession()
				.createCriteria(Customer.class)
				.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public String[] getSerials() {
		List<Device> list = null;
		ArrayList<String> newList = null;
		String array[] = null;
		try {
			list = getDeviceList();
			newList = new ArrayList<String>();

			for (Device device : list) {
				newList.add(device.getSerialNumber());
			}

			array = new String[newList.size()];

			for (int i = 0; i < newList.size(); i++) {
				array[i] = newList.get(i);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return array;
	}
	

	@Override
	public List<TicketHistory> getHistoryByTicketNumber(Long ticketNumber) {
		
		List<TicketHistory> newList = null;
		try{
			
			List<TicketHistory> list = getAllTicketHistoryByTicketNumber();
			 newList = new ArrayList<TicketHistory>();
			for(TicketHistory ticketHistory:list){
				if(ticketHistory.getTicketNo().equals(ticketNumber)){
					newList.add(ticketHistory);
				}
			}
			
		}catch(Exception e){
			e.getMessage();
		}
		return newList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TicketHistory> getAllTicketHistoryByTicketNumber() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				TicketHistory.class);
		return (List<TicketHistory>) criteria.list();	
	}
	
	@Override
	public Tickets getLoggedTicketsByTicketNumber(Long ticketNumber) {

		return (Tickets) sessionFactory.getCurrentSession().get(Tickets.class,
				ticketNumber);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TicketHistory> getAllTicketHistoryByTicketNumber(Long recordID) {
		return (List<TicketHistory>) sessionFactory.getCurrentSession().get(TicketHistory.class,
				recordID);
	}
		

	@Override
	public String replaceToner(String compitableSiteStock,
			String currentMonoReading, String currentColourReading,
			String firstName, String lastName, String loggedInUser,String contactEmail,
			String contactTelephoneNumber,String contactCellNumber,String description, String serialNumber) {
		
		    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    date = new Date();
		    Employee employee = null;
		    SiteStock siteStock = null;
		    Tickets ticket = new Tickets();
		    int tempQuantity  =0;
		try{
			String [] splitSerials = compitableSiteStock.split(",");
			
			List<String> serialNumbers = Arrays.asList(splitSerials);
			
			for(String tempSerial: serialNumbers){
				device = getDeviceBySerialNumbuer(serialNumber);
				if(device != null){
					employee = (Employee) session.getAttribute("loggedInUser");
					//employee =employeeDao.getEmployeeByEmpNum(contactEmail);
					if(employee != null){
						
						siteStock = siteStocDaoInt.getSiteStock(tempSerial, device.getCustomerDevice().getCustomerName());
						if(siteStock!= null){
							tempQuantity = siteStock.getQuantity() - 1;
							siteStock.setQuantity(tempQuantity);
							sessionFactory.getCurrentSession().update(siteStock);
							
							device.setMonoReading(currentMonoReading);
							device.setColourReading(currentColourReading);
							sessionFactory.getCurrentSession().update(device);
							
							ticketHistory = new TicketHistory();
							ticketHistory.setActionTaken("Replace Toner");
							ticketHistory.setStatus("Closed");
							ticketHistory.setEscalatedDate(dateFormat.format(date));
							ticketHistory.setMonoReading(currentMonoReading);
							ticketHistory.setColourReading(currentColourReading);
							ticketHistory.setEmployee(employee);
							ticketHistory.setComment(description);
																					
							ticket.setActionTaken("Replace Toner");
							ticket.setStatus("Closed");
							ticket.setDateTime(dateFormat.format(date));
							ticket.setDateResolved(dateFormat.format(date));
							ticket.setDevice(device);
							ticket.setUsedPartNumbers(compitableSiteStock);
							ticket.setEmployee(employee);
							ticket.setTicketLoggedBy(loggedInUser);
							ticket.setDescription(description);
							ticket.setFirstName(firstName);
							ticket.setLastName(lastName);
							ticket.setContactTelephoneNumber(contactTelephoneNumber);
							ticket.setContactCellNumber(contactCellNumber);
							ticket.setContactEmail(contactEmail);
							ticket.setComments("Toner Replaced");
							
							Long RecordID = ticketsDaoInt.logTicketForTonnerReplacement(ticket);
							ticketHistory.setTicketNo(RecordID);
							sessionFactory.getCurrentSession().save(ticketHistory);
							retMessage = "Toner Replaced successfully";
						}
					}
					else{
						retMessage = "Replace Toner could not be processed";
					}	
				}
			}
			
		}catch(Exception ex){
			retMessage= ex.getMessage();
		}
		return retMessage;
	}

	@Override
	public JRDataSource getDeviceListDataSource() {
		JRDataSource ds = null;
		List<DeviceReportBean> result = new ArrayList<DeviceReportBean>();
		try{
			deviceList = getDeviceList();
			for(Device device:deviceList){
				DeviceReportBean deviceBean = new DeviceReportBean();				
				deviceBean.setCustomerName(device.getCustomerDevice().getCustomerName());
				deviceBean.setSerialNumber(device.getSerialNumber());
				deviceBean.setModelNumber(device.getModelNumber());
				deviceBean.setModelBrand(device.getModelBrand());
				deviceBean.setAddress(device.getStreetNumber()+" "+device.getStreetName()+" "+ device.getCity_town()+", "+device.getProvince()+" "+device.getAreaCode());
				result.add(deviceBean);
				ds = new JRBeanCollectionDataSource(result);
			}
		}catch(Exception e){
			e.getMessage();
		}
		return ds;
	}
	
	
}

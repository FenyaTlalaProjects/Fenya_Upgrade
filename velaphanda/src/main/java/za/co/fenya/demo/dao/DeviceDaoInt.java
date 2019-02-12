package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.bean.DeviceBean;
import za.co.fenya.demo.model.Accessories;
import za.co.fenya.demo.model.Device;
import za.co.fenya.demo.model.TicketHistory;
import za.co.fenya.demo.model.Tickets;
import net.sf.jasperreports.engine.JRDataSource;


public interface DeviceDaoInt {
	
	String saveDevice(Device device);
	Device getDeviceBySerialNumbuer(String serialNumber);
	List<Device> getDeviceList();
	List<Device> getDeviceList(Integer offset, Integer maxResults,String clientName);
	List<Device> getDeviceListByClientName(String clientName);
	List<Accessories> accessories(Device device);
	String updateDevice(Device device,DeviceBean deviceBean);
	String prepareDeviceData(DeviceBean deviceBean);
	DeviceBean getAccessoriesForUpdate(Long recordID);
	Integer count();
	String[] getSerials();
	String replaceToner(String compitableSiteStock,String currentMonoReading,String currentColourReading,String firstName,String lastName,String loggedInUser,String contactEmail,String contactTelephoneNumber,String contactCellNumber,String description,String serialNumber
			);
	JRDataSource getDeviceListDataSource();
	List<TicketHistory> getHistoryByTicketNumber(Long ticketNumber);
	List<TicketHistory> getAllTicketHistoryByTicketNumber();
	List<TicketHistory> getAllTicketHistoryByTicketNumber(Long recordID);
	Tickets getLoggedTicketsByTicketNumber(Long ticketNumber);
}

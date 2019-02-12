package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.bean.DeviceBean;
import za.co.fenya.demo.model.Accessories;
import net.sf.jasperreports.engine.JRDataSource;


public interface AccessoriesInt {
	String saveAccessories(List<Accessories> accessories);
	String updateAccessories(List<Accessories> accessories);
	List<Accessories> getAccessoriesByDeviceSerial(String serialNumber);
	Accessories getAccessories(Long recordID);
	List<String> getAccessoriesList(String deviceSerialNumber);
	String removeAccessory(String []serialNumbers,DeviceBean deviceBean);
	JRDataSource getAccessoriesByDeviceSerialDataSource(String serialNumber);

}

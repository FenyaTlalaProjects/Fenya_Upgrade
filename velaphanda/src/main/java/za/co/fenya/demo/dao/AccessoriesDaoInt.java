package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.bean.DeviceBean;
import za.co.fenya.demo.model.Accessories;
import net.sf.jasperreports.engine.JRDataSource;


public interface AccessoriesDaoInt {
	
	String saveAccessories(List<Accessories> accessories);
	String updateAccessories(List<Accessories> accessories);
	List<Accessories> getAccessoriesByDeviceSerial(String serialNumber);
	String removeAccessory(String[] strings,DeviceBean deviceBean);
	Accessories getAccessories(Long recordID);
	List<String> getAccessoriesList(String deviceSerialNumber);
	JRDataSource getAccessoriesByDeviceSerialDataSource(String serialNumber);
}

package za.co.fenya.demo.service.impl;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.bean.DeviceBean;
import za.co.fenya.demo.dao.AccessoriesDaoInt;
import za.co.fenya.demo.model.Accessories;
import za.co.fenya.demo.service.AccessoriesInt;



@Service("accessoriesService")
public class AccessoriesService implements AccessoriesInt{
	
	@Autowired
	private AccessoriesDaoInt accessoriesDaoInt;
	private String retMessage = null;

	@Override
	public String saveAccessories(List<Accessories> accessories) {
		
		retMessage = accessoriesDaoInt.saveAccessories(accessories);
		return retMessage;
	}

	@Override
	public String updateAccessories(List<Accessories> accessories) {
		retMessage = accessoriesDaoInt.updateAccessories(accessories);
		return retMessage;
	}

	@Override
	public List<Accessories> getAccessoriesByDeviceSerial(String recordID) {
		
		return accessoriesDaoInt.getAccessoriesByDeviceSerial(recordID);
	}

	@Override
	public Accessories getAccessories(Long recordID) {

		return accessoriesDaoInt.getAccessories(recordID);
	}

	@Override
	public List<String> getAccessoriesList(String deviceSerialNumber) {
		return accessoriesDaoInt.getAccessoriesList(deviceSerialNumber);
	}
	@Override
	public String removeAccessory(String[]serialNumbers,DeviceBean deviceBean) {
		return accessoriesDaoInt.removeAccessory(serialNumbers,deviceBean);
	}

	@Override
	public JRDataSource getAccessoriesByDeviceSerialDataSource(
			String serialNumber) {
		return accessoriesDaoInt.getAccessoriesByDeviceSerialDataSource(serialNumber);
	}

}

package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.bean.DeviceBean;
import za.co.fenya.demo.bean.ReadingBean;
import za.co.fenya.demo.model.Accessories;
import za.co.fenya.demo.model.Device;
import net.sf.jasperreports.engine.JRDataSource;


public interface ReadingServiceInt {
	

	String createReading(ReadingBean reading);
}

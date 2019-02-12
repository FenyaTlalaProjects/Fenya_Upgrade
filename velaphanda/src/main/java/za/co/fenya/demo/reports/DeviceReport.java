package za.co.fenya.demo.reports;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import za.co.fenya.demo.dao.DeviceDaoInt;
import za.co.fenya.demo.dao.TicketHistoryDaoInt;
import za.co.fenya.demo.service.AccessoriesInt;
import za.co.fenya.demo.service.DeviceServiceInt;
import za.co.fenya.demo.service.TicketHistoryInt;
import za.co.fenya.demo.service.TicketsServiceInt;



@Controller
public class DeviceReport {
	
	@Autowired
	private DeviceDaoInt deviceDaoInt;
	@Autowired
	private DeviceServiceInt deviceServiceInt;
	@Autowired
	private TicketHistoryInt ticketHistoryInt;
	@Autowired
	private TicketHistoryDaoInt ticketHistoryDaoInt;
	@Autowired
	private TicketsServiceInt ticketsServiceInt;
	@Autowired
	private AccessoriesInt accessoryInt;
	
    @RequestMapping(value = "/deviceDetailsDownloadPDF", method = RequestMethod.GET)
    public ModelAndView doDeviceDetailsReportPDF(@RequestParam("serialNumber") String serialNumber) 
		 {
		ModelAndView modelAndView = null;
		// Retrieve our data from a custom data provider
		// Our data comes from a DAO layer
		
		// Assign the datasource to an instance of JRDataSource
		// JRDataSource is the datasource that Jasper understands
		// This is basically a wrapper to Java's collection classes
		JRDataSource deviceDetails  = accessoryInt.getAccessoriesByDeviceSerialDataSource(serialNumber);
		
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("deviceDetailsDatasource", deviceDetails);
		
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/deviceDetails-views.xml
		modelAndView = new ModelAndView("deviceDetailsPdfReport", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}
    
    @RequestMapping(value = "/deviceListDownloadPDF", method = RequestMethod.GET)
    public ModelAndView doDeviceListReportPDF() 
		 {
		ModelAndView modelAndView = null;
		// Retrieve our data from a custom data provider
		// Our data comes from a DAO layer
		
		// Assign the datasource to an instance of JRDataSource
		// JRDataSource is the datasource that Jasper understands
		// This is basically a wrapper to Java's collection classes
		JRDataSource deviceList  = deviceDaoInt.getDeviceListDataSource();
		
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("deviceListDatasource",deviceList);
		
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/deviceList-views.xml
		modelAndView = new ModelAndView("deviceListPdfReport", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}
    
    @RequestMapping(value = "/deviceHistoryDownloadPDF", method = RequestMethod.GET)
    public ModelAndView doDeviceHistoryReportPDF(@RequestParam("recordID")Long recordID) 
		 {
		ModelAndView modelAndView = null;
		// Retrieve our data from a custom data provider
		// Our data comes from a DAO layer
		
		// Assign the datasource to an instance of JRDataSource
		// JRDataSource is the datasource that Jasper understands
		// This is basically a wrapper to Java's collection classes
		JRDataSource deviceHistory  = ticketHistoryDaoInt.getDeviceHistoryDataSource(recordID);
		
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("deviceHistoryDatasource", deviceHistory);
		
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/deviceHistory-views.xml
		modelAndView = new ModelAndView("deviceHistoryPdfReport", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}

}

package za.co.fenya.demo.reports;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

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
	@Autowired
	private ApplicationContext appContext;
	
    @RequestMapping(value = "/deviceDetailsDownloadPDF", method = RequestMethod.GET)
    public ModelAndView doDeviceDetailsReportPDF(@RequestParam("serialNumber") String serialNumber){
    	    	
    	 JasperReportsPdfView view = new JasperReportsPdfView();
         //get the source
    	 JRDataSource deviceDetails  = accessoryInt.getAccessoriesByDeviceSerialDataSource(serialNumber);
 		
 		 //Device Details report path 
         view.setUrl("classpath:deviceDetails.jrxml");
         view.setApplicationContext(appContext);

         Map<String, Object> params = new HashMap<>();
         params.put("datasource", deviceDetails);

         return new ModelAndView(view, params);    	
    	
	}
    
    @RequestMapping(value = "/deviceListDownloadPDF", method = RequestMethod.GET)
    public ModelAndView doDeviceListReportPDF() {
		
		 JasperReportsPdfView view = new JasperReportsPdfView();
         //get the source
		 JRDataSource deviceList  = deviceDaoInt.getDeviceListDataSource();
			
 		 //Device List report path 
         view.setUrl("classpath:deviceList.jrxml");
         view.setApplicationContext(appContext);

         Map<String, Object> params = new HashMap<>();
         params.put("datasource", deviceList);

         return new ModelAndView(view, params); 
		
	}
    
    @RequestMapping(value = "/deviceHistoryDownloadPDF", method = RequestMethod.GET)
    public ModelAndView doDeviceHistoryReportPDF(@RequestParam("recordID")Long recordID){
    	
		 JasperReportsPdfView view = new JasperReportsPdfView();
         //get the source
		 JRDataSource deviceHistory  = ticketHistoryDaoInt.getDeviceHistoryDataSource(recordID);
				
 		 //Device History report path 
         view.setUrl("classpath:deviceHistory.jrxml");
         view.setApplicationContext(appContext);

         Map<String, Object> params = new HashMap<>();
         params.put("datasource", deviceHistory);

         return new ModelAndView(view, params); 
	}

}

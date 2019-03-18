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

import za.co.fenya.demo.dao.CustomerContactDetailsDaoInt;
import za.co.fenya.demo.dao.CustomerDaoInt;
import za.co.fenya.demo.service.CustomerContactDetailsServiceInt;
import za.co.fenya.demo.service.CustomerServiceInt;


@Controller
public class CustomerReport {
	
	
	@Autowired
	private CustomerDaoInt custIntDao;
	@Autowired
	private CustomerServiceInt customerServiceInt;
	@Autowired
	private CustomerContactDetailsServiceInt contactDetailsServiceInt;
	@Autowired
	private CustomerContactDetailsDaoInt customerContactDetailsDaoIntDaoInt;
	@Autowired
	private ApplicationContext appContext;
	   
    @RequestMapping(value = "/customerListDownloadPDF", method = RequestMethod.GET)
    public ModelAndView customerListDownloadPDF(){
    	
    	 JasperReportsPdfView view = new JasperReportsPdfView();
         //get the source
    	 JRDataSource customerList  = custIntDao.getCustomerListDataSource();
 		 //Customer List report path 
         view.setUrl("classpath:customerList.jrxml");
         view.setApplicationContext(appContext);

         Map<String, Object> params = new HashMap<>();
         params.put("datasource", customerList);

         return new ModelAndView(view, params);
    	
	}
    
    @RequestMapping(value = "/viewCustomerDownloadPDF", method = RequestMethod.GET)
    public ModelAndView viewCustomerReportPDF(@RequestParam("customerName") String customerName){
		
    	 JasperReportsPdfView view = new JasperReportsPdfView();
         //get the source
    	 JRDataSource contactList  = contactDetailsServiceInt.getCustomerContactDetailsDataSource(customerName);
 		 JRDataSource viewCustomer  = custIntDao.getCustomerDetailsDataSource(customerName);
 		 //View customer report path 
         view.setUrl("classpath:viewCustomer.jrxml");
         view.setApplicationContext(appContext);

         Map<String, Object> params = new HashMap<>();
         params.put("datasource", viewCustomer);
         params.put("datasource", contactList);

         return new ModelAndView(view, params);
    	
    	
	}
    
}

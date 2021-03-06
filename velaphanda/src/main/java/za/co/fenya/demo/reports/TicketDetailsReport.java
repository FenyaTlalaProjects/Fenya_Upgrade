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

import za.co.fenya.demo.service.TicketHistoryInt;
import za.co.fenya.demo.service.TicketsServiceInt;




@Controller
public class TicketDetailsReport {
	
	@Autowired
	private TicketsServiceInt ticketsServiceInt;
	@Autowired
	private ApplicationContext appContext;
	
    @RequestMapping(value = "/ticketDownloadPDF", method = RequestMethod.GET)
    public ModelAndView doTicketDetailsReportPDF(@RequestParam("recordID") Long recordID){
    	    	
    	 JasperReportsPdfView view = new JasperReportsPdfView();
         //get the source
    	 JRDataSource ticketDetails  = ticketsServiceInt.getTicketDetailsDataSource(recordID);
 		
 		 //Ticket Details report path 
         view.setUrl("classpath:ticketDetails.jrxml");
         view.setApplicationContext(appContext);

         Map<String, Object> params = new HashMap<>();
         params.put("datasource", ticketDetails);

         return new ModelAndView(view, params);
    	
	}

}

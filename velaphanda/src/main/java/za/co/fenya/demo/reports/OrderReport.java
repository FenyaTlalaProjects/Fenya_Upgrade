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

import za.co.fenya.demo.dao.OrderDetailsDaoInt;



@Controller
public class OrderReport {
	
   @Autowired
   private OrderDetailsDaoInt detailsDaoInt;
   @Autowired
   private ApplicationContext appContext;
   
   @RequestMapping(path = "/orderDownloadPDF", method = RequestMethod.GET)
   public ModelAndView report(@RequestParam("recordID") Long recordID) {

       JasperReportsPdfView view = new JasperReportsPdfView();
       //get the source
       JRDataSource orderDetails = detailsDaoInt.getOrderDetailsDataSource(recordID);
	   //order report path 
       view.setUrl("classpath:deliveryNote.jrxml");
       view.setApplicationContext(appContext);

       Map<String, Object> params = new HashMap<>();
       params.put("datasource", orderDetails);

       return new ModelAndView(view, params);
   }
   
   
}

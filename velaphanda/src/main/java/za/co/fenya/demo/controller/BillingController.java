package za.co.fenya.demo.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import za.co.fenya.demo.bean.CustomerBean;
import za.co.fenya.demo.bean.InvoiceBean;
import za.co.fenya.demo.model.Customer;
import za.co.fenya.demo.model.Device;
import za.co.fenya.demo.model.Employee;
import za.co.fenya.demo.service.AccessoriesInt;
import za.co.fenya.demo.service.CustomerContactDetailsServiceInt;
import za.co.fenya.demo.service.CustomerDeviceHistoryServiceInt;
import za.co.fenya.demo.service.CustomerServiceInt;
import za.co.fenya.demo.service.DeviceServiceInt;
import za.co.fenya.demo.service.ModelNumbersMasterServiceInt;
import za.co.fenya.demo.service.OrdersServiceInt;
import za.co.fenya.demo.service.TicketsServiceInt;




@Controller
public class BillingController {
	
	@Autowired
	private CustomerServiceInt customerServiceInt;
	@Autowired
	private DeviceServiceInt deviceServiceInt;
	@Autowired
	private HttpSession session;
	private String retMessage = null;
	ModelAndView model = null;
	List<Device> deviceList =null;
	Customer customer = null;
	Employee userName = null;
	private String customerName, technicianName, technicianEmail,
	selectedDateRange, heading, machineType = null;
	public String[] getSerialNumbers = null;
	
	
	//create billing management pages
	@RequestMapping(value={"billingmanagement","userbillingmanagement"},method=RequestMethod.GET)
	public ModelAndView displayBillingPage(String customerName){
		selectedDateRange = "Select Date";
		model= new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if(userName != null){
		
			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("billingmanagement");
			
		   }else if(userName.getRole().equalsIgnoreCase("User")){			   
			   model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
			   model.setViewName("userbillingmanagement");			   
		   }
		}
		else{
			model.setViewName("login");
		}
		return model;
	}
	
	//Create invoice page
	@RequestMapping(value={"createInvoice","usercreateinvoice"},method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView displayCreateInvoice(@ModelAttribute("createInvoice")String customerName,InvoiceBean invoiceBean){
		model= new ModelAndView();
		selectedDateRange = "Select Date";
		userName = (Employee) session.getAttribute("loggedInUser");
		if(userName != null){
		
			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
				
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());	
				model.addObject("newDate", selectedDateRange);
				model.setViewName("createInvoice");
			
		   }else if(userName.getRole().equalsIgnoreCase("User")){
			   getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
			   model.setViewName("usercreateinvoice");			   
		   }
		}
		else{
			model.setViewName("login");
		}
		return model;
	}
	
}

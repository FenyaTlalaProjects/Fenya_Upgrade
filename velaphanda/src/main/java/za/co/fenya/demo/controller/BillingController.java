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
	List<Device> deviceList = null;
	Customer customer = null;
	Employee userName = null;
	private String customerName, technicianName, technicianEmail, selectedDateRange, heading, machineType = null;
	public String[] getSerialNumbers = null;

	// create billing management pages
	@RequestMapping(value = { "billingmanagement", "userbillingmanagement" }, method = RequestMethod.GET)
	public ModelAndView displayBillingPage(String customerName) {
		selectedDateRange = "Select Date";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {

			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("billingmanagement");

			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("userbillingmanagement");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// create statements page
	@RequestMapping(value = { "statements", "userstatements" }, method = RequestMethod.GET)
	public ModelAndView displayStatementPage(String customerName) {
		selectedDateRange = "Select Date";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {

			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("statements");

			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("userstatements");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// create invoice page
	@RequestMapping(value = { "invoice", "userInvoice" }, method = RequestMethod.GET)
	public ModelAndView displayInvoicePage(String customerName) {
		selectedDateRange = "Select Date";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {

			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("invoice");

			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("userInvoice");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}
	
	//select customer reading page
	@RequestMapping(value = { "customerReadings", "userCustomerReadings" }, method = RequestMethod.GET)
	public ModelAndView displayCustomersPage(String customerName) {
	    	selectedDateRange = "Select Date";
			model = new ModelAndView();
			userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {

			if(userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
					model.addObject("customerName", customerName);
					model.addObject("customers", customerServiceInt.getClientList());
					model.addObject("newDate", selectedDateRange);
					model.setViewName("customerReadings");
			}else if (userName.getRole().equalsIgnoreCase("User")) {
					model.addObject("customerName", customerName);
					model.addObject("customers", customerServiceInt.getClientList());
					model.addObject("newDate", selectedDateRange);
					model.setViewName("userCustomerReadings");
			   }
			}else{
			   model.setViewName("login");
			}
			return model;
	}
	
		
	@RequestMapping(value={"searchCustomerName","userSearchCustomerName"})
	public ModelAndView searchCustomer(@RequestParam("customerName") String customerName) {
		String selectedName = customerName;
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if(userName != null){
			if(userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))){
			getSerialNumbers = deviceServiceInt.getSerials();
			model.addObject("serialNumbers", getSerialNumbers);
			model.addObject("customerName", customerName);
			model.addObject("selectedName", selectedName);
			model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
			if(customerName !=null){
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("custName", customerName);
							
			}else{
				model.addObject("errorRetMessage", "Customer name does not exist.");
			}
			model.setViewName("customerReadings");
		}else if(userName.getRole().equalsIgnoreCase("User")){
			getSerialNumbers = deviceServiceInt.getSerials();
			model.addObject("serialNumbers", getSerialNumbers);
			model.addObject("customerName", customerName);
			model.addObject("selectedName", selectedName);
			model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
			if(customerName !=null){
				model.addObject("custName", customerName);
			}else{
				model.addObject("errorRetMessage", "Customer name does not exist.");
			}
			model.setViewName("userCustomerReadings");
		  }
		}
		else{
			model.setViewName("login");
		}		
		return model;
	}

	// create invoice page
	@RequestMapping(value = { "SLA", "userSLA" }, method = RequestMethod.GET)
	public ModelAndView displaySLAPage(String customerName) {
		selectedDateRange = "Select Date";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {

			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("SLA");

			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("userSLA");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// Create invoice page
	@RequestMapping(value = { "readings", "usercreateinvoice" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayCreateInvoice(@ModelAttribute("createInvoice") String customerName,
			InvoiceBean invoiceBean) {
		model = new ModelAndView();
		selectedDateRange = "Select Date";
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {

			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("readings");

			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("usercreateinvoice");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// Create invoice page
	@RequestMapping(value = { "readingsCustomerByDevice", "userCreateInvoiceCustomerByDevice" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayDeviceByCusCreateInvoice(@RequestParam("customerName") String customerName,
			@ModelAttribute InvoiceBean invoiceBean) {
		model = new ModelAndView();
		selectedDateRange = "Select Date";
		String selectedName = customerName;

		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {

			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {

				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("readingsCustomerByDevice");

			} else if (userName.getRole().equalsIgnoreCase("User")) {

				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("userCreateInvoiceCustomerByDevice");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

}

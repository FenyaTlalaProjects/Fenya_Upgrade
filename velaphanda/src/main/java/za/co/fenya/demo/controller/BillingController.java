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
import za.co.fenya.demo.bean.ReadingBean;
import za.co.fenya.demo.bean.SparePartsBean;
import za.co.fenya.demo.model.Accessories;
import za.co.fenya.demo.model.Customer;
import za.co.fenya.demo.model.Device;
import za.co.fenya.demo.model.Employee;
import za.co.fenya.demo.model.Invoice;
import za.co.fenya.demo.model.Reading;
import za.co.fenya.demo.model.Tickets;
import za.co.fenya.demo.service.AccessoriesInt;
import za.co.fenya.demo.service.CustomerContactDetailsServiceInt;
import za.co.fenya.demo.service.CustomerDeviceHistoryServiceInt;
import za.co.fenya.demo.service.CustomerServiceInt;
import za.co.fenya.demo.service.DeviceServiceInt;
import za.co.fenya.demo.service.InvoiceServiceInt;
import za.co.fenya.demo.service.ModelNumbersMasterServiceInt;
import za.co.fenya.demo.service.OrdersServiceInt;
import za.co.fenya.demo.service.ReadingServiceInt;
import za.co.fenya.demo.service.TicketsServiceInt;

@Controller
public class BillingController {

	@Autowired
	private CustomerServiceInt customerServiceInt;
	@Autowired
	private DeviceServiceInt deviceServiceInt;
	@Autowired
	private ReadingServiceInt deviceReadingServiceInt;
	@Autowired
	private InvoiceServiceInt deviceInvoiceServiceInt;
	@Autowired
	private HttpSession session;

	private String retMessage = null;
	ModelAndView model = null;
	List<Device> deviceList = null;
	Customer customer = null;
	Employee userName = null;
	private String customerName, technicianName, technicianEmail, selectedDateRange, heading, machineType = null;
	public String[] getSerialNumbers = null;
	Reading getReadings = new Reading();
	Invoice getInvocie = new Invoice();
	Reading readings = null;
	private String globalCustomerName = null;

	// readings management page
	@RequestMapping(value = { "readingsmanagement", "userreadingsmanagement" }, method = RequestMethod.GET)
	public ModelAndView displayBillingPage() {
		heading = "All Readings";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				model.addObject("heading", heading);
				model.addObject("deviceReadingList", deviceReadingServiceInt.getAllReadings());
				model.setViewName("readingsmanagement");

			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("heading", heading);
				model.addObject("deviceReadingList", deviceReadingServiceInt.getAllReadings());
				model.setViewName("userreadingsmanagement");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// invoice management page
	@RequestMapping(value = { "invoicemanagement", "userinvoicemanagement" }, method = RequestMethod.GET)
	public ModelAndView displayInvoiceMangement() {
		heading = "All Invoices";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				model.addObject("heading", heading);
				model.addObject("deviceReadingList", deviceReadingServiceInt.getAllReadings());
				model.setViewName("invoicemanagement");

			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("heading", heading);
				model.addObject("deviceReadingList", deviceReadingServiceInt.getAllReadings());
				model.setViewName("userinvoicemanagement");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// invoice management page
	@RequestMapping(value = "invoicemanagement", params = { "newCustomer" }, method = RequestMethod.GET)
	public ModelAndView displayInvoiceMangementNew() {
		heading = "All Invoices";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				model.addObject("heading", heading);
				model.addObject("deviceReadingList", deviceReadingServiceInt.getAllReadings());
				model.setViewName("invoicemanagement");

			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("heading", heading);
				model.addObject("deviceReadingList", deviceReadingServiceInt.getAllReadings());
				model.setViewName("userinvoicemanagement");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// SLA management page
	@RequestMapping(value = { "slamanagement", "userslamanagement" }, method = RequestMethod.GET)
	public ModelAndView displaySLAPage(String customerName) {
		selectedDateRange = "Select Date";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {

			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("slamanagement");

			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("userslamanagement");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// statements management page
	@RequestMapping(value = { "statementsmanagement", "userstatementsmanagement" }, method = RequestMethod.GET)
	public ModelAndView displayStatementPage(String customerName) {
		selectedDateRange = "Select Date";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {

			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("statementsmanagement");

			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.setViewName("userstatementsmanagement");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// select customer reading page
	@RequestMapping(value = { "customerReadings", "userCustomerReadings" }, method = RequestMethod.GET)
	public ModelAndView displayCheckReadingPage() {

		selectedDateRange = "Select Date";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {

			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.setViewName("customerReadings");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.setViewName("userCustomerReadings");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// display searched customer and its serial numbers
	@RequestMapping(value = { "readingsCustomerByDevice", "userReadingsCustomerByDevice" }, method = RequestMethod.GET)
	public ModelAndView displaySearchedCustomer(String customerName) {
		model = new ModelAndView();
		String selectedName = customerName;
		userName = (Employee) session.getAttribute("loggedInUser");

		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.setViewName("customerReadings");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.setViewName("userCustomerReadings");
			}
		} else {
			model.setViewName("login");
		}
		return model;

	}

	// search reading for a client
	@RequestMapping(value = { "searchCustomerReading", "userSearchCustomerReading" }, method = RequestMethod.POST)
	public ModelAndView searchCustomer(@RequestParam("customerName") String customerName,
			@RequestParam("serialNumber") String serialNumber, @RequestParam("period") String period,
			ReadingBean reading) {
		String selectedName = customerName;
		String selectedSerialNumber = serialNumber;
		String selectedPeriod = period;
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				getSerialNumbers = deviceServiceInt.getSerials();
				reading.setEmployee(userName.getEmail());
				reading.setReadingPeriod(selectedPeriod);
				getReadings = deviceReadingServiceInt.createReading(reading);
				if (getReadings != null) {
					model.addObject("readingBean", getReadings);
					System.err.println("Moleko...................................");
				}
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("selectedPeriod", selectedPeriod);
				model.addObject("selectedSerialNumber", selectedSerialNumber);
				// model.addObject("retMessage",
				// deviceReadingServiceInt.createDefaultReading(reading));
				// model.addObject("deviceList",
				// deviceServiceInt.getDeviceListByClientName(customerName));
				if (customerName != null) {
					model.addObject("customers", customerServiceInt.getClientList());
					model.addObject("custName", customerName);

				} else {
					model.addObject("errorRetMessage", "Customer name does not exist.");
				}

				model.setViewName("customerReadings");

			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				getReadings = deviceReadingServiceInt.createReading(reading);
				if (getReadings != null) {
					model.addObject("deviceReading", getReadings);
				}
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("selectedPeriod", selectedPeriod);
				model.addObject("selectedSerialNumber", selectedSerialNumber);
				model.addObject("readingBean", reading);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				// model.addObject("retMessage",
				// deviceReadingServiceInt.createDefaultReading(reading));
				if (customerName != null) {
					model.addObject("custName", customerName);
				} else {
					model.addObject("errorRetMessage", "Customer name does not exist.");
				}
				model.setViewName("userCustomerReadings");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// read current readings
	@RequestMapping(value = { "readReadings", "userReadReadings" })
	public ModelAndView readReadings(@RequestParam("customerName") String customerName,
			@RequestParam("serialNumber") String serialNumber, @RequestParam("period") String period) {
		String selectedName = customerName;
		String selectedSerialNumber = serialNumber;
		String selectedPeriod = period;

		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {

				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("selectedPeriod", selectedPeriod);
				model.addObject("serialNo", serialNumber);
				model.addObject("selectedSerialNumber", selectedSerialNumber);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				// getReadings = deviceReadingServiceInt.createReading(reading);
				if (getReadings != null) {
					model.addObject("deviceReading", getReadings);
				}
				if (customerName != null) {
					model.addObject("customers", customerServiceInt.getClientList());
					model.addObject("custName", customerName);
					model.addObject("serialNo", serialNumber);
					model.addObject("selectedSerialNumber", selectedSerialNumber);
					model.addObject("deviceReading", getReadings);
				} else {
					model.addObject("errorRetMessage", "Customer name does not exist.");
				}
				model.setViewName("customerReadings");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceReading", getReadings);
				model.addObject("selectedPeriod", selectedPeriod);
				model.addObject("serialNo", serialNumber);
				model.addObject("selectedSerialNumber", selectedSerialNumber);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				/// getReadings =
				/// deviceReadingServiceInt.getPreviousReadingForDevice(serialNumber);
				if (getReadings != null) {
					model.addObject("deviceReading", getReadings);
				}
				if (customerName != null) {
					model.addObject("customers", customerServiceInt.getClientList());
					model.addObject("custName", customerName);
					model.addObject("serialNo", serialNumber);
					model.addObject("deviceReading", getReadings);
					model.addObject("selectedSerialNumber", selectedSerialNumber);
				} else {
					model.addObject("errorRetMessage", "Customer name does not exist.");
				}
				model.setViewName("userCustomerReadings");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// save reading
	@RequestMapping(value = "captureReadings", params = { "Save" }, method = RequestMethod.POST)
	public ModelAndView saveReading(@ModelAttribute("captureReadings") ReadingBean reading) {
		model = new ModelAndView();
		String saveReadings = "saveReadings";
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				reading.setEmployee(userName.getEmail());
				model.addObject("saveReadings", saveReadings);
				model.addObject("retMessage", deviceReadingServiceInt.saveReading(reading));
				model.setViewName("confirmations");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("saveReadings", saveReadings);
				model.addObject("retMessage", deviceReadingServiceInt.saveReading(reading));
				model.setViewName("confirm");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// submit reading
	@RequestMapping(value = "captureReadings", params = { "Submit" }, method = RequestMethod.POST)
	public ModelAndView submitReading(@ModelAttribute("captureReadings") ReadingBean reading) {
		model = new ModelAndView();
		String submitReadings = "submitReadings";
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				reading.setEmployee(userName.getEmail());
				model.addObject("retMessage", deviceReadingServiceInt.submitReading(reading));
				model.addObject("submitReadings", submitReadings);
				model.setViewName("confirmations");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("retMessage", deviceReadingServiceInt.submitReading(reading));
				model.addObject("submitReadings", submitReadings);
				model.setViewName("confirm");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	//capture invoice
	@RequestMapping(value = "captureInvoice", params = { "SaveInvoice" }, method = RequestMethod.POST)
	public ModelAndView saveInvoice(@ModelAttribute("captureInvoice") ReadingBean reading,Invoice invoice) {
		model = new ModelAndView();
		String saveInvoice = "saveInvoice";
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				reading.setEmployee(userName.getEmail());
				model.addObject("saveInvoice", saveInvoice);
				//getInvocie = deviceInvoiceServiceInt.saveInvoice(invoice);
				model.setViewName("confirmations");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("saveInvoice", saveInvoice);
				//getInvocie = deviceInvoiceServiceInt.saveInvoice(invoice);
				model.setViewName("confirm");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// submit invoice
	@RequestMapping(value = "captureInvoice", params = { "SubmitInvoice" }, method = RequestMethod.POST)
	public ModelAndView submitInvoice(@ModelAttribute("captureInvoice") ReadingBean reading,Invoice invoice) {
		model = new ModelAndView();
		String submitInvoice = "submitInvoice";
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				reading.setEmployee(userName.getEmail());
				//getInvocie = deviceInvoiceServiceInt.submitInvoice(invoice);
				model.addObject("submitInvoice", submitInvoice);
				model.setViewName("confirmations");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				//getInvocie = deviceInvoiceServiceInt.submitInvoice(invoice);
				model.addObject("submitInvoice", submitInvoice);
				model.setViewName("confirm");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// read and display list of captured readings
	@RequestMapping(value = { "capturedReadings" }, method = RequestMethod.GET)
	public ModelAndView displayCapturedReading(String serialNumber) {
		String selectedName = customerName;
		heading = "Captured";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceReadingList", deviceReadingServiceInt.getAllReadings());
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("billingmanagement");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("userBillingmanagement");

			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// read and display list of pending readings
	@RequestMapping(value = { "pendingReadings" }, method = RequestMethod.GET)
	public ModelAndView displayPendingReadings(String serialNumber) {
		String selectedName = customerName;
		heading = "Pending";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("billingmanagement");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("userBillingmanagement");

			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// create invoice page
	@RequestMapping(value = { "invoice", "userinvoice" }, method = RequestMethod.GET)
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
				model.setViewName("userinvoice");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// display searched customer and its serial numbers on invoice
	@RequestMapping(value = { "invoiceCustomerByDevice", "userinvoiceCustomerByDevice" }, method = RequestMethod.GET)
	public ModelAndView displaySearchCustomer(String customerName) {
		model = new ModelAndView();
		String selectedName = customerName;
		userName = (Employee) session.getAttribute("loggedInUser");

		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || userName.getRole().equalsIgnoreCase("Admin")) {
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.setViewName("invoice");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customers", customerServiceInt.getClientList());
				model.addObject("newDate", selectedDateRange);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.setViewName("userinvoice");
			}
		} else {
			model.setViewName("login");
		}
		return model;

	}

	// search available reading for a client to display when creating invoice
	@RequestMapping(value = { "invoiceSearchCustomerReading",
			"userInvoiceSearchCustomerReading" }, method = RequestMethod.POST)
	public ModelAndView searchCustomerReadingForInvoice(@RequestParam("customerName") String customerName,
			@RequestParam("serialNumber") String serialNumber, @RequestParam("period") String period,
			ReadingBean reading, InvoiceBean invoice) {
		String selectedName = customerName;
		String selectedSerialNumber = serialNumber;
		String selectedPeriod = period;
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				getSerialNumbers = deviceServiceInt.getSerials();
				reading.setEmployee(userName.getEmail());
				reading.setReadingPeriod(selectedPeriod);
				getReadings = deviceReadingServiceInt.createReading(reading);
				// getInvocie = deviceInvoiceServiceInt.createInvoiceHeader(invoice);
				if (getReadings != null) {
					model.addObject("readingBean", getReadings);
					System.err.println("Moleko...................................");
				}
				if (getInvocie != null) {
					model.addObject("invoiceBean", getInvocie);
					System.err.println("Re lata existing invoice...................................");
				}
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("selectedPeriod", selectedPeriod);
				model.addObject("selectedSerialNumber", selectedSerialNumber);
				// model.addObject("retMessage",
				// deviceReadingServiceInt.createDefaultReading(reading));
				// model.addObject("deviceList",
				// deviceServiceInt.getDeviceListByClientName(customerName));
				if (customerName != null) {
					model.addObject("customers", customerServiceInt.getClientList());
					model.addObject("custName", customerName);

				} else {
					model.addObject("errorRetMessage", "Customer name does not exist.");
				}

				model.setViewName("invoice");

			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				getReadings = deviceReadingServiceInt.createReading(reading);
				if (getReadings != null) {
					model.addObject("deviceReading", getReadings);
				}
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("selectedPeriod", selectedPeriod);
				model.addObject("selectedSerialNumber", selectedSerialNumber);
				model.addObject("readingBean", reading);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				// model.addObject("retMessage",
				// deviceReadingServiceInt.createDefaultReading(reading));
				if (customerName != null) {
					model.addObject("custName", customerName);
				} else {
					model.addObject("errorRetMessage", "Customer name does not exist.");
				}
				model.setViewName("userinvoice");
			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// read and display list of captured invoice
	@RequestMapping(value = { "capturedinvoice" }, method = RequestMethod.GET)
	public ModelAndView displayCapturedInvoice(String serialNumber) {
		String selectedName = customerName;
		heading = "Captured";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceReadingList", deviceReadingServiceInt.getAllReadings());
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("invoicemanagement");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("userinvoicemanagement");

			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// read and display list of pending invoice
	@RequestMapping(value = { "pendinginvoice" }, method = RequestMethod.GET)
	public ModelAndView displaypendinginvoice(String serialNumber) {
		String selectedName = customerName;
		heading = "Pending";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("invoicemanagement");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("userinvoicemanagement");

			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// read and display list of paid invoice
	@RequestMapping(value = { "paidinvoice" }, method = RequestMethod.GET)
	public ModelAndView displaypaidinvoice(String serialNumber) {
		String selectedName = customerName;
		heading = "Paid";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("invoicemanagement");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("userinvoicemanagement");

			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// read and display list of Approved invoice
	@RequestMapping(value = { "approvedinvoice" }, method = RequestMethod.GET)
	public ModelAndView displayApprovedinvoice(String serialNumber) {
		String selectedName = customerName;
		heading = "Approved";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("invoicemanagement");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("userinvoicemanagement");

			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// read and display list of Deleted invoice
	@RequestMapping(value = { "deletedinvoice" }, method = RequestMethod.GET)
	public ModelAndView displaydeletedinvoice(String serialNumber) {
		String selectedName = customerName;
		heading = "Deleted";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("invoicemanagement");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("userinvoicemanagement");

			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// read and display list of sent invoice
	@RequestMapping(value = { "sentinvoice" }, method = RequestMethod.GET)
	public ModelAndView displaysentinvoice(String serialNumber) {
		String selectedName = customerName;
		heading = "Sent";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("invoicemanagement");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("userinvoicemanagement");

			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// read and display list of draft invoice
	@RequestMapping(value = { "draftinvoice" }, method = RequestMethod.GET)
	public ModelAndView displaydraftinvoice(String serialNumber) {
		String selectedName = customerName;
		heading = "Draft";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("invoicemanagement");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("userinvoicemanagement");

			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

	// read and display list of partial invoice
	@RequestMapping(value = { "partialinvoice" }, method = RequestMethod.GET)
	public ModelAndView displaypartialinvoice(String serialNumber) {
		String selectedName = customerName;
		heading = "Partial";
		model = new ModelAndView();
		userName = (Employee) session.getAttribute("loggedInUser");
		if (userName != null) {
			if (userName.getRole().equalsIgnoreCase("Manager") || (userName.getRole().equalsIgnoreCase("Admin"))) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("invoicemanagement");
			} else if (userName.getRole().equalsIgnoreCase("User")) {
				getSerialNumbers = deviceServiceInt.getSerials();
				model.addObject("serialNumbers", getSerialNumbers);
				model.addObject("customerName", customerName);
				model.addObject("selectedName", selectedName);
				model.addObject("deviceList", deviceServiceInt.getDeviceListByClientName(customerName));
				model.addObject("deviceReading", getReadings);
				model.addObject("heading", heading);
				model.setViewName("userinvoicemanagement");

			}
		} else {
			model.setViewName("login");
		}
		return model;
	}

}

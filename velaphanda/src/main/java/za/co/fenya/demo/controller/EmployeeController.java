package za.co.fenya.demo.controller;


import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import za.co.fenya.demo.dao.Impl.PasswordEncrypt;
import za.co.fenya.demo.model.Employee;
import za.co.fenya.demo.model.LoginAttempt;
import za.co.fenya.demo.model.UserLogDetails;
import za.co.fenya.demo.service.CredentialsServiceInt;
import za.co.fenya.demo.service.EmployeeServiceInt;



@Controller
public class EmployeeController {

	@Autowired
	private EmployeeServiceInt employeeServiceInt;
	@Autowired
	private EmployeeServiceInt employeeService;
	@Autowired
	private CredentialsServiceInt credentialsServiceInt;
	
	@Autowired
	private HttpSession session;
	/*@Autowired
	private TicketsServiceInt ticketsServiceInt;
	@Autowired
	private OrdersServiceInt ordersServiceInt;
	@Autowired
	private UserLogDetailsServiceInt userLogDetailsServiceInt;
	
	
	@Autowired
	private LoginAttemptServiceInt serviceInt;*/
	
	String retMessage =null;
	ModelAndView model = null;
	Employee userName = null;
	private UserLogDetails details;
	private Employee techName;
	
	private LoginAttempt loginAttempt;
	String retPage = null;
	Integer count = 1;
	
	@RequestMapping({"/login", "/"})
	public ModelAndView loadLogin()
	{
		model = new ModelAndView();
		details = new UserLogDetails();
		
		try{
		
			String sessioID = (String) session.getAttribute("sessionID");
			//userLogDetailsServiceInt.updateTimeout(sessioID);
			session.invalidate();
		}catch(Exception e){
			e.getMessage();
		}
		model.setViewName("login");
		return model;
	}
	@RequestMapping(value="authenticate",method={ RequestMethod.POST,RequestMethod.GET})
	public ModelAndView authenticateLogin(@ModelAttribute("authenticate")Employee employee,@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout)
	{
		ModelAndView model = new ModelAndView();
		String userName = employee.getEmail();
		String password = employee.getPassword();
		long numberOfDays = 0L;
		
		employee = employeeService.getEmployeeByEmpNumber(employee.getEmail());
	    password = PasswordEncrypt.encryptPassword(password);
		if(employee != null&& employee.getStatus().equalsIgnoreCase("ACTIVE")){
			session.setAttribute("loggedInUser", employee);
			String user= employee.getFirstName()+" " + employee.getLastName();
			String userEmail = employee.getEmail();
			session.setAttribute("user", user);
			session.setAttribute("userEmail", userEmail);
		
			if(employee.isFirstTimeLogin()==true && employee.getEmail().equals(userName)&& employee.getPassword().equals(password)){
				model.setViewName("changePassword");
			}else{
				
				numberOfDays = credentialsServiceInt.passwordDateDifference(userName);
				
				if(numberOfDays > 65 && numberOfDays < 75){
					int noDays = (int) (75 - numberOfDays);
					String message = "Your password is about to expire in "+ noDays + " days, Please reset your password now";
					model.addObject("message", message);
					model.addObject("noDays",noDays);
					model.setViewName("passwordExpired");
				}else if(numberOfDays >= 75){					
					model.setViewName("changePassword");
				}
				else{
					String approver = employee.getEmail();
					model.addObject("loggedInUser", employee.getEmail());
					model.addObject("approver", approver);
					if(employee.getRole().equalsIgnoreCase("ADMIN")&& employee.getEmail().equals(userName)&& employee.getPassword().equals(password)||
							employee.getRole().equalsIgnoreCase("Manager") && employee.getEmail().equals(userName)&& employee.getPassword().equals(password)){
						String userSessionID =session.getId();
						
						session.setAttribute("sessionID", userSessionID);
						if(details !=null){
							/*userLogDetailsServiceInt.getLastUserLogDetails(userEmail);
							userLogDetailsServiceInt.saveUserLogDetails(details);*/
						}
						/*model.addObject("openTickets", ticketsServiceInt.getTicketCount("Open", "Last 14 Days", "", "", 0L));						
						model.addObject("countAcknowledgedTickets", ticketsServiceInt.getTicketCount("Acknowledged", "Last 14 Days", "", "",0L));
						model.addObject("countTakenTickets", ticketsServiceInt.getTicketCount("Taken", "Last 14 Days", "", "",0L));
						model.addObject("escalatedTickets", ticketsServiceInt.getTicketCount("Escalated", "Last 14 Days", "", "", 0L));
						model.addObject("awaitingSparesTickets", ticketsServiceInt.getTicketCount("Awaiting Spares", "Last 14 Days", "", "", 0L));
						model.addObject("bridgedTickets", ticketsServiceInt.getTicketCount("SLA Bridged", "Last 14 Days", "", "", 0L));
						model.addObject("resolvedTickets", ticketsServiceInt.getTicketCount("Resolved", "Last 14 Days", "", "", 0L));
						model.addObject("closedTickets", ticketsServiceInt.getTicketCount("Closed", "Last 14 Days", "", "", 0L));
						serviceInt.userLoggeIn(employee);*/
						model.setViewName("home");
					}
					else if(employee.getRole().equalsIgnoreCase("TECHNICIAN") && employee.getEmail().equals(userName)&& employee.getPassword().equals(password))
					{
						String userSessionID =session.getId();
						session.setAttribute("sessionID", userSessionID);
						if(details !=null){
							//userLogDetailsServiceInt.saveUserLogDetails(details);
						}
						//serviceInt.userLoggeIn(employee);
													
						/*model.addObject("openTickets", ticketsServiceInt.getTicketCountForTechnicianDashbord("Open", "Last 14 Days", employee.getEmail() , "", 0L));
						model.addObject("countAcknowledgedTickets", ticketsServiceInt.getTicketCountForTechnicianDashbord("Acknowledged", "Last 14 Days", employee.getEmail(), "",0L));
						model.addObject("countTakenTickets", ticketsServiceInt.getTicketCountForTechnicianDashbord("Taken", "Last 14 Days", employee.getEmail() , "",0L));
						model.addObject("escalatedTickets", ticketsServiceInt.getTicketCountForTechnicianDashbord("Escalated", "Last 14 Days", employee.getEmail(), "", 0L));
						model.addObject("awaitingSparesTickets", ticketsServiceInt.getTicketCountForTechnicianDashbord("Awaiting Spares", "Last 14 Days", employee.getEmail(), "", 0L));
						model.addObject("bridgedTickets", ticketsServiceInt.getTicketCountForTechnicianDashbord("SLA Bridged", "Last 14 Days", employee.getEmail(), "", 0L));
						model.addObject("resolvedTickets", ticketsServiceInt.getTicketCountForTechnicianDashbord("Resolved", "Last 14 Days", employee.getEmail(), "", 0L));
						model.addObject("closedTickets", ticketsServiceInt.getTicketCountForTechnicianDashbord("Closed", "Last 14 Days", employee.getEmail(), "", 0L));
										 */
						model.setViewName("technicianHome");
												
					}
					else if(employee.getRole().equalsIgnoreCase("USER") && employee.getEmail().equals(userName)&& employee.getPassword().equals(password))
					{
						String userSessionID =session.getId();
						session.setAttribute("sessionID", userSessionID);
						if(details!=null){
							//userLogDetailsServiceInt.saveUserLogDetails(details);
						}
						/*
						serviceInt.userLoggeIn(employee);
						model.addObject("openTickets", ticketsServiceInt.getTicketCount("Open", "Last 14 Days", "", "", 0L));
						model.addObject("countAcknowledgedTickets", ticketsServiceInt.getTicketCount("Acknowledged", "Last 14 Days", "", "",0L));
						model.addObject("countTakenTickets", ticketsServiceInt.getTicketCount("Taken", "Last 14 Days", "", "",0L));
						model.addObject("escalatedTickets", ticketsServiceInt.getTicketCount("Escalated", "Last 14 Days", "", "", 0L));
						model.addObject("awaitingSparesTickets", ticketsServiceInt.getTicketCount("Awaiting Spares", "Last 14 Days", "", "", 0L));
						model.addObject("bridgedTickets", ticketsServiceInt.getTicketCount("SLA Bridged", "Last 14 Days", "", "", 0L));
						model.addObject("resolvedTickets", ticketsServiceInt.getTicketCount("Resolved", "Last 14 Days", "", "", 0L));
						model.addObject("closedTickets", ticketsServiceInt.getTicketCount("Closed", "Last 14 Days", "", "", 0L));
					*/
						model.setViewName("userdashboard");
					}else{
						
						
						/*loginAttempt = serviceInt.getEmployeeDetails(employee);
						serviceInt.upsertUserAttempt(loginAttempt);*/
						if(loginAttempt.getAttemptCount()==1){
							String attempMessage = "Invalid password.You have 2 attempts left";
							model.addObject("attempMessage", attempMessage);
							
							model.setViewName("loginattempted");
						}
						else if(loginAttempt.getAttemptCount()==2){
							String attempMessage = "Invalid password.You have 1 attempts left";
							model.addObject("attempMessage", attempMessage);
							model.setViewName("loginattempted");
						}
						else if(loginAttempt.getAttemptCount()>=3){
							String attempMessage = "Your account is blocked after "+ loginAttempt.getAttemptCount() + " wrong attempts. Please consult your manager for password reset";
							model.addObject("attempMessage", attempMessage);
							model.addObject("count", loginAttempt.getAttemptCount());
							
							model.setViewName("lastAttemptLogin");
						}
					}
				}
			
			}
			
		}else if(employee != null&& employee.getStatus().equalsIgnoreCase("BLOCKED")){
			//retRole= "redirect:logioutnattempted";
			model.addObject("attempMessage", "Your acount has been BLOCKED after 3 wrong attempts, please consult your manager for password reset");
			model.setViewName("loginattempted");
		}
		else{//retRole= "redirect:error";
			System.out.println("Failed to login"+ userName+ "and password is "+ password);
			   model.setViewName("error");
		}	
		return model;
	}

}

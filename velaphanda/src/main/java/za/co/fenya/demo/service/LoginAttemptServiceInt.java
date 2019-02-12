package za.co.fenya.demo.service;

import za.co.fenya.demo.model.Employee;
import za.co.fenya.demo.model.LoginAttempt;


public interface LoginAttemptServiceInt {

	void upsertUserAttempt(LoginAttempt userLoginAttempt);
	LoginAttempt getLoginUser(String userName);
	LoginAttempt getEmployeeDetails(Employee employee);
	void userLoggeIn(Employee employee);
}

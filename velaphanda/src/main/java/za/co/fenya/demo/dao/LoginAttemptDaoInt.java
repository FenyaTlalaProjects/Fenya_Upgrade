package za.co.fenya.demo.dao;

import za.co.fenya.demo.model.Employee;
import za.co.fenya.demo.model.LoginAttempt;

public interface LoginAttemptDaoInt {

	
	void upsertUserAttempt(LoginAttempt userLoginAttempt);
	LoginAttempt getLoginUser(String userName);
	LoginAttempt getEmployeeDetails(Employee employee);
	void userLoggeIn(Employee employee);
}

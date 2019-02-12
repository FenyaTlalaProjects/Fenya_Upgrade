package za.co.fenya.demo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.dao.LoginAttemptDaoInt;
import za.co.fenya.demo.model.Employee;
import za.co.fenya.demo.model.LoginAttempt;
import za.co.fenya.demo.service.LoginAttemptServiceInt;



@Service("loginAttemptService")
@Transactional
public class LoginAttemptService implements LoginAttemptServiceInt{
	
	@Autowired
	private LoginAttemptDaoInt loginAttemptDaoInt;

	@Override
	public void upsertUserAttempt(LoginAttempt userLoginAttempt) {
		
		loginAttemptDaoInt.upsertUserAttempt(userLoginAttempt);
	}

	@Override
	public LoginAttempt getLoginUser(String userName) {
		
		return loginAttemptDaoInt.getLoginUser(userName);
	}

	@Override
	public LoginAttempt getEmployeeDetails(Employee employee) {
		return loginAttemptDaoInt.getEmployeeDetails(employee);
	}

	@Override
	public void userLoggeIn(Employee employee) {
		loginAttemptDaoInt.userLoggeIn(employee);
		
	}
}

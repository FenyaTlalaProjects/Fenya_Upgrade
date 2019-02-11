package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.model.UserLogDetails;

public interface UserLogDetailsDaoInt {

	String saveUserLogDetails(UserLogDetails details);
	List<UserLogDetails> getLogoutDateTime();
	List<UserLogDetails> getUserLogDetails();
	String lougoutTimeStamp();
	String updateTimeout(String sessionID);
	UserLogDetails getUserLogDetails(String sessionID);
	String getLastUserLogDetails(String userEmail);
	List<UserLogDetails> userActivities(String email);
}

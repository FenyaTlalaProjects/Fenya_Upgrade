package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.model.UserLogDetails;


public interface UserLogDetailsServiceInt {
	
	void saveUserLogDetails(UserLogDetails details);
	void updateTimeout(String sessionID);
	UserLogDetails getUserLogDetails(String sessionID);
	List<UserLogDetails> userActivities(String email);
	void getLastUserLogDetails(String userEmail);

}

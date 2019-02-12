package za.co.fenya.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.dao.UserLogDetailsDaoInt;
import za.co.fenya.demo.model.UserLogDetails;
import za.co.fenya.demo.service.UserLogDetailsServiceInt;


@Service("userLogDetailsService")
@Transactional
public class UserLogDetailsService implements UserLogDetailsServiceInt{
	
	@Autowired
	private UserLogDetailsDaoInt userLogDetailsDao;

	@Override
	public void saveUserLogDetails(UserLogDetails details) {
		try{
			userLogDetailsDao.saveUserLogDetails(details);
			  
		}catch(Exception e){
			e.getMessage();
		}
		
	}

	@Override
	public void updateTimeout(String sessionID) {
		userLogDetailsDao.updateTimeout(sessionID);
	}

	@Override
	public UserLogDetails getUserLogDetails(String sessionID) {
		return userLogDetailsDao.getUserLogDetails(sessionID);
	}

	@Override
	public List<UserLogDetails> userActivities(String email) {
		return userLogDetailsDao.userActivities(email);
	}

	@Override
	public void getLastUserLogDetails(String userEmail) {
		
		userLogDetailsDao.getLastUserLogDetails(userEmail);
	}

}

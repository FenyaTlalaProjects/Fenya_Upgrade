package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.model.User;

public interface UserDao {

    User findById(int id);
	
	User findBySSO(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();
}

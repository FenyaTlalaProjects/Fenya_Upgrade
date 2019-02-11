package za.co.fenya.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.dao.UserDao;
import za.co.fenya.demo.model.User;
import za.co.fenya.demo.service.UserService;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserDao dao;
	
	
	@Override
	public User findById(int id) {
	
		return dao.findById(id);
	}

	@Override
	public User findBySSO(String sso) {
		
		return dao.findBySSO(sso);
	}

	@Override
	public void saveUser(User user) {
		dao.save(user);
		
	}

	@Override
	public void updateUser(User user) {
		
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setSsoId(user.getSsoId());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserDocuments(user.getUserDocuments());
		}
	}

	@Override
	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
		
	}

	@Override
	public List<User> findAllUsers() {
	
		return dao.findAllUsers();
	}

	@Override
	public boolean isUserSSOUnique(Integer id, String sso) {
		
		User user = findBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

}

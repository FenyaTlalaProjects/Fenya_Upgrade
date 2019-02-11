package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.model.UserDocument;

public interface UserDocumentDao {

    List<UserDocument> findAll();
	
	UserDocument findById(int id);
	
	void save(UserDocument document);
	
	List<UserDocument> findAllByUserId(int userId);
	
	void deleteById(int id);
}

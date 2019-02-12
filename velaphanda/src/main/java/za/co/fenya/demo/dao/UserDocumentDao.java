package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.model.CustomerDocument;

public interface UserDocumentDao {

    List<CustomerDocument> findAll();
	
	CustomerDocument findById(int id);
	
	void save(CustomerDocument document);
	
	List<CustomerDocument> findAllByUserId(String customerName);
	
	void deleteById(int id);
}

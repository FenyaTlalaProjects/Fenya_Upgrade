package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.model.CustomerDocument;

public interface UserDocumentService {

	CustomerDocument findById(int id);

	List<CustomerDocument> findAll();
	
	List<CustomerDocument> findAllByUserId(String customerName);
	
	void saveDocument(CustomerDocument document);
	
	void deleteById(int id);
}

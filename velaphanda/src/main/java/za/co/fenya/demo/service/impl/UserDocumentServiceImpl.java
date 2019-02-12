package za.co.fenya.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.dao.UserDocumentDao;
import za.co.fenya.demo.model.CustomerDocument;
import za.co.fenya.demo.service.UserDocumentService;


@Service("userDocumentService")
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService{

	@Autowired
	UserDocumentDao dao;
	
	@Override
	public CustomerDocument findById(int id) {
		
		return dao.findById(id);
	}

	@Override
	public List<CustomerDocument> findAll() {
		
		return dao.findAll();
	}

	@Override
	public List<CustomerDocument> findAllByUserId(String customerName) {
		
		return dao.findAllByUserId(customerName);
	}

	@Override
	public void saveDocument(CustomerDocument document) {
		dao.save(document);
		
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
		
	}

}

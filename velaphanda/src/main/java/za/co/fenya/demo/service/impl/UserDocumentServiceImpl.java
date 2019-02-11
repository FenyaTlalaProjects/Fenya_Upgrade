package za.co.fenya.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.dao.UserDocumentDao;
import za.co.fenya.demo.model.UserDocument;
import za.co.fenya.demo.service.UserDocumentService;


@Service("userDocumentService")
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService{

	@Autowired
	UserDocumentDao dao;
	
	@Override
	public UserDocument findById(int id) {
		
		return dao.findById(id);
	}

	@Override
	public List<UserDocument> findAll() {
		
		return dao.findAll();
	}

	@Override
	public List<UserDocument> findAllByUserId(int userId) {
		
		return dao.findAllByUserId(userId);
	}

	@Override
	public void saveDocument(UserDocument document) {
		dao.save(document);
		
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
		
	}

}

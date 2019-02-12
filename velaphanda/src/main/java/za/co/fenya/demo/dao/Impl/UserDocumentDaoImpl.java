package za.co.fenya.demo.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import za.co.fenya.demo.dao.UserDocumentDao;
import za.co.fenya.demo.model.CustomerDocument;


@Repository("userDocumentDao")
public class UserDocumentDaoImpl extends AbstractDao<Integer, CustomerDocument> implements UserDocumentDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerDocument> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<CustomerDocument>)crit.list();
	}

	@Override
	public CustomerDocument findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(CustomerDocument document) {
		persist(document);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerDocument> findAllByUserId(String customerName) {
		Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.createCriteria("user");
		userCriteria.add(Restrictions.eq("id", customerName));
		return (List<CustomerDocument>)crit.list();
	}

	@Override
	public void deleteById(int id) {
		CustomerDocument document =  getByKey(id);
		delete(document);
	}

}

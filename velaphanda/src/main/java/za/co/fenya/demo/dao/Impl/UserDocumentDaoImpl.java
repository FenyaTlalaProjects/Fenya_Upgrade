package za.co.fenya.demo.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import za.co.fenya.demo.dao.UserDocumentDao;
import za.co.fenya.demo.model.UserDocument;


@Repository("userDocumentDao")
public class UserDocumentDaoImpl extends AbstractDao<Integer, UserDocument> implements UserDocumentDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDocument> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<UserDocument>)crit.list();
	}

	@Override
	public UserDocument findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(UserDocument document) {
		persist(document);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDocument> findAllByUserId(int userId) {
		Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.createCriteria("user");
		userCriteria.add(Restrictions.eq("id", userId));
		return (List<UserDocument>)crit.list();
	}

	@Override
	public void deleteById(int id) {
		UserDocument document =  getByKey(id);
		delete(document);
	}

}

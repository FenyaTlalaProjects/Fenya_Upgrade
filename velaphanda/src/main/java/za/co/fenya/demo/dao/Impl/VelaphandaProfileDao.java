package za.co.fenya.demo.dao.Impl;



import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.dao.VelaphandaProfileDaoInt;
import za.co.fenya.demo.model.VelaphandaProfile;



@Repository("velaphandaProfileDao")
@Transactional(propagation=Propagation.REQUIRED)
public class VelaphandaProfileDao implements VelaphandaProfileDaoInt{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String saveVelaphandaAddress(VelaphandaProfile profile) {
		
		sessionFactory.getCurrentSession().save(profile);
		return profile.getCompanyName();
	}

	@Override
	public VelaphandaProfile getVelaphandaAddress(String companyName) {
	    return (VelaphandaProfile) sessionFactory.getCurrentSession().get(VelaphandaProfile.class, companyName);
	}

}

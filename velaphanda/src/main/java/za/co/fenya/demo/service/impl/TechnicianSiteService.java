package za.co.fenya.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import za.co.fenya.demo.model.TechnicianSite;
import za.co.fenya.demo.service.TechnicianSiteDaoInt;


/*@Service("technicianService")
@Transactional*/
public class TechnicianSiteService implements TechnicianSiteDaoInt{
	@Autowired
	private TechnicianSiteDaoInt technicianSiteDao;

	@Override
	public String assingTechniciansToSite(List<TechnicianSite> technicians) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeTechnicianOnSite(List<TechnicianSite> technicians) {
		// TODO Auto-generated method stub
		return null;
	}

	

}

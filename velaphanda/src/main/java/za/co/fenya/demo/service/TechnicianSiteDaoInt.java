package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.model.TechnicianSite;


public interface TechnicianSiteDaoInt {
	
	String assingTechniciansToSite(List<TechnicianSite> technicians);
	String removeTechnicianOnSite(List<TechnicianSite> technicians);

}

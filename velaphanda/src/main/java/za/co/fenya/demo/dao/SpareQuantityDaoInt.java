package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.bean.SpareQuantity;

public interface SpareQuantityDaoInt {

	List<SpareQuantity> spareQuantity();
	List<SpareQuantity> spareQuantityForTechnicians();
	List<SpareQuantity> spareQuantityForTechnician(String technicianName);
	List<SpareQuantity> spareQuantityForTechnicianSiteStock();
}

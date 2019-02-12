package za.co.fenya.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.bean.SpareQuantity;
import za.co.fenya.demo.dao.SpareQuantityDaoInt;
import za.co.fenya.demo.service.SpareQuantityInt;


@Service("spareQuantityService")
@Transactional
public class SpareQuantityService implements SpareQuantityInt{

	@Autowired
	private SpareQuantityDaoInt spareInt;
	@Override
	public List<SpareQuantity> spareQuantity() {
		return spareInt.spareQuantity();
	}
	@Override
	public List<SpareQuantity> spareQuantityForTechnicians() {
		return spareInt.spareQuantityForTechnicians();
	}
	@Override
	public List<SpareQuantity> spareQuantityForTechnician(String technicianName) {
		return spareInt.spareQuantityForTechnician(technicianName);
	}
	@Override
	public List<SpareQuantity> spareQuantityForTechnicianSiteStock() {
		return spareInt.spareQuantityForTechnicianSiteStock();
	}

}

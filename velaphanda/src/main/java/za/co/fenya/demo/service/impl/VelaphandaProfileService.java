package za.co.fenya.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.dao.VelaphandaProfileDaoInt;
import za.co.fenya.demo.model.VelaphandaProfile;
import za.co.fenya.demo.service.VelaphandaInt;


@Service("velaphandaProfileService")
@Transactional
public class VelaphandaProfileService implements VelaphandaInt{

	@Autowired
	private VelaphandaProfileDaoInt velas;
	@Override
	public String saveVelaphandaAddress(VelaphandaProfile profile) {
		
		return velas.saveVelaphandaAddress(profile);
	}

	@Override
	public VelaphandaProfile getVelaphandaAddress(String companyName) {
		return velas.getVelaphandaAddress(companyName);
	}

}

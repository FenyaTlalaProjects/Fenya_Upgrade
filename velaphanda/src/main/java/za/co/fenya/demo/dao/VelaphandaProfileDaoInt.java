package za.co.fenya.demo.dao;

import za.co.fenya.demo.model.VelaphandaProfile;


public interface VelaphandaProfileDaoInt {

	String saveVelaphandaAddress(VelaphandaProfile profile);
	VelaphandaProfile getVelaphandaAddress(String companyName);
}

package za.co.fenya.demo.service;

import za.co.fenya.demo.model.VelaphandaProfile;


public interface VelaphandaInt {

	String saveVelaphandaAddress(VelaphandaProfile profile);
	VelaphandaProfile getVelaphandaAddress(String companyName);
}

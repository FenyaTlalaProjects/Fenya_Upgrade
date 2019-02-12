package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.model.SpareMaster;


public interface SpareMasterServiceInt {

	List<SpareMaster> getSparesFromMastaData();
	SpareMaster getSpareMaster(String partNumber);
	String[] getSerials();
	List<String> getModelDevice(String partNumber);
	String saveSpareMasterData(SpareMaster spareMaster );
}

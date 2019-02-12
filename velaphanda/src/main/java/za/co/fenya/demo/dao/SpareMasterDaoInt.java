package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.model.SpareMaster;


public interface SpareMasterDaoInt {
	
	List<SpareMaster> getSparesFromMastaData();
	SpareMaster getSpareMaster(String partNumber);
	String[] getSerials();
	List<String> getModelDevice(String partNumber);
	String saveSpareMasterData(SpareMaster spareMaster );

}

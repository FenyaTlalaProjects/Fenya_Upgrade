package za.co.fenya.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.dao.SpareMasterDaoInt;
import za.co.fenya.demo.model.SpareMaster;
import za.co.fenya.demo.service.SpareMasterServiceInt;


@Service("psareMasterService")
@Transactional
public class SpareMasterService implements SpareMasterServiceInt{

	
	@Autowired
	private SpareMasterDaoInt spareMasterDaoInt;
	@Override
	public List<SpareMaster> getSparesFromMastaData() {
		return spareMasterDaoInt.getSparesFromMastaData();
	}
	@Override
	public SpareMaster getSpareMaster(String partNumber) {
		
		return spareMasterDaoInt.getSpareMaster(partNumber);
	}
	@Override
	public String[] getSerials() {
		
		return spareMasterDaoInt.getSerials();
	}
	@Override
	public List<String> getModelDevice(String partNumber) {
		return spareMasterDaoInt.getModelDevice(partNumber);
	}
	@Override
	public String saveSpareMasterData(SpareMaster spareMaster) {
		return spareMasterDaoInt.saveSpareMasterData(spareMaster);
	}

}

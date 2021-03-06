package za.co.fenya.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.fenya.demo.dao.ModelNumbersMasterDaoInt;
import za.co.fenya.demo.model.ModelNumbers;
import za.co.fenya.demo.service.ModelNumbersMasterServiceInt;



@Service("modelNumbersMasterService")
@Transactional
public class ModelNumbersMasterService implements ModelNumbersMasterServiceInt{

	
	@Autowired
	private ModelNumbersMasterDaoInt modelNumbersMasterDaoInt;

	@Override
	public String saveModelNumberData(ModelNumbers modelNumber) {
		return modelNumbersMasterDaoInt.saveModelNumberData(modelNumber);
	}
	@Override
	public String[] getModelNumbers() {
		return modelNumbersMasterDaoInt.getModelNumbers();
	}
	@Override
	public List<ModelNumbers> getModelNumbersFromMasterData() {
		return modelNumbersMasterDaoInt.getModelNumbersFromMasterData();
	}
	@Override
	public ModelNumbers getModelNumbersMaster(String modelNumber) {
		return modelNumbersMasterDaoInt.getModelNumbersMaster(modelNumber);
	}
	@Override
	public List<String> getAllModelNumbers(String modelNumber) {
		return modelNumbersMasterDaoInt.getAllModelNumbers(modelNumber);
	}
	

}

package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.model.ModelNumbers;


public interface ModelNumbersMasterServiceInt {

	String[] getModelNumbers();
	List<ModelNumbers> getModelNumbersFromMasterData();
	ModelNumbers getModelNumbersMaster(String modelNumber);
	List<String> getAllModelNumbers(String modelNumber);
	String saveModelNumberData(ModelNumbers modelNumber);
}

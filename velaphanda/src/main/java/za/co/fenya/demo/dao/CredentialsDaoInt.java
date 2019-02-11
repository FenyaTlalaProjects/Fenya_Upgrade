package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.model.Credentials;

public interface CredentialsDaoInt {

	boolean saveNewPassword(Credentials credentials);
	List<Credentials> getCredentialsForUser(String email);
	List<Credentials> getCredentialsForUser();
	long passwordDateDifference(String userName);
	Credentials getEmployeePasswordInfo(String userName);
	Credentials getCurrentPasswordDate(String userName);
}

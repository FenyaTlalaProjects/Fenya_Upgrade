package za.co.fenya.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.dao.CredentialsDaoInt;
import za.co.fenya.demo.service.CredentialsServiceInt;

@Service("credentialsService")
public class CrendentialsService implements CredentialsServiceInt{

	@Autowired
	private CredentialsDaoInt credentialsDaoInt;

	@Override
	public long passwordDateDifference(String userName) {
		
		return credentialsDaoInt.passwordDateDifference(userName);
	}
}

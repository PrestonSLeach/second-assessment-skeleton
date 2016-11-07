package com.cooksys.service;

import org.springframework.stereotype.Service;

import com.cooksys.entity.Credentials;
import com.cooksys.entity.Users;
import com.cooksys.repo.CredentialRepo;
import com.cooksys.repo.UserRepo;

@Service
public class CredentialService {

	private CredentialRepo cr;
	private Credentials c;
	private UserRepo ur;

	public CredentialService(CredentialRepo cr, UserRepo us) {
		this.cr = cr;
		this.ur = ur;
	}

	public void save(Credentials c) {
		cr.saveAndFlush(c);
		System.out.println("Credential saved");
	}

	public void addPassword(Credentials password) {
		cr.saveAndFlush(password);
		System.out.println("Credential password added");
	}

//	public Users getUser(Credentials c) {
//		cr.saveAndFlush(c);
//		return ur.getUsersByCredentials(c);
//	}

	public void createCredentials(String username, String password) {
		try {
			c.setUsername(username);
			c.setPassword(password);
			cr.saveAndFlush(c);
			System.out.println("createCredentials");
		} catch (NullPointerException e) {
			System.out.println("Must enter username and password." + e.getMessage());

		}
	}

}

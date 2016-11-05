package com.cooksys.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Credentials;


public interface CredentialRepo extends JpaRepository<Credentials, Long> {
	
	List<Credentials> findByUsernameIgnoreCase(String username);

	List<Credentials> getCredentialsByUsernameIgnoreCaseAndPassword(String username, String password);
	
//	Credentials getUserByUsernameIgnoreCaseAndPassword(Credentials c);
//	Credentials getUserByUsername(String username);


}

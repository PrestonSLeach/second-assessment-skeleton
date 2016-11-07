package com.cooksys.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Credentials;
import com.cooksys.entity.Users;

public interface CredentialsRepo extends JpaRepository<Credentials, Long>{

	Users getUsersByUsernameAndPassword(String username, String password);
}

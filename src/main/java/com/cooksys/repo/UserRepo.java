package com.cooksys.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Credentials;
import com.cooksys.entity.Users;

public interface UserRepo extends JpaRepository<Users, Long> {

	List<Users> findByUsernameNotNull(String username);

	Users getUsersByUsernameIgnoreCaseAndActiveTrue(String username);

	Boolean findUsersByUsernameIgnoreCaseAndActiveTrue(String username);

	Users getCredentialsByUsernameIgnoreCase(String username);

	Users getUsersByUsernameIgnoreCase(String username);

	List<Users> getByUsernameNotNull(String userlist);

	List<Users> getUsernameByUsernameNotNull();

	List<Users> findByActiveTrue();

	Users getUsersByCredentials(Credentials credentials);

}

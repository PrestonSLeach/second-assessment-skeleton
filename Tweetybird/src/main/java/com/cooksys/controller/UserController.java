package com.cooksys.controller;

import java.util.List;

import org.hibernate.Session;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Credentials;
import com.cooksys.entity.Users;
import com.cooksys.repo.CredentialsRepo;
import com.cooksys.repo.UserRepo;
import com.cooksys.service.CredentialService;
import com.cooksys.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	private UserService userService;
	private UserRepo userRepo;
	private CredentialService cs;
	private Users user;
	private Credentials creds;
	private Session session;
	private CredentialsRepo cr;

	public UserController(UserService userService, UserRepo userRepo, CredentialService cs, CredentialsRepo cr) {
		this.userService = userService;
		this.userRepo = userRepo;
		this.cs = cs;
		this.cr = cr;
	}

	@GetMapping
	public List<Users> getUsernames() {
		return userRepo.findByActiveTrue();
	}

	@GetMapping("/@{username}")
	public Users getUser(@PathVariable String username) {
		return userRepo.getUsersByUsernameIgnoreCaseAndActiveTrue(username);
	}

	@PostMapping
	public void createUser(@RequestBody Users user) {
		user.setActive(true);
		
		try {
			userService.add(user);
			System.out.println("createUser");
		} catch (NullPointerException e) {
			System.out.println("Must enter username and password." + e.getMessage());

		} catch (DataIntegrityViolationException e) {
			System.out.println("Wrong information.");
		}

	}
	@Transactional
	@DeleteMapping("/@{username}")
	public void deleteUser(@PathVariable String username, @RequestBody Credentials c, Users u) {
//		if ((userRepo.getUsersByCredentials(c) != null)) {
		u = userRepo.getUsersByUsernameIgnoreCaseAndActiveTrue(username);
		u.setActive(false);
		userService.add(u);
//		}
	}

	@PostMapping("/@{username}/follow")
	public void followUser(@PathVariable String username, @RequestBody Credentials c, Users u) {
		u = userRepo.getUsersByCredentials(c);
		u.getFollowers().add(username);
		userService.add(u);
		System.out.println("fuck yea");

	}

}

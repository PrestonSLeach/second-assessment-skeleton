package com.cooksys.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	private EntityManager em;

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
	public Users createUser(@RequestBody Users user) {
		user.setActive(true);
		user.setUsername(user.getCredentials().getUsername());
		try {
			userService.add(user);
			return user;
		} catch (DataIntegrityViolationException e) {
			System.out.println("Wrong information.");
			return user;
		}

	}
	
	@Transactional
	@PatchMapping("/@{username}")
	public Users updateProfile(@PathVariable String username, @RequestBody Users user) {
		user = userRepo.getUsersByUsernameIgnoreCaseAndActiveTrue(username);
		user.setProfile(user.getProfile());
		userService.add(user);
		return user;
	}

	@Transactional
	@DeleteMapping("/@{username}")
	public void deleteUser(@PathVariable String username, @RequestBody Users u) {
		u = userRepo.getUsersByUsernameIgnoreCaseAndActiveTrue(username);
		u.setActive(false);
		userService.add(u);
	}
	
	@Transactional
	@PostMapping("/@{username}/follow")
	public Users followUser(@PathVariable String username, @RequestBody Users u) {
		u.getFollowers().add(username);
		userService.add(u);
		System.out.println("fuck yea");
		return u;

	}

}

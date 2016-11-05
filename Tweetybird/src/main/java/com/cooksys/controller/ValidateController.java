package com.cooksys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.repo.UserRepo;

@RestController
@RequestMapping("validate")
public class ValidateController {
	
	private UserRepo ur;
	
	public ValidateController(UserRepo ur) {
		this.ur = ur;
	}
	
	@GetMapping("username/available/@{username}")
	public boolean usernameAvailable(@PathVariable String username) {
		if(ur.getUsersByUsernameIgnoreCase(username) != null) {
			return false;
		} else return true;
		}

	@GetMapping("/username/exists/@{username}")
		public boolean userExists(@PathVariable String username) {
			if(ur.getUsersByUsernameIgnoreCaseAndActiveTrue(username) != null) {
				return true;
			} else return false;
			

	}
	

}

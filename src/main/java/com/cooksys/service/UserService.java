package com.cooksys.service;

import org.springframework.stereotype.Service;

import com.cooksys.entity.Credentials;
import com.cooksys.entity.Users;
import com.cooksys.repo.CredentialsRepo;
import com.cooksys.repo.TweetRepo;
import com.cooksys.repo.UserRepo;

@Service
public class UserService {

	private UserRepo userRepo;
	private TweetRepo tweetRepo;
	private CredentialsRepo cr;

	public UserService(UserRepo userRepo, TweetRepo tweetRepo, CredentialsRepo cr) {
		this.userRepo = userRepo;
		this.tweetRepo = tweetRepo;
		this.cr = cr;
	}

	// public void addTweet(Users user, Tweet tweet) {
	// user.getTweets().add(tweet);
	// userRepo.saveAndFlush(user);
	// }

	// public String get(String name) {
	// if (user.getUsername() == name) {
	// return user.getUsername();
	// } else
	// return "User does not exist.";
	// }

	public void add(Users user) {
		userRepo.saveAndFlush(user);

	}

	public void addCreds(Credentials c) {
		cr.saveAndFlush(c);
	}

	public void addFollower(Users user, String username) {
		user.getFollowers().add(username);
		userRepo.saveAndFlush(user);
	}
	// public List<User> getUserList() {
	// userlist = userRepo.getUsernameByUsernameNotNull();
	// return null;
	// }
}

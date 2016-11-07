package com.cooksys.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.cooksys.entity.Credentials;
import com.cooksys.entity.Tweet;
import com.cooksys.repo.TweetRepo;


@RestController
@RequestMapping("tweets")
public class TweetController {
	
	private TweetRepo tr;
	Calendar calendar = Calendar.getInstance();
	Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
	
	public TweetController(TweetRepo tr) {
		this.tr = tr;
	}
	
	@GetMapping
	public List<Tweet> getTweets() {
		return tr.findByActiveTrue();
		
	}
	
	@PostMapping
	public Tweet postTweet(@RequestBody Credentials creds, @RequestBody Tweet tweet) {
		tweet.setActive(true);
		tweet.setAuthor(creds.getUsername());
		tweet.setPosted(currentTimestamp);
		tr.saveAndFlush(tweet);
		return tweet;
	}
	
	@DeleteMapping("/{id}")
	public Tweet deleteTweet(@PathVariable String id, @RequestBody Tweet tweet) {
		tweet = tr.getTweetById(id);
		tweet.setActive(false);
		tr.saveAndFlush(tweet);
		return tweet;
	}
}

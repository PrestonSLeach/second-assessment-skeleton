package com.cooksys.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Tweet;

public interface TweetRepo extends JpaRepository<Tweet, Long>{
	
	List<Tweet> findByActiveTrue();

	Tweet getTweetById(String id);
}

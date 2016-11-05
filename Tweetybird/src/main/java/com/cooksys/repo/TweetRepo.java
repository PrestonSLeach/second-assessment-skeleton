package com.cooksys.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Tweet;

public interface TweetRepo extends JpaRepository<Tweet, Long>{

}

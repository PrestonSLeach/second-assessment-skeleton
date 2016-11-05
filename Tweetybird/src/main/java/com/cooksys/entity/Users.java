package com.cooksys.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(updatable = false)
	private Long joined;
	
	@Column(nullable = false)
	private Boolean active;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Tweet> tweets;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "credentials_fk", unique = true)
	private Credentials credentials;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name= "profile_fk", unique = true)
	private Profile profile;
	
	@ElementCollection()
	private List<String> followers;
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getJoined() {
		return joined;
	}

	public void setJoined(Long joined) {
		this.joined = joined;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public List<String> getFollowers() {
		return followers;
	}

	public void setFollowers(List<String> follows) {
		this.followers = follows;
	}

}

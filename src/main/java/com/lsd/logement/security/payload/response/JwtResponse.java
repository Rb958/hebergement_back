package com.lsd.logement.security.payload.response;


import com.lsd.logement.entity.personnel.User;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private final List<String> roles;
	private User userDetails;

	public JwtResponse(String accessToken, User userDetail, List<String> roles) {
		this.token = accessToken;
		this.userDetails = userDetail;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public JwtResponse setToken(String token) {
		this.token = token;
		return this;
	}

	public String getType() {
		return type;
	}

	public JwtResponse setType(String type) {
		this.type = type;
		return this;
	}

	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}

	public List<String> getRoles() {
		return roles;
	}
}

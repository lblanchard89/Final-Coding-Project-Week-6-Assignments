package com.promineotech.rudimentarybanking.view;

import com.promineotech.rudimentarybanking.entites.User;

public class UserLoggedInView {
	private User user;
	private String jwt;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}

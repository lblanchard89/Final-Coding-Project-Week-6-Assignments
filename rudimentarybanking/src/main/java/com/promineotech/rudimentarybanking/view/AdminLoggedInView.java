package com.promineotech.rudimentarybanking.view;

import com.promineotech.rudimentarybanking.entites.Admin;

public class AdminLoggedInView {
	private Admin admin;
	private String jwt;
	
	public Admin getAdmin() {
		return admin;
	}
	
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}


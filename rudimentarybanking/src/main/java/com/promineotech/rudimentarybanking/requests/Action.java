package com.promineotech.rudimentarybanking.requests;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.rudimentarybanking.entites.Account;


public class Action {
	
	//WITHDRAW or DEPOSIT
	private String type;
	private Long accountId;
	private double amount;
	
	@JsonIgnore
	public Set<Account> accounts;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}

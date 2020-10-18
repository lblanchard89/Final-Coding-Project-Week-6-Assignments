package com.promineotech.rudimentarybanking.requests;

import java.util.Set;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.rudimentarybanking.entites.Account;


public class Transfer {
	
	
	private Long toAccountId;
	private Long fromAccountId;
	private double amount;
	
	@JsonIgnore
	public Set<Account> accounts;
	
	public Long getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(Long toAccountId) {
		this.toAccountId = toAccountId;
	}
	public Long getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}

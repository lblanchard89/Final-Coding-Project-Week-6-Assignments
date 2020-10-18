package com.promineotech.rudimentarybanking.entites;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.rudimentarybanking.requests.Action;
import com.promineotech.rudimentarybanking.requests.Transfer;
import com.promineotech.rudimentarybanking.util.AccountTier;

@Entity
public class Account {
	
	private Long id;
	private String accountType;
	private double balance;
	private AccountTier accountTier;
	private Set<User> users;
	
	@JsonIgnore
	private Set<Action> actions;
	
	@JsonIgnore
	private Set<Transfer> transfers;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name ="userAccounts",
		joinColumns = @JoinColumn(name = "accountId", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
	public Set<User> getUsers() {
		return users;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountTier getAccountTier() {
		return accountTier;
	}

	public void setAccountTier(AccountTier accountTier) {
		this.accountTier = accountTier;
	}

}

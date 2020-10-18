package com.promineotech.rudimentarybanking.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.rudimentarybanking.entites.Account;
import com.promineotech.rudimentarybanking.entites.User;
import com.promineotech.rudimentarybanking.repository.AccountRepository;
import com.promineotech.rudimentarybanking.repository.UserRepository;
import com.promineotech.rudimentarybanking.requests.Action;
import com.promineotech.rudimentarybanking.requests.Transfer;

@Service
public class AccountService {
	
	private static final Logger logger = LogManager.getLogger(AccountService.class);
	
	@Autowired
	private AccountRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Iterable<Account> getAllAccounts() {
		return repo.findAll();
	}
	
	
	public Account createAccount(Account account, Long id) {
		Account newAccount = account;
		User accountUser = userRepo.findOne(id);
		Set<User> accountUsers = new HashSet<User>();
		accountUsers.add(accountUser);
		newAccount.setUsers(accountUsers);
		return repo.save(newAccount);
	}
	
	public Account getAccountById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to retrieve user: " + id, e);
			throw e;
		}
	}
	
	public Iterable<Account> getUserAccounts(Long id) throws Exception {
		User user = userRepo.findOne(id);
		try {
			return user.getAccounts();
		} catch(Exception e){
			logger.error("Exception occurred while trying to access user accounts: " + id, e);
			throw e;
		}
	}
	
	public Iterable<Account> getAccounts() {
		return repo.findAll();
	}
	
	public double showBalance(Long id) throws Exception {
		try {
			Account account = repo.findOne(id); 
			return account.getBalance();
			} catch (Exception e) {
				logger.error("Exception occured while trying to retrieve balance of account: " + id, e);
				throw e;
		}
	}
	
	public Account updateAccount(Account account, Long id) throws Exception {	
		try {
			Account editAccount = repo.findOne(id);
			editAccount.setUsers(account.getUsers()); 
			editAccount.setAccountType(account.getAccountType());
			editAccount.setAccountTier(account.getAccountTier());
			return repo.save(editAccount);
		} catch (Exception e) {
			logger.error("Exception occured while trying to update the account with an id of: " + id, e);
			throw new Exception("Unable to update the account.");
		}
	}


	public void closeAccount(Long id) throws Exception {
		try {
			Account account = repo.findOne(id);
			if (account.getBalance() != 0) {
				throw new Exception("Account cannot be closed.");
			}else {
				if (account.getBalance() == 0) {
					repo.delete(id);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occured while trying to close an account with the id of: " + id, e);
			throw new Exception("Unable to close the account.");
		}
	}

	public Account changeBalance(Action action) throws Exception {
		Account account = repo.findOne(action.getAccountId());
		if (action.getType().equals("DEPOSIT")) {
			account.setBalance(account.getBalance() + action.getAmount());
		} else if (action.getType().equals("WITHDRAW") && (action.getAmount() <= account.getBalance())) {
			account.setBalance(account.getBalance() - action.getAmount());
		} else throw new Exception("Insufficient funds to complete request."); 
		return repo.save(account);
	}	

	public Account newTransfer(Transfer transfer) throws Exception {
		Account fromAccount = repo.findOne(transfer.getFromAccountId());
		Account toAccount = repo.findOne(transfer.getToAccountId());
		if (fromAccount.getBalance() >= transfer.getAmount()) {
			fromAccount.setBalance(fromAccount.getBalance() - transfer.getAmount());
			toAccount.setBalance(toAccount.getBalance() + transfer.getAmount());
			repo.save(fromAccount);
		} else throw new Exception("Insufficient funds to complete request.");
		return repo.save(toAccount);
		
		
	}
}

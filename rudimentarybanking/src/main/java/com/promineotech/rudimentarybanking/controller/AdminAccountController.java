package com.promineotech.rudimentarybanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.rudimentarybanking.entites.Account;
import com.promineotech.rudimentarybanking.requests.Action;
import com.promineotech.rudimentarybanking.requests.Transfer;
import com.promineotech.rudimentarybanking.service.AccountService;

@RestController
@RequestMapping("/admin/account") 
public class AdminAccountController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/user/{id}/register",method=RequestMethod.POST)
	public ResponseEntity<Object> registerAccount(@RequestBody Account account, @PathVariable Long id) {
		return new ResponseEntity<Object>(accountService.createAccount(account, id), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> showAllAccounts() {
		return new ResponseEntity<Object>(accountService.getAllAccounts(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Object> showAccountById(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(accountService.getAccountById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public ResponseEntity<Object> getUserAccounts(@PathVariable Long id) {
		try {
		return new ResponseEntity<Object>(accountService.getUserAccounts(id), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(accountService.updateAccount(account, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}/action", method=RequestMethod.PUT)
	public ResponseEntity<Object> changeBalance(@RequestBody Action action) {
		try {
			return new ResponseEntity<Object>(accountService.changeBalance(action), HttpStatus.OK);
		} catch (Exception e ) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}/transfer", method=RequestMethod.PUT)
	public ResponseEntity<Object> newTransfer(@RequestBody Transfer transfer) {
		try {
			return new ResponseEntity<Object>(accountService.newTransfer(transfer), HttpStatus.OK);
		} catch (Exception e ) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAccount(@PathVariable Long id) {
		try {
			accountService.closeAccount(id);
			return new ResponseEntity<Object>("Successfully closed account with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}

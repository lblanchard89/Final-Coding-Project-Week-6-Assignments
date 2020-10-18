package com.promineotech.rudimentarybanking.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.rudimentarybanking.entites.Account;
import com.promineotech.rudimentarybanking.entites.Credentials;
import com.promineotech.rudimentarybanking.requests.Action;
import com.promineotech.rudimentarybanking.requests.Transfer;
import com.promineotech.rudimentarybanking.service.AccountService;
import com.promineotech.rudimentarybanking.service.AuthenticationService;

@RestController
@RequestMapping("/user") 
public class UserAccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AuthenticationService authService;
	
//	@RequestMapping(value="/users/{id}",method=RequestMethod.POST)
//	public ResponseEntity<Object> createAccount(@RequestBody Account account, @PathVariable Long id) {
//		return new ResponseEntity<Object>(accountService.createAccount(account, id), HttpStatus.CREATED);
//	}
//	
	
//	@RequestMapping(value = "/accountregister", method = RequestMethod.POST)
//	public ResponseEntity<Object> createAccount(@RequestBody Account account, HttpServletRequest request, @PathVariable Long id) {
//		try {
//			if (authenticationService.isAdmin(authenticationService.getToken(request))) {
//				return new ResponseEntity<Object>(accountService.createAccount(account, id), HttpStatus.CREATED);
//			} else {
//				return new ResponseEntity<Object>("Unauthorized request.", HttpStatus.UNAUTHORIZED);
//			}
//		} catch (Exception e) {
//			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
	
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody Credentials cred){
		try {
			return new ResponseEntity<Object>(authService.userLogin(cred), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}/account/{id}",method=RequestMethod.GET)
	public ResponseEntity<Object> showUserAccount(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(accountService.getAccountById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}/account",method=RequestMethod.GET)
	public ResponseEntity<Object> showAllUserAccounts(@PathVariable Long id) {
		try {
		return new ResponseEntity<Object>(accountService.getUserAccounts(id), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/{id}/account/{id}/action", method=RequestMethod.PUT)
	public ResponseEntity<Object> newAction(@RequestBody Action action) {
		try {
			return new ResponseEntity<Object>(accountService.changeBalance(action), HttpStatus.OK);
		} catch (Exception e ) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}/account/{id}/transfer", method=RequestMethod.PUT)
	public ResponseEntity<Object> newTransfer(@RequestBody Transfer transfer) {
		try {
			return new ResponseEntity<Object>(accountService.newTransfer(transfer), HttpStatus.OK);
		} catch (Exception e ) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}

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

import com.promineotech.rudimentarybanking.entites.Admin;
import com.promineotech.rudimentarybanking.entites.Credentials;
import com.promineotech.rudimentarybanking.service.AdminService;
import com.promineotech.rudimentarybanking.service.AuthenticationService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;
	
	@Autowired
	private AuthenticationService authService;

	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Object> adminRegister(@RequestBody Credentials cred, HttpServletRequest request) {
		try {
				return new ResponseEntity<Object>(authService.registerAdmin(cred), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody Credentials cred){
		try {
			return new ResponseEntity<Object>(authService.adminLogin(cred), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAdminInfo(@PathVariable Long id, HttpServletRequest request) {
		try {
			return new ResponseEntity<Object>(service.getAdminById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateAdmin(@RequestBody Admin admin, @PathVariable Long id, HttpServletRequest request) {
		try {
			return new ResponseEntity<Object>(service.updateAdminInfo(admin, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAdmin(@PathVariable Long id, HttpServletRequest request) {
		try {
			service.deleteAdmin(id);
			return new ResponseEntity<Object>("Successfully deleted admin with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}


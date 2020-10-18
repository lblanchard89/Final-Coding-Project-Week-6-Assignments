//package com.promineotech.rudimentarybanking.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.promineotech.rudimentarybanking.entites.Credentials;
//import com.promineotech.rudimentarybanking.entites.User;
//import com.promineotech.rudimentarybanking.service.AuthenticationService;
//import com.promineotech.rudimentarybanking.service.UserService;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//	
//	@Autowired
//	private UserService service;
//	
//	@Autowired
//	private AuthenticationService authenticationService;
//	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ResponseEntity<Object> login(@RequestBody Credentials cred){
//		try {
//			return new ResponseEntity<Object>(authenticationService.userLogin(cred), HttpStatus.OK);
//		} catch(Exception e) {
//			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
	
//	
////		@RequestMapping(value = "/{id}", method = RequestMethod.GET)
////		public ResponseEntity<Object> getUserInfo(@PathVariable Long id, HttpServletRequest request){
////			try {
////				return new ResponseEntity<Object>(service.getUserById(id), HttpStatus.OK);
////			}catch(Exception e) {
////				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
////			}
////		}
//		
////		@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
////		public ResponseEntity<Object> updateUserInfo(@RequestBody User user, @PathVariable Long id, HttpServletRequest request){
////			try {
////				if(authenticationService.isCorrectUser(authenticationService.getToken(request), id) || authenticationService.isAdmin(authenticationService.getToken(request))) {
////					return new ResponseEntity<Object>(service.updateUserInfo(user, id), HttpStatus.OK);
////				}else {
////					return new ResponseEntity<Object>("Unauthorized request.", HttpStatus.UNAUTHORIZED);
////				}
////			}catch(Exception e) {
////				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
////			}
////		}
////		
////		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
////		public ResponseEntity<Object> deleteUser(@PathVariable Long id, HttpServletRequest request) {
////			try {
////				if (authenticationService.isCorrectUser(authenticationService.getToken(request), id)
////						|| authenticationService.isAdmin(authenticationService.getToken(request))) {
////					service.deleteUser(id);
////					return new ResponseEntity<Object>("Successfully deleted User with id: " + id, HttpStatus.OK);
////				} else {
////					return new ResponseEntity<Object>("Unauthorized request.", HttpStatus.UNAUTHORIZED);
////				}
////			} catch (Exception e) {
////				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
////			}
////		}
////		
//}

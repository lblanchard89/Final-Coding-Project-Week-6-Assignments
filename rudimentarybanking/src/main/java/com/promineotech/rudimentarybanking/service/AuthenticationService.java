package com.promineotech.rudimentarybanking.service;

import java.security.Key;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.naming.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.rudimentarybanking.entites.Admin;
import com.promineotech.rudimentarybanking.entites.Credentials;
import com.promineotech.rudimentarybanking.entites.User;
import com.promineotech.rudimentarybanking.repository.AdminRepository;
import com.promineotech.rudimentarybanking.repository.UserRepository;
import com.promineotech.rudimentarybanking.view.AdminLoggedInView;
import com.promineotech.rudimentarybanking.view.UserLoggedInView;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthenticationService {

	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private UserRepository userRepo;

	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public Admin registerAdmin(Credentials cred) throws AuthenticationException {
		Admin admin = new Admin();
		admin.setUsername(cred.getUsername());
		admin.setPassword(BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt()));
		admin.setEmail(cred.getEmail());
		if (!isValidEmailAddress(cred.getEmail())) {
			throw new AuthenticationException("Email is not valid");
		} else {
			try {
				adminRepo.save(admin);
				return admin;
			} catch (DataIntegrityViolationException e) {
				throw new AuthenticationException("Email already in use.");
			}
		}
	}
	
	public User register(Credentials cred) throws AuthenticationException {
		User user = new User();
		user.setUsername(cred.getUsername());
		user.setPassword(BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt()));
		user.setEmail(cred.getEmail());
		if (!isValidEmailAddress(cred.getEmail())) {
			throw new AuthenticationException("Email is not valid");
		} else {
			try {
				userRepo.save(user);
				return user;
			} catch (DataIntegrityViolationException e) {
				throw new AuthenticationException("Email already in use.");
			}
		}
	}

	public AdminLoggedInView adminLogin(Credentials cred) throws AuthenticationException {
		Admin foundAdmin = adminRepo.findByEmail(cred.getEmail());
		if (BCrypt.checkpw(cred.getPassword(), foundAdmin.getPassword())) {
			AdminLoggedInView view = new AdminLoggedInView();
			view.setAdmin(foundAdmin);
			view.setJwt(adminGenerateToken(foundAdmin));
			return view;
		} else {
			throw new AuthenticationException("Incorrect email or password.");
		}
	}
	
	public UserLoggedInView userLogin(Credentials cred) throws AuthenticationException {
		User foundUser = userRepo.findByEmail(cred.getEmail());
		if (BCrypt.checkpw(cred.getPassword(), foundUser.getPassword())) {
			UserLoggedInView view = new UserLoggedInView();
			view.setUser(foundUser);
			view.setJwt(userGenerateToken(foundUser));
			return view;
		} else {
			throw new AuthenticationException("Incorrect email or password.");
		}
	}

	private String adminGenerateToken(Admin admin) {
		String jwt = Jwts.builder()
				.claim("adminId", admin.getId())
				.signWith(key).compact();
		return jwt;
	}
	
	private String userGenerateToken(User user) {
		String jwt = Jwts.builder()
				.claim("userId", user.getId())
				.signWith(key).compact();
		return jwt;
	}

//	public boolean isAdmin(String token) {
//		return ((String) Jwts.parser()
//				.setSigningKey(key)
//				.parseClaimsJws(token)
//				.getBody().get("role"))
//				.equals("ADMIN");
//	}
//	
//	public boolean isCorrectUser(String jwt, Long userId) {
//		return new Long((Integer)Jwts.parser()
//				.setSigningKey(key)
//				.parseClaimsJws(jwt)
//				.getBody()
//				.get("userId"))
//				.equals(userId);
//	}

	public String getToken(HttpServletRequest request) throws Exception {
		String header = request.getHeader("Authorization");
		if (header == null) {
			throw new Exception("Request contains no token.");
		}
		return header.replaceAll("Bearer ", "");
	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

}


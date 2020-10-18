package com.promineotech.rudimentarybanking.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.rudimentarybanking.entites.Admin;
import com.promineotech.rudimentarybanking.repository.AdminRepository;

@Service
public class AdminService {


	private static final Logger logger = LogManager.getLogger(UserService.class);
		
	@Autowired
	private AdminRepository repo;
	
	public Iterable<Admin> getAdmins() {
		return repo.findAll();
	}
	
	public Admin getAdminById(Long id) {
		return repo.findOne(id);
	}
	
	public Admin registerAdmin(Admin admin) {
		return repo.save(admin);
	}
	
	public Admin updateAdminInfo(Admin admin, Long id) throws Exception{
		try {
			Admin updateAdmin = repo.findOne(id);
			updateAdmin.setUsername(admin.getUsername());
			updateAdmin.setPassword(BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));;
			return repo.save(updateAdmin);
		} catch (Exception e) {
			logger.error("Exception occured while trying to update admin: " + id, e);
			throw new Exception("Unable to update admin.");
		}
	}
	
	public void deleteAdmin(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delte admin: " + id, e);
			throw new Exception("Unable to delete admin.");
		}
	}
}

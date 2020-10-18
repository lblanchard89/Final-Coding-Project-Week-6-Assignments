package com.promineotech.rudimentarybanking.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.rudimentarybanking.entites.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {

	Admin findByUsername(String username);

}

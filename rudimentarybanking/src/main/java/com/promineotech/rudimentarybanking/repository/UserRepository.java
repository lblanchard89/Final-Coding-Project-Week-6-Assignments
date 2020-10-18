package com.promineotech.rudimentarybanking.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.rudimentarybanking.entites.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByUsername(String username);

}

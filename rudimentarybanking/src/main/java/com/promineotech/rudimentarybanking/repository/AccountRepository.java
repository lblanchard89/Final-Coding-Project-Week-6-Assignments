package com.promineotech.rudimentarybanking.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.rudimentarybanking.entites.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{



}

package com.phillies.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.phillies.domain.Account;

public interface AccountRepo extends MongoRepository<Account, String> {
	public Account findByNameIgnoreCaseAndPassword(String name, String pass);
}
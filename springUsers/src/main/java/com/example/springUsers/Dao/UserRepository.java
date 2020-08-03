package com.example.springUsers.Dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.springUsers.Model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	User findByUserId(String userId); 
	
	User findByEmail(String email);
	
	void deleteByUserId(String userId);
}
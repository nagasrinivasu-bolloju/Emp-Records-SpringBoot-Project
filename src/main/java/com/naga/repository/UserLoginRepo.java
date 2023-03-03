package com.naga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naga.model.LoginRecords;


public interface UserLoginRepo extends JpaRepository<LoginRecords,String>{
	public LoginRecords findByUsername(String username);
}

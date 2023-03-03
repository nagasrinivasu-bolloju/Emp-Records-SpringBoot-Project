package com.naga.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naga.model.LoginRecords;
import com.naga.repository.UserLoginRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserLoginRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		LoginRecords user=userRepo.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("Username Not Found");
		return new MyUserDetails(user);
	}

}

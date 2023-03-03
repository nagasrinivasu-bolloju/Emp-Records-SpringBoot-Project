package com.naga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naga.model.Address;

public interface AddressRepo extends JpaRepository<Address,Integer>{
	public Address findById(int addrid);
}

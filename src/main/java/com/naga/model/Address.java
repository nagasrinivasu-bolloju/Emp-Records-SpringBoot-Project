package com.naga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address {
	@Id
	private int addrid;
	private String address;
	
	public Address() {
		super();
	}
	public int getAddrid() {
		return addrid;
	}
	public void setAddrid(int addrid) {
		this.addrid = addrid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Address(int addrid, String address) {
		super();
		this.addrid = addrid;
		this.address = address;
	}
}

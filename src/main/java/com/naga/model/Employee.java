package com.naga.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Employee
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int empid;
	private String firstName,lastName;
	private float salary;
	
	@ManyToOne//(cascade = CascadeType.PERSIST)
	@JoinColumn(name="addrid")	//using this annotaion we can store the data using only two tables and the third table will not be created in the db.
	private Address address;
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Employee()
	{
		
	}
	
	public Employee(int empid, String firstName, String lastName,float salary) {
		super();
		this.empid = empid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
}

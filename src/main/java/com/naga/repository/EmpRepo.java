package com.naga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naga.model.Employee;

public interface EmpRepo extends JpaRepository<Employee,Integer>{
	public Employee findById(int empid);
	
	public List<Employee> findByFirstName(String firstName);
}

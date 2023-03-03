package com.naga.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naga.customBinding.ModelObjectBinder;
import com.naga.model.Address;
import com.naga.model.Emp;
import com.naga.model.Employee;
import com.naga.repository.AddressRepo;
import com.naga.repository.EmpRepo;

@Service
public class ServiceClass
{
	
	@Autowired
	EmpRepo empRepo;
	
	@Autowired
	AddressRepo addressRepo;

	public int insert(Employee employee) throws SQLException {
		int addrid=employee.getAddress().getAddrid();
		Employee savedEmployee;
		
		if(!addressRepo.existsById(addrid))
		{
			addressRepo.save(employee.getAddress());
		}
		savedEmployee=empRepo.save(employee);
		if(savedEmployee!=null)
			return 1;
		return 0;
	}
	
	public void update(Employee employee)
	{
		int addrid=employee.getAddress().getAddrid();
		if(!addressRepo.existsById(addrid))
		{
			addressRepo.save(employee.getAddress());
		}
		if(empRepo.existsById(employee.getEmpid()))
		{
			System.out.println("emp found in db");
			empRepo.save(employee);
		}
	}
	
	public int delete(int empid) {
		Employee employee=empRepo.findById(empid);
		empRepo.delete(employee);
		return 1;
	}

	public Emp getEmployee(int empid) {
		Employee employee=empRepo.findById(empid);
		Emp emp=ModelObjectBinder.customObjectResolver(employee);
		return emp;
	}

	public List<Emp> getAllEmpsWithSameNames(String name,int action) {
		// TODO Auto-generated method stub
		List<Employee> employees=empRepo.findByFirstName(name);
		List<Emp> emps=new ArrayList<>();
		for(int i=0;i<employees.size();i++)
		{
			emps.add(ModelObjectBinder.customObjectResolver(employees.get(i)));
		}
		return emps;
	}
	
	public List<Emp> getAllEmployees() {
		// TODO Auto-generated method stub
		List<Employee> employees=empRepo.findAll();
		List<Emp> emps=new ArrayList<>();
		for(int i=0;i<employees.size();i++)
		{
			Emp emp=ModelObjectBinder.customObjectResolver(employees.get(i));
			emps.add(emp);
		}
		return emps;
	}

}

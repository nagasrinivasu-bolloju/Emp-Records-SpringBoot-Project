package com.naga.customBinding;

import com.naga.model.Address;
import com.naga.model.Emp;
import com.naga.model.Employee;

public class ModelObjectBinder {
	public static Employee customObjectBinder(Emp emp)
	{
		Employee employee=new Employee();
		Address address=new Address();
		
		address.setAddrid(emp.getAddrid());
		address.setAddress(emp.getAddress());
		
		employee.setEmpid(emp.getEmpid());
		employee.setAddress(address);
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setSalary(emp.getSalary());
		
		return employee;
	}
	
	public static Emp customObjectResolver(Employee employee)
	{
		Emp emp=new Emp();
		
		emp.setEmpid(employee.getEmpid());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setSalary(employee.getSalary());
		emp.setAddrid(employee.getAddress().getAddrid());
		emp.setAddress(employee.getAddress().getAddress());
		
		return emp;
	}
}

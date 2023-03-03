package com.naga.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.naga.customBinding.ModelObjectBinder;
import com.naga.model.Emp;
import com.naga.model.Employee;
import com.naga.service.ServiceClass;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class MyController {
	@Autowired
	ServiceClass service;
	

	public MyController(ServiceClass service)
	{
		this.service=service;
	}
	
	@GetMapping("/new")					
	private ModelAndView redirectToForm() throws IOException
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("employee-form");
		return mv;
	}
	
	@RequestMapping("/insert")
	public RedirectView insertIntoTable(Emp emp) throws IOException, SQLException 
	{
		RedirectView rv=new RedirectView();
		rv.setUrl("/list");
		Employee employee=ModelObjectBinder.customObjectBinder(emp);
		int rows=service.insert(employee);
		if(rows<=0)
			throw new RuntimeException();
		return rv;
	}
	
	@RequestMapping("/update")
	private RedirectView updateTable(Emp emp) throws IOException 
	{
		Employee employee=ModelObjectBinder.customObjectBinder(emp);
		service.update(employee);
		return new RedirectView("/");
	}
	
	@RequestMapping("/delete")
	private ModelAndView deleteEmployee(@RequestParam int id) throws ServletException, IOException 
	{
		ModelAndView mv=new ModelAndView(); //cant be mocked when testing.
		if(service.delete(id)==0)
		{
			
			mv.addObject("msg","Deletion failed!!!");
			mv.setViewName("error");
			return mv;
		}
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("/edit")
	private ModelAndView redirectToFormWithEmp(@RequestParam int id) throws ServletException, IOException 
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("employee-form");
		mv.addObject("emp",service.getEmployee(id));
		return mv;
	}
	
	@GetMapping("/temp")
	private ModelAndView reDirectToEmpidForm(@RequestParam int action) throws ServletException, IOException {
		 ModelAndView mv=new ModelAndView();
		 mv.setViewName("empid-form");
		 mv.addObject("action",action);
		 return mv;
	}
	
	@RequestMapping("/read-employees-with-similar-names")
	private ModelAndView readEmployeeNames(@RequestParam String name,@RequestParam int actionState) throws ServletException, IOException 
	{
		System.out.println("Name="+name+" actionState="+actionState);
		ModelAndView mv=new ModelAndView();
		List<Emp> employees=service.getAllEmpsWithSameNames(name,actionState);
		System.out.println("Printing Employees with simi names in /read-employees-with-simi-names");
		for(int i=0;i<employees.size();i++)
		{
			System.out.println(employees.get(i).getFirstName());
		}
		mv.setViewName("emp-names");
		mv.addObject("employees",employees);
		mv.addObject("length",employees.size());
		mv.addObject("action",actionState);
		return mv;
	}
	
	@RequestMapping("/list")
	public ModelAndView list() throws ServletException, IOException, SQLException 
	{
		ModelAndView mv=new ModelAndView();
		List<Emp> employees=service.getAllEmployees();
		mv.setViewName("employee-list");
		mv.addObject("employees",employees);
		return mv;
	}
	
//	@PostMapping("/login")
//	private ModelAndView loginAdmin(HttpServletRequest request) throws IOException
//	{ 
//		String name=request.getParameter("admin");
//		String pass=request.getParameter("pass");
//		ModelAndView mv=new ModelAndView();
//		if(name.equals("naga") && pass.equals("123"))
//		{
//			HttpSession session=request.getSession();
//			session.setAttribute("admin","activeState");
//			mv.setViewName("home");
//		}
//		else
//		{
//			mv.addObject("msg", 1);
//			mv.setViewName("index");
//		}
//		return mv;
//	}
//	
//	@RequestMapping("/logout")
//	private String logoutAdmin(HttpServletRequest request) throws IOException {
//		HttpSession session=request.getSession();
//		session.removeAttribute("admin");
//		session.invalidate();
//		return "index";
//	}
	@GetMapping("/")
	private ModelAndView startMethod()
	{
		System.out.println("going to involk home page");
		return new ModelAndView("home");
	}
//	@RequestMapping("return-to-home")
//	private ModelAndView returnHome()
//	{
//		ModelAndView mv=new ModelAndView();
//		mv.setViewName("home");
//		System.out.println("In 'return-to-home':going to involk home page");
//		return mv;
//	}
}

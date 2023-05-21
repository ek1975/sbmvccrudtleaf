package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data
	private EmployeeService es;

	@Autowired
	public EmployeeController(EmployeeService es) {
		this.es = es;
	}

	//adding mapping for add employee
	@GetMapping("/showEmpForm")
	public String addEmployee(Model m) {
		Employee e = new Employee();
		m.addAttribute("employee", e);
		return "/employees/emp-form";
	}

	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model m) {
		List<Employee> el = es.findAll();
		// add to the spring model
		m.addAttribute("employees", el);
		return "employees/list-employees";
	}

	//save employee to DB
	@PostMapping("/save")
	public String saveEmp (@ModelAttribute("employee") Employee e) {
		es.save(e);
		return "redirect:/employees/list";
	}

	@GetMapping("/showEmp4Upd")
	public String showEmp4Upd(@RequestParam("empId") int id, Model m) {
		Employee e = es.findById(id);
		m.addAttribute("employee", e);
		return "/employees/emp-form";
	}

	@GetMapping("/delEmp")
	public String delEmp(@RequestParam("empId") int id) {
		es.deleteById(id);
		return "redirect:/employees/list";
	}

    /*  Commented out because we're retrieving employees from the database.
	private List<Employee> theEmployees;

	@PostConstruct
	private void loadData() {

		// create employees
		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");

		// create the list
		theEmployees = new ArrayList<>();

		// add to the list
		theEmployees.add(emp1);
		theEmployees.add(emp2);
		theEmployees.add(emp3);
	}
*/
}

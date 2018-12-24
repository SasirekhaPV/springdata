package com.softedge.springdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.softedge.springdata.exception.EmployeeNotFoundException;
import com.softedge.springdata.model.Employee;
import com.softedge.springdata.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.addEmployee(employee), HttpStatus.OK);
	}
	
	@PutMapping("/employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee), HttpStatus.OK);
	}

	@GetMapping("/employees")	
	public ResponseEntity<List<Employee>> findAllEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int employeeId) {
		ResponseEntity<Employee> response = null;
		try {
			employeeService.deleteEmployee(employeeId);
			response = new ResponseEntity<>(HttpStatus.OK);
		}
		catch(EmployeeNotFoundException e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable int employeeId) {
		ResponseEntity<Employee> response = null;
		try {
			response = new ResponseEntity<Employee>(employeeService.findById(employeeId), HttpStatus.OK);
		}
		catch(EmployeeNotFoundException e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
}









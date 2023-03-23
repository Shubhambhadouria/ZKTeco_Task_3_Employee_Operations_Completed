package com.zkteco.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.entity.Employee;
import com.zkteco.entity.Result;
import com.zkteco.exceptions.EmployeeException;
import com.zkteco.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	
	@Autowired
	EmployeeService employeeService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@PostMapping("/employee")
	public Result createEmployeeController(@Valid @RequestBody Employee employee, HttpServletRequest request) {
		LOGGER.info("Inside Create Employee Controller");
		return employeeService.createEmployee(employee, request);
	}
	
	@PostMapping("/employeelist")
	public Result createEmployeeBatchController(@Valid @RequestBody List<Employee> employees, HttpServletRequest request) {
		LOGGER.info("Inside Batch Create Employee Controller");
		return employeeService.createBatchEmployee(employees, request);
	}
	
	@PutMapping("/employee/{id}")
	public Result updateEmployeeByIdController(@PathVariable("id") String Id,
											@RequestBody Employee employee, HttpServletRequest request) throws EmployeeException {
		LOGGER.info("Inside Get Employee By Id Controller");
	    return employeeService.updateEmployeeById(employee, Id, request);
	}
	 
	@GetMapping("/searchbyid/{id}")
	public Result getEmployeeByIdController(@PathVariable("id") String id, HttpServletRequest request) throws EmployeeException {
		LOGGER.info("Inside getByIdorEmailorPhoneController Method");
		return employeeService.fetchEmployeeDetailsById(id, request);
	}
	
	@GetMapping("/searchbyemail/{email}")
	public Result getEmployeeByEmailController(@PathVariable("email") String email, HttpServletRequest request) throws EmployeeException {
		LOGGER.info("Inside getByIdorEmailorPhoneController Method");
		return employeeService.fetchEmployeeDetailsByEmail(email, request);
	}
	
	@GetMapping("/searchbyphone/{phone}")
	public Result getEmployeeByPhoneController(@PathVariable("phone") String phone, HttpServletRequest request) throws EmployeeException {
		LOGGER.info("Inside getByIdorEmailorPhoneController Method");
		return employeeService.fetchEmployeeDetailsByPhone(phone, request);
	}
	
	@GetMapping("/allemployees")
	public Result fetchAllEmployeesController(HttpServletRequest request) throws EmployeeException {
		LOGGER.info("Inside Fetch Employee List Controller");
		return employeeService.fetchAllEmployee(request);
	}
	
	@GetMapping("/employee/{date1}/{date2}")
	public Result fetchAllEmployeesBetweenDatesController(@PathVariable("date1") LocalDate startDate,
														  @PathVariable("date2") LocalDate endDate, HttpServletRequest request) throws EmployeeException {
		LOGGER.info("Inside Fetch Employee By Created Dates Controller");
		return employeeService.fetchAllEmployeeCreatedBetweenDates(startDate, endDate, request);
	}
	
	@DeleteMapping("/employee/{id}")
	public Result deleteEmployeeById(@PathVariable("id") String id, HttpServletRequest request) throws EmployeeException {
		LOGGER.info("Inside Delete Employee By Employee Id Controller");
		return employeeService.deleteEmployeeById(id, request);
	}
	
}

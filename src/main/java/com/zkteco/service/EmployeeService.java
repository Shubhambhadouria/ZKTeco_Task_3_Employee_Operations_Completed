package com.zkteco.service;

import java.time.LocalDate;
import java.util.List;

import com.zkteco.entity.Employee;
import com.zkteco.entity.Result;
import com.zkteco.exceptions.EmployeeException;

import jakarta.servlet.http.HttpServletRequest;

public interface EmployeeService {
	
	public Result createEmployee(Employee employee, HttpServletRequest request);
	
	public Result createBatchEmployee(List<Employee> employees, HttpServletRequest request);
	
	public Result updateEmployeeById(Employee employee, String empId, HttpServletRequest request) throws EmployeeException;
	
	public Result fetchEmployeeDetailsById(String id, HttpServletRequest request)  throws EmployeeException;
	
	public Result fetchEmployeeDetailsByEmail(String email, HttpServletRequest request) throws EmployeeException;
	
	public Result fetchEmployeeDetailsByPhone(String phone, HttpServletRequest request) throws EmployeeException;
	
	public Result fetchAllEmployee(HttpServletRequest request)  throws EmployeeException;
	
	public Result fetchAllEmployeeCreatedBetweenDates(LocalDate startDate, LocalDate endDate, HttpServletRequest request)  throws EmployeeException;
	
	public Result deleteEmployeeById(String empId, HttpServletRequest request)  throws EmployeeException;

}

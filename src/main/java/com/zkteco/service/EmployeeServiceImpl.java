package com.zkteco.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.zkteco.config.MyLocaleResolver;
import com.zkteco.entity.Employee;
import com.zkteco.entity.Result;
import com.zkteco.exceptions.EmployeeException;
import com.zkteco.repository.EmployeeRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MyLocaleResolver myLocaleResolver;
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Result createEmployee(Employee employee, HttpServletRequest request) {
		return validationService.validation(employee, request);
	}

	@Override
	public Result createBatchEmployee(List<Employee> employees, HttpServletRequest request) {
		return validationService.validationBatch(employees, request);
	}

	@Override
	public Result updateEmployeeById(Employee employee, String empId, HttpServletRequest request) throws EmployeeException {
		Optional<Employee> opt = employeeRepository.findById(empId);
		if(opt.isPresent()) {
			return validationService.updateValidation(employee,empId, request);
		}else {
			throw new EmployeeException(messageSource.getMessage("message53", null, myLocaleResolver.resolveLocale(request))+empId);
		}
	}

	@Override
	public Result fetchEmployeeDetailsById(String id, HttpServletRequest request) throws EmployeeException {
		Employee employee = employeeRepository.findById(id).get();
		if(employee!=null) {
			return new Result("OK",messageSource.getMessage("message54", null, myLocaleResolver.resolveLocale(request)),employee);
		}else {
			throw new EmployeeException(messageSource.getMessage("message55", null, myLocaleResolver.resolveLocale(request)));
		}
	}
	
	@Override
	public Result fetchEmployeeDetailsByEmail(String email, HttpServletRequest request) throws EmployeeException {
		Employee employee = employeeRepository.findByEmail(email);
		if(employee!=null) {
			return new Result("OK",messageSource.getMessage("message54", null, myLocaleResolver.resolveLocale(request)),employee);
		}else {
			throw new EmployeeException(messageSource.getMessage("message55", null, myLocaleResolver.resolveLocale(request)));
		}
	}
	
	@Override
	public Result fetchEmployeeDetailsByPhone(String phone, HttpServletRequest request) throws EmployeeException {
		Employee employee = employeeRepository.findByPhone(phone);
		if(employee!=null) {
			return new Result("OK",messageSource.getMessage("message56", null, myLocaleResolver.resolveLocale(request)),employee);
		}else {
			throw new EmployeeException(messageSource.getMessage("message55", null, myLocaleResolver.resolveLocale(request)));
		}
	}

	@Override
	public Result fetchAllEmployee(HttpServletRequest request) throws EmployeeException {
		List<Employee> employees = employeeRepository.findAll();
		if(employees.size()==0) {
			throw new EmployeeException(messageSource.getMessage("message55", null, myLocaleResolver.resolveLocale(request)));
		}else {
			return new Result("OK",messageSource.getMessage("message57", null, myLocaleResolver.resolveLocale(request)),employees);
		}
		
	}

	@Override
	public Result fetchAllEmployeeCreatedBetweenDates(LocalDate startDate, LocalDate endDate, HttpServletRequest request) throws EmployeeException {

		List<Employee> employees = employeeRepository.findByCreateDateBetween(startDate, endDate);
		
		if(employees.size()!=0) {
			return new Result("OK",messageSource.getMessage("message58", null, myLocaleResolver.resolveLocale(request)),employees);
		}else {
			throw new EmployeeException(messageSource.getMessage("message55", null, myLocaleResolver.resolveLocale(request)));
		}

	}

	@Override
	public Result deleteEmployeeById(String empId, HttpServletRequest request) throws EmployeeException {
		
		Optional<Employee> opt =  employeeRepository.findById(empId);
		
		if(opt.isPresent()) {
			employeeRepository.deleteById(empId);
			return new Result("OK",messageSource.getMessage("message59", null, myLocaleResolver.resolveLocale(request))+empId,opt.get());
		}else {
			throw new EmployeeException(messageSource.getMessage("message53", null, myLocaleResolver.resolveLocale(request))+empId);
		}
	}

	
}

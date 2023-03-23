package com.zkteco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.zkteco.config.MyLocaleResolver;
import com.zkteco.entity.Department;
import com.zkteco.entity.Result;
import com.zkteco.exceptions.DepartmentException;
import com.zkteco.repository.DepartmentRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MyLocaleResolver myLocaleResolver;
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public Result createDepartment(Department department, HttpServletRequest request) {
		return validationService.validationDepartment(department, request);
	}

	@Override
	public Result createBatchDepartment(List<Department> departments, HttpServletRequest request) {
		return validationService.validationBatchDepartment(departments, request);
	}

	@Override
	public Result updateDepartmentById(Department department, String deptId, HttpServletRequest request) throws DepartmentException {
		Optional<Department> opt = departmentRepository.findById(deptId);
		if(opt.isPresent()) {
			return validationService.updateDepartmentValidation(department, deptId, request);
		}else {
			throw new DepartmentException(messageSource.getMessage("message60", null, myLocaleResolver.resolveLocale(request))+deptId);
		}
	}

	@Override
	public Result fetchDepartmentDetailsById(String deptId, HttpServletRequest request) throws DepartmentException {
		Optional<Department> opt = departmentRepository.findById(deptId);
		if(opt.isPresent()) {
			return new Result("OK",messageSource.getMessage("message61", null, myLocaleResolver.resolveLocale(request)),opt);
		}else {
			throw new DepartmentException(messageSource.getMessage("message61", null, myLocaleResolver.resolveLocale(request))+deptId);
		}
	}

	@Override
	public Result fetchAllDepartment(HttpServletRequest request) throws DepartmentException {
		List<Department> departments = departmentRepository.findAll();
		if(departments.size()!=0) {
			return new Result("OK",messageSource.getMessage("message62", null, myLocaleResolver.resolveLocale(request)),departments);
		} else {
			throw new DepartmentException(messageSource.getMessage("message63", null, myLocaleResolver.resolveLocale(request)));
		}
	}

	@Override
	public Result deleteDepartmentById(String deptId, HttpServletRequest request) throws DepartmentException {
		Optional<Department> opt =  departmentRepository.findById(deptId);
		if(opt.isPresent()) {
			departmentRepository.deleteById(deptId);
			return new Result("OK",messageSource.getMessage("message64", null, myLocaleResolver.resolveLocale(request))+deptId,opt.get());
		}else {
			throw new DepartmentException(messageSource.getMessage("message60", null, myLocaleResolver.resolveLocale(request))+deptId);
		}
	}
	
}

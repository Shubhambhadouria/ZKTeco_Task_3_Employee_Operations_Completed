package com.zkteco.controller;
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

import com.zkteco.entity.Department;
import com.zkteco.entity.Result;
import com.zkteco.exceptions.DepartmentException;
import com.zkteco.service.DepartmentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@PostMapping("/department")
	public Result createDepartmentController(@Valid @RequestBody Department department, HttpServletRequest request) {
		LOGGER.info("Inside Create Department Controller");
		return departmentService.createDepartment(department, request);
	}
	
	@PostMapping("/departmentlist")
	public Result createDepartmentBatchController(@RequestBody List<Department> department, HttpServletRequest request) {
		LOGGER.info("Inside Create Batch Department Controller");
		return departmentService.createBatchDepartment(department, request);
	}
	
	@PutMapping("/department/{id}")
	public Result updateDepartmentByIdController(@PathVariable("id") String Id,
											  @RequestBody Department department, HttpServletRequest request) throws DepartmentException {
		LOGGER.info("Inside Update Department By DepartmentId Controller");
	    return departmentService.updateDepartmentById(department, Id, request);
	}
	
	@GetMapping("/department/{id}")
	public Result getDepartmentByIdController(@PathVariable("id") String Id, HttpServletRequest request) throws DepartmentException {
		LOGGER.info("Inside Fetch Department By DepartmentId Controller");
		return departmentService.fetchDepartmentDetailsById(Id, request);
	}
	
	@GetMapping("/alldepartment")
	public Result fetchAllDepartmentController(HttpServletRequest request) throws DepartmentException {
		LOGGER.info("Inside Fetch All Department List Controller");
		return departmentService.fetchAllDepartment(request);
	}
	
	@DeleteMapping("/department/{id}")
	public Result deleteDepartmentById(@PathVariable("id") String id, HttpServletRequest request) throws DepartmentException {
		LOGGER.info("Inside Delete Department By DepartmentId Controller");
		return departmentService.deleteDepartmentById(id, request);
	}
	
}

package com.zkteco.service;

import java.util.List;

import com.zkteco.entity.Department;
import com.zkteco.entity.Result;
import com.zkteco.exceptions.DepartmentException;

import jakarta.servlet.http.HttpServletRequest;

public interface DepartmentService {

	public Result createDepartment(Department department, HttpServletRequest request);
	
	public Result createBatchDepartment(List<Department> departments, HttpServletRequest request);
	
	public Result updateDepartmentById(Department employee, String empId, HttpServletRequest request) throws DepartmentException;
	
	public Result fetchDepartmentDetailsById(String deptId, HttpServletRequest request) throws DepartmentException;
	
	public Result fetchAllDepartment(HttpServletRequest request) throws DepartmentException;
	
	public Result deleteDepartmentById(String deptId, HttpServletRequest request) throws DepartmentException;


	
}

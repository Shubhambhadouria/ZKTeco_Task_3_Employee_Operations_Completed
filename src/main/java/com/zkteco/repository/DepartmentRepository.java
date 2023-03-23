package com.zkteco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zkteco.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String>{

	public Department findByDeptCode(String deptCode);
	
	public Department findByDeptName(String deptName);
	
}

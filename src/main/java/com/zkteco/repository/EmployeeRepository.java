package com.zkteco.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zkteco.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

	public Employee findByEmail(String email);
	
	public Employee findByPhone(String phone);
	
	@Query("select u from Employee u where u.id=?1 or u.email=?2 or u.phone=?3")
	public Employee findByIdOrEmailOrPhone(String userId, String email, String phone);

	@Query("select x from Employee x where x.empCreatedDate between ?1 and ?2")
	public List<Employee> findByCreateDateBetween(LocalDate Date1, LocalDate Date2);

}

package com.zkteco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zkteco.entity.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, String>{

	public Designation findByDesCode(String desCode);
	
	public Designation findByDesName(String desName);

}

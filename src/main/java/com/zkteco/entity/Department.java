package com.zkteco.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {

	@Id
	private String deptId;
	
	@Column(name = "department_name")
	private String deptName;
	
	@Column(name = "department_code")
	private String deptCode;
	
	@Column(name = "department_create_date")
	private LocalDate deptCreateDate;
	
	@Column(name = "department_update_date")
	private LocalDate deptUpdateDate;
	
}

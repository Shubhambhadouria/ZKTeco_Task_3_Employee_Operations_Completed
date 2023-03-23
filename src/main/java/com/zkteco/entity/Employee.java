package com.zkteco.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

	@Id
	@Column(name = "employee_id")
	private String id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column(name = "profile_photo")
	private Boolean profilePhoto;
	
	@Column(name = "create_date")
	private LocalDate empCreatedDate;
	
	@Column(name = "update_date")
	private LocalDate empUpdateDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "departmentId",
			referencedColumnName = "deptId"
	)
	private Department department;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "deignation_id",
			referencedColumnName = "desId"
			
	)
	private Designation designation;
	
}

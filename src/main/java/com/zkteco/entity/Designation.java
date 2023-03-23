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
public class Designation {

	@Id
	private String desId;
	
	@Column(name = "designation_name")
	private String desName;
	
	@Column(name = "designation_code")
	private String desCode;
	
	@Column(name = "designation_create_date")
	private LocalDate desCreateDate;
	
	@Column(name = "designation_update_date")
	private LocalDate desUpdateDate;
	
}

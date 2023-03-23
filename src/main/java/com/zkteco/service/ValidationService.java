package com.zkteco.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.zkteco.config.MyLocaleResolver;
import com.zkteco.entity.Department;
import com.zkteco.entity.Designation;
import com.zkteco.entity.Employee;
import com.zkteco.entity.Result;
import com.zkteco.repository.DepartmentRepository;
import com.zkteco.repository.DesignationRepository;
import com.zkteco.repository.EmployeeRepository;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ValidationService {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MyLocaleResolver myLocaleResolver;

	//-------------------------------------------VALIDATIONS FOR EMPLOYEE STARTS HERE-----------------------------------------------//
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Result validation(Employee employee, HttpServletRequest request) {
		
		// Validation For Id
		
		if(Objects.nonNull(employee.getId()) && !employee.getId().equalsIgnoreCase("")) {
			if((employee.getId().length()<37 && employee.getId().length()>0)) {
				if(employeeRepository.findById(employee.getId()).isPresent()) {
					return new Result("UIP",messageSource.getMessage("message22", null, myLocaleResolver.resolveLocale(request))+employee.getId(),null);
				}
			}else {
				return new Result("EIL",messageSource.getMessage("message23", null, myLocaleResolver.resolveLocale(request)),null);
			}	
		} else {
			return new Result("UIE",messageSource.getMessage("message24", null, myLocaleResolver.resolveLocale(request)),null);
		}
			
		
		
		// Validation For First Name
		
		if(Objects.nonNull(employee.getFirstName()) && !employee.getFirstName().equalsIgnoreCase("")) {
			if(!(employee.getFirstName().length()<51 && employee.getFirstName().length()>0)) {
				return new Result("FNL",messageSource.getMessage("message25", null, myLocaleResolver.resolveLocale(request)),null);
			}
		} else {
		 	return new Result("FNL",messageSource.getMessage("message26", null, myLocaleResolver.resolveLocale(request)),null);
		}
		
		
		
		// Validation For Last Name
		
		if(Objects.nonNull(employee.getLastName()) && !employee.getLastName().equalsIgnoreCase("")) {
			if((employee.getLastName().length()>50)) {
				return new Result("LNL",messageSource.getMessage("message27", null, myLocaleResolver.resolveLocale(request)),null);
			}
		}
		
		
		
		// Validation For Gender
		
		if(Objects.nonNull(employee.getGender()) && !employee.getGender().equalsIgnoreCase("")){
			if(!employee.getGender().matches("(?:[M|F|O])")) {
				return new Result("G",messageSource.getMessage("message28", null, myLocaleResolver.resolveLocale(request)),null);
			}
		}
			
		
		
		// Validation For Email
				
		if(Objects.nonNull(employee.getEmail()) && !employee.getEmail().equals("")) {
			if(employee.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[.]+[a-zA-Z]{2,}")) {
				if(employeeRepository.findByEmail(employee.getEmail())!=null) {
					return new Result("EAP",messageSource.getMessage("message29", null, myLocaleResolver.resolveLocale(request)),null);
				}
			} else {
				return new Result("ER",messageSource.getMessage("message37", null, myLocaleResolver.resolveLocale(request)),null);
			}	
		} else {
			return new Result("EE",messageSource.getMessage("message30", null, myLocaleResolver.resolveLocale(request)),null);
		}
				
		
					
		// Validation For Phone
				
		if(Objects.nonNull(employee.getPhone()) &&  !employee.getPhone().equals("")) {
			if(employee.getPhone().matches("[\\+]+[0-9]{2}+[-]+[0-9]{10}")) {
				if(employeeRepository.findByPhone(employee.getPhone())!=null) {
					return new Result("PAP",messageSource.getMessage("message31", null, myLocaleResolver.resolveLocale(request)),null);
				}
			} else {
				return new Result("PR",messageSource.getMessage("message32", null, myLocaleResolver.resolveLocale(request)),null);
			}
		} else {
			return new Result("PE",messageSource.getMessage("message33", null, myLocaleResolver.resolveLocale(request)),null);
		}
				
				
				
		// Validation For Password
				
		if(Objects.nonNull(employee.getPassword()) && !employee.getPassword().equals("")) {
			if(!employee.getPassword().matches("^(?=.*[A-Z])[A-Za-z0-9].{8,16}")) {
				return new Result("E",messageSource.getMessage("message34", null, myLocaleResolver.resolveLocale(request)),null);
			}
		} else {
			return new Result("PE",messageSource.getMessage("message35", null, myLocaleResolver.resolveLocale(request)),null);
		}
		
		employee.setDepartment(departmentRepository.findById(employee.getDepartment().getDeptId()).get());;
		employee.setDesignation(designationRepository.findById(employee.getDesignation().getDesId()).get());
		employeeRepository.save(employee);
		return new Result("OK",messageSource.getMessage("message36", null, myLocaleResolver.resolveLocale(request)),employee);
		
		
		
	}
	
	public Result validationBatch(List<Employee> employees, HttpServletRequest request) {
		for(int i=0; i<employees.size(); i++) {
			
			
			
			// Validation For Id
			
			if(Objects.nonNull(employees.get(i).getId()) && !employees.get(i).getId().equalsIgnoreCase("")) {	
				if((employees.get(i).getId().length()<37 && employees.get(i).getId().length()>0)) {
					if(employeeRepository.findById(employees.get(i).getId()).isPresent()) {
						return new Result("UIP",messageSource.getMessage("message22", null, myLocaleResolver.resolveLocale(request))+employees.get(i).getId(),null);
					}
				} else {
					return new Result("EIL",messageSource.getMessage("message23", null, myLocaleResolver.resolveLocale(request)),null);
				}
			} else {
				return new Result("UIE",messageSource.getMessage("message24", null, myLocaleResolver.resolveLocale(request)),null);
			}
				
			
			
			// Validation For First Name
			
			if(Objects.nonNull(employees.get(i).getFirstName()) && !employees.get(i).getFirstName().equalsIgnoreCase("")) {	
				if(!(employees.get(i).getFirstName().length()<51 && employees.get(i).getFirstName().length()>0)) {
					return new Result("FNL",messageSource.getMessage("message25", null, myLocaleResolver.resolveLocale(request)),null);
				}
			} else {
			 	return new Result("FNL",messageSource.getMessage("message26", null, myLocaleResolver.resolveLocale(request)),null);
			}
			
			
			
			// Validation For Last Name
			
			if(Objects.nonNull(employees.get(i).getLastName()) && !employees.get(i).getLastName().equalsIgnoreCase("")) {
				if((employees.get(i).getLastName().length()>50)) {
					return new Result("LNL",messageSource.getMessage("message27", null, myLocaleResolver.resolveLocale(request)),null);
				}
			}
					
			
			
			// Validation For Gender
			
			if(Objects.nonNull(employees.get(i).getGender()) && !employees.get(i).getGender().equalsIgnoreCase("")){
				if(!employees.get(i).getGender().matches("(?:[M|F|O])")) {
					return new Result("G",messageSource.getMessage("message28", null, myLocaleResolver.resolveLocale(request)),null);
				}
			}
				
			
			
			// Validation For Email
					
			if(Objects.nonNull(employees.get(i).getEmail()) && !employees.get(i).getEmail().equals("")) {
				if(employees.get(i).getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[.]+[a-zA-Z]{2,}")) {
					if(employeeRepository.findByEmail(employees.get(i).getEmail())!=null) {
						return new Result("EAP",messageSource.getMessage("message29", null, myLocaleResolver.resolveLocale(request)),null);
					}
				} else {
					return new Result("ER",messageSource.getMessage("message37", null, myLocaleResolver.resolveLocale(request)),null);
				}
			} else {
				return new Result("EE",messageSource.getMessage("message30", null, myLocaleResolver.resolveLocale(request)),null);
			}
					
			
						
			// Validation For Phone
					
			if(Objects.nonNull(employees.get(i).getPhone()) &&  !employees.get(i).getPhone().equals("")) {
				if(employees.get(i).getPhone().matches("[\\+]+[0-9]{2}+[-]+[0-9]{10}")) {
					if(employeeRepository.findByPhone(employees.get(i).getPhone())!=null) {
						return new Result("PAP",messageSource.getMessage("message31", null, myLocaleResolver.resolveLocale(request)),null);
					}
				} else {
					return new Result("PR",messageSource.getMessage("message32", null, myLocaleResolver.resolveLocale(request)),null);
				}
			} else {
				return new Result("PE",messageSource.getMessage("message33", null, myLocaleResolver.resolveLocale(request)),null);
			}
					
			
					
			// Validation For Password
					
			if(Objects.nonNull(employees.get(i).getPassword()) && !employees.get(i).getPassword().equals("")) {
				if(!employees.get(i).getPassword().matches("^(?=.*[A-Z])[A-Za-z0-9].{8,16}")) {
					return new Result("E",messageSource.getMessage("message34", null, myLocaleResolver.resolveLocale(request)),null);
				}
			} else {
				return new Result("PE",messageSource.getMessage("message35", null, myLocaleResolver.resolveLocale(request)),null);
			}
		}
		employeeRepository.saveAll(employees);
		return new Result("DONE",messageSource.getMessage("message51", null, myLocaleResolver.resolveLocale(request)),employees);
		
	}
	
	public Result updateValidation(Employee employee, String empId, HttpServletRequest request) {
		
		Employee employee2 = employeeRepository.findById(empId).get();
		
		// Validation For First Name
		
		if(Objects.nonNull(employee.getFirstName()) && !employee.getFirstName().equalsIgnoreCase("")) {			
			if(!(employee.getFirstName().length()<51 && employee.getFirstName().length()>0)) {
				return new Result("FNL",messageSource.getMessage("message25", null, myLocaleResolver.resolveLocale(request)),null);
			} 
		} 
				
		
		// Validation For Last Name
				
		if(Objects.nonNull(employee.getLastName()) && !employee.getLastName().equalsIgnoreCase("")) {
			if((employee.getLastName().length()>50)) {
				return new Result("LNL",messageSource.getMessage("message27", null, myLocaleResolver.resolveLocale(request)),null);
			}
		}
							
		// Validation For Gender
				
		if(Objects.nonNull(employee.getGender()) && !employee.getGender().equalsIgnoreCase("")){
			if(!employee.getGender().matches("(?:[M|F|O])")) {
				return new Result("G",messageSource.getMessage("message28", null, myLocaleResolver.resolveLocale(request)),null);
			}
		}
					
		// Validation For Email
						
		if(Objects.nonNull(employee.getEmail()) && !employee.getEmail().equals("")) {	
			if(employee.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[.]+[a-zA-Z]{2,}")) {		
				if(employeeRepository.findByEmail(employee.getEmail())!=null) {
					return new Result("EAP",messageSource.getMessage("message29", null, myLocaleResolver.resolveLocale(request)),null);
				} else {
					employee2.setEmail(employee.getEmail());
				}	
			} else {
				return new Result("ER",messageSource.getMessage("message37", null, myLocaleResolver.resolveLocale(request)),null);
			}			
		} 
								
						
			
		// Validation For Phone
						
		if(Objects.nonNull(employee.getPhone()) &&  !employee.getPhone().equals("")) {
			if(employee.getPhone().matches("[\\+]+[0-9]{2}+[-]+[0-9]{10}")) {		
				if(employeeRepository.findByPhone(employee.getPhone())!=null) {
					return new Result("PAP",messageSource.getMessage("message31", null, myLocaleResolver.resolveLocale(request)),null);
				}		
			} else {
				return new Result("PR",messageSource.getMessage("message32", null, myLocaleResolver.resolveLocale(request)),null);
			}	
		} 
						
				
									
		// Validation For Password
						
		if(Objects.nonNull(employee.getPassword()) && !employee.getPassword().equals("")) {
			if(!employee.getPassword().matches("^(?=.*[A-Z])[A-Za-z0-9].{8,16}")) {
				return new Result("E",messageSource.getMessage("message34", null, myLocaleResolver.resolveLocale(request)),null);
			}
		} 
		
		
		if(Objects.nonNull(employee.getFirstName()) && 
				!"".equalsIgnoreCase(employee.getFirstName())) {
			employee2.setFirstName(employee.getFirstName());
		}
		if(Objects.nonNull(employee.getLastName()) && 
				!"".equalsIgnoreCase(employee.getLastName())) {
			employee2.setLastName(employee.getLastName());
		}
		if(Objects.nonNull(employee.getGender()) && 
				!"".equalsIgnoreCase(employee.getGender())) {
			employee2.setGender(employee.getGender());
		}
		if(Objects.nonNull(employee.getEmail()) && 
				!"".equalsIgnoreCase(employee.getEmail())) {
			employee2.setEmail(employee.getEmail());
		}
		if(Objects.nonNull(employee.getPhone()) && 
				!"".equalsIgnoreCase(employee.getPhone())) {
			employee2.setPhone(employee.getPhone());
		}
		if(Objects.nonNull(employee.getPassword()) && 
				!"".equalsIgnoreCase(employee.getPassword())) {
			employee2.setPassword(employee.getPassword());
		}
		if(Objects.nonNull(employee.getDateOfBirth())) {
			employee2.setDateOfBirth(employee.getDateOfBirth());
		}
		if(Objects.nonNull(employee.getProfilePhoto())) {
			employee2.setProfilePhoto(employee.getProfilePhoto());
		}
		if(Objects.nonNull(employee.getEmpCreatedDate())) {
			employee2.setEmpCreatedDate(employee.getEmpCreatedDate());
		}
		if(Objects.nonNull(employee.getEmpUpdateDate())) {
			employee2.setEmpUpdateDate(employee.getEmpUpdateDate());
		}
				
		employeeRepository.save(employee2);
		return new Result("OK",messageSource.getMessage("message38", null, myLocaleResolver.resolveLocale(request)),employee2);
	}
	
	//-------------------------------------------VALIDATIONS FOR EMPLOYEE ENDS HERE---------------------------------------------------//
	
	
	//-------------------------------------------VALIDATIONS FOR DEPARTMENT STARTS HERE-----------------------------------------------//
	
	@Autowired
	DepartmentRepository departmentRepository;
		
	
	public Result validationDepartment(Department department, HttpServletRequest request) {
	
		// Validation For Id
		
		if(Objects.nonNull(department.getDeptId()) && !department.getDeptId().equalsIgnoreCase("")) {
			if((department.getDeptId().length()<37 && department.getDeptId().length()>0)) {
				if(departmentRepository.findById(department.getDeptId()).isPresent()) {
					return new Result("DIP",messageSource.getMessage("message39", null, myLocaleResolver.resolveLocale(request))+department.getDeptId(),null);
				}
			}else {
				return new Result("DIL",messageSource.getMessage("message40", null, myLocaleResolver.resolveLocale(request)),null);
			}	
		} else {
			return new Result("DIE",messageSource.getMessage("message41", null, myLocaleResolver.resolveLocale(request)),null);
		}
			
		
		
		// Validation For Code
		
		if(Objects.nonNull(department.getDeptCode()) && !department.getDeptCode().equalsIgnoreCase("")) {
			if((department.getDeptCode().length()<31 && department.getDeptCode().length()>0)) {
				if(departmentRepository.findByDeptCode(department.getDeptCode())!=null) {
					return new Result("DIP",messageSource.getMessage("message42", null, myLocaleResolver.resolveLocale(request))+department.getDeptCode(),null);
				}
			}else {
				return new Result("DIL",messageSource.getMessage("message43", null, myLocaleResolver.resolveLocale(request)),null);
			}	
		} else {
			return new Result("DIE",messageSource.getMessage("message44", null, myLocaleResolver.resolveLocale(request)),null);
		}
		
		
		
		// Validation For Name
		
		if(Objects.nonNull(department.getDeptName()) && !department.getDeptName().equalsIgnoreCase("")) {
			if((department.getDeptName().length()<51 && department.getDeptName().length()>0)) {
				if(departmentRepository.findByDeptName(department.getDeptName())!=null) {
					return new Result("DIP",messageSource.getMessage("message45", null, myLocaleResolver.resolveLocale(request))+department.getDeptName(),null);
				}
			}else {
				return new Result("DIL",messageSource.getMessage("message46", null, myLocaleResolver.resolveLocale(request)),null);
			}	
		} else {
			return new Result("DIE",messageSource.getMessage("message47", null, myLocaleResolver.resolveLocale(request)),null);
		}
		
		departmentRepository.save(department);
		return new Result("OK",messageSource.getMessage("message49", null, myLocaleResolver.resolveLocale(request)),department);
	}
	
	public Result validationBatchDepartment(List<Department> departments, HttpServletRequest request) {
		
		for(int i=0; i<departments.size(); i++) {
			
			// Validation For Id
			
			if(Objects.nonNull(departments.get(i).getDeptId()) && !departments.get(i).getDeptId().equalsIgnoreCase("")) {
				if((departments.get(i).getDeptId().length()<37 && departments.get(i).getDeptId().length()>0)) {
					if(departmentRepository.findById(departments.get(i).getDeptId()).isPresent()) {
						return new Result("DIP",messageSource.getMessage("message39", null, myLocaleResolver.resolveLocale(request))+departments.get(i).getDeptId(),null);
					}
				}else {
					return new Result("DIL",messageSource.getMessage("message40", null, myLocaleResolver.resolveLocale(request)),null);
				}	
			} else {
				return new Result("DIE",messageSource.getMessage("message41", null, myLocaleResolver.resolveLocale(request)),null);
			}
				
			
			
			// Validation For Code
			
			if(Objects.nonNull(departments.get(i).getDeptCode()) && !departments.get(i).getDeptCode().equalsIgnoreCase("")) {
				if((departments.get(i).getDeptCode().length()<31 && departments.get(i).getDeptCode().length()>0)) {
					if(departmentRepository.findByDeptCode(departments.get(i).getDeptCode())!=null) {
						return new Result("DIP",messageSource.getMessage("message42", null, myLocaleResolver.resolveLocale(request))+departments.get(i).getDeptCode(),null);
					}
				}else {
					return new Result("DIL",messageSource.getMessage("message43", null, myLocaleResolver.resolveLocale(request)),null);
				}	
			} else {
				return new Result("DIE",messageSource.getMessage("message44", null, myLocaleResolver.resolveLocale(request)),null);
			}
			
			
			
			// Validation For Name
			
			if(Objects.nonNull(departments.get(i).getDeptName()) && !departments.get(i).getDeptName().equalsIgnoreCase("")) {
				if((departments.get(i).getDeptName().length()<51 && departments.get(i).getDeptName().length()>0)) {
					if(departmentRepository.findByDeptName(departments.get(i).getDeptName())!=null) {
						return new Result("DIP",messageSource.getMessage("message45", null, myLocaleResolver.resolveLocale(request))+departments.get(i).getDeptName(),null);
					}
				}else {
					return new Result("DIL",messageSource.getMessage("message46", null, myLocaleResolver.resolveLocale(request)),null);
				}	
			} else {
				return new Result("DIE",messageSource.getMessage("message47", null, myLocaleResolver.resolveLocale(request)),null);
			}
			
		}
		
		
		departmentRepository.saveAll(departments);
		return new Result("OK",messageSource.getMessage("message49", null, myLocaleResolver.resolveLocale(request)),departments);
	}
	
	public Result updateDepartmentValidation(Department department, String deptId, HttpServletRequest request) {
		
		Department department2 = departmentRepository.findById(deptId).get();
			
		// Validation For Code
				
		if(Objects.nonNull(department.getDeptCode()) && !department.getDeptCode().equalsIgnoreCase("")) {
			if((department.getDeptCode().length()<31 && department.getDeptCode().length()>0)) {
				if(departmentRepository.findByDeptCode(department.getDeptCode())!=null) {
					return new Result("DIP",messageSource.getMessage("message42", null, myLocaleResolver.resolveLocale(request))+department.getDeptCode(),null);
				}
			} else {
				return new Result("DIL",messageSource.getMessage("message43", null, myLocaleResolver.resolveLocale(request)),null);
			}	
		} 
				
				
				
		// Validation For Name
				
		if(Objects.nonNull(department.getDeptName()) && !department.getDeptName().equalsIgnoreCase("")) {
			if((department.getDeptName().length()<31 && department.getDeptName().length()>0)) {
				if(departmentRepository.findByDeptName(department.getDeptName())!=null) {
					return new Result("DIP",messageSource.getMessage("message45", null, myLocaleResolver.resolveLocale(request))+department.getDeptName(),null);
				}
			} else {
				return new Result("DIL",messageSource.getMessage("message46", null, myLocaleResolver.resolveLocale(request)),null);
			}	
		}
		
		if(Objects.nonNull(department.getDeptName()) && 
				!"".equalsIgnoreCase(department.getDeptName())) {
			department2.setDeptName(department.getDeptName());
		}
		if(Objects.nonNull(department.getDeptCode()) && 
				!"".equalsIgnoreCase(department.getDeptCode())) {
			department2.setDeptCode(department.getDeptCode());
		}
		if(Objects.nonNull(department.getDeptCreateDate())) {
			department2.setDeptCreateDate(department.getDeptCreateDate());
		}
		if(Objects.nonNull(department.getDeptUpdateDate())) {
			department2.setDeptUpdateDate(department.getDeptUpdateDate());
		}
		
		departmentRepository.save(department2);
		return new Result("OK",messageSource.getMessage("message52", null, myLocaleResolver.resolveLocale(request)),department2);
		
	}
	
	//-------------------------------------------VALIDATIONS FOR DEPARTMENT ENDS HERE-------------------------------------------------//
	
	
	//-------------------------------------------VALIDATIONS FOR DESIGNATION STARTS HERE-----------------------------------------------//
	
	@Autowired
	DesignationRepository designationRepository;
			
	public Result validationDesignation(Designation designation, HttpServletRequest request) {
		
			// Validation For Id
			
			if(Objects.nonNull(designation.getDesId()) && !designation.getDesId().equalsIgnoreCase("")) {
				if((designation.getDesId().length()<37 && designation.getDesId().length()>0)) {
					if(designationRepository.findById(designation.getDesId()).isPresent()) {
						return new Result("DIP",messageSource.getMessage("message2", null, myLocaleResolver.resolveLocale(request))+designation.getDesId(),null);
					}
				}else {
					return new Result("DIL",messageSource.getMessage("message3", null, myLocaleResolver.resolveLocale(request)),null);
				}	
			} else {
				return new Result("DIE",messageSource.getMessage("message4", null, myLocaleResolver.resolveLocale(request)),null);
			}
				
			
			
			// Validation For Code
			
			if(Objects.nonNull(designation.getDesCode()) && !designation.getDesCode().equalsIgnoreCase("")) {
				if((designation.getDesCode().length()<31 && designation.getDesCode().length()>0)) {
					if(designationRepository.findByDesCode(designation.getDesCode())!=null) {
						return new Result("DIP",messageSource.getMessage("message5", null, myLocaleResolver.resolveLocale(request))+designation.getDesCode(),null);
					}
				}else {
					return new Result("DIL",messageSource.getMessage("message6", null, myLocaleResolver.resolveLocale(request)),null);
				}	
			} else {
				return new Result("DIE",messageSource.getMessage("message7", null, myLocaleResolver.resolveLocale(request)),null);
			}
			
			
			
			// Validation For Name
			
			if(Objects.nonNull(designation.getDesName()) && !designation.getDesName().equalsIgnoreCase("")) {
				if((designation.getDesName().length()<51 && designation.getDesName().length()>0)) {
					if(designationRepository.findByDesName(designation.getDesName())!=null) {
						return new Result("DIP",messageSource.getMessage("message8", null, myLocaleResolver.resolveLocale(request))+designation.getDesName(),null);
					}
				}else {
					return new Result("DIL",messageSource.getMessage("message9", null, myLocaleResolver.resolveLocale(request)),null);
				}	
			} else {
				return new Result("DIE",messageSource.getMessage("message10", null, myLocaleResolver.resolveLocale(request)),null);
			}
			
			designationRepository.save(designation);
			return new Result("OK",messageSource.getMessage("message11", null, myLocaleResolver.resolveLocale(request)),designation);
		}
		
	public Result validationBatchDesignation(List<Designation> designations, HttpServletRequest request) {
			
			for(int i=0; i<designations.size(); i++) {
				
				// Validation For Id
				
				if(Objects.nonNull(designations.get(i).getDesId()) && !designations.get(i).getDesId().equalsIgnoreCase("")) {
					if((designations.get(i).getDesId().length()<37 && designations.get(i).getDesId().length()>0)) {
						if(designationRepository.findById(designations.get(i).getDesId()).isPresent()) {
							return new Result("DIP",messageSource.getMessage("message12", null, myLocaleResolver.resolveLocale(request))+designations.get(i).getDesId(),null);
						}
					}else {
						return new Result("DIL",messageSource.getMessage("message13", null, myLocaleResolver.resolveLocale(request)),null);
					}	
				} else {
					return new Result("DIE",messageSource.getMessage("message14", null, myLocaleResolver.resolveLocale(request)),null);
				}
					
				
				
				// Validation For Code
				
				if(Objects.nonNull(designations.get(i).getDesCode()) && !designations.get(i).getDesCode().equalsIgnoreCase("")) {
					if((designations.get(i).getDesCode().length()<31 && designations.get(i).getDesCode().length()>0)) {
						if(designationRepository.findByDesCode(designations.get(i).getDesCode())!=null) {
							return new Result("DIP",messageSource.getMessage("message15", null, myLocaleResolver.resolveLocale(request))+designations.get(i).getDesCode(),null);
						}
					}else {
						return new Result("DIL",messageSource.getMessage("message16", null, myLocaleResolver.resolveLocale(request)),null);
					}	
				} else {
					return new Result("DIE",messageSource.getMessage("message17", null, myLocaleResolver.resolveLocale(request)),null);
				}
				
				
				
				// Validation For Name
				
				if(Objects.nonNull(designations.get(i).getDesName()) && !designations.get(i).getDesName().equalsIgnoreCase("")) {
					if((designations.get(i).getDesName().length()<31 && designations.get(i).getDesName().length()>0)) {
						if(designationRepository.findByDesName(designations.get(i).getDesName())!=null) {
							return new Result("DIP",messageSource.getMessage("message18", null, myLocaleResolver.resolveLocale(request))+designations.get(i).getDesName(),null);
						}
					}else {
						return new Result("DIL",messageSource.getMessage("message19", null, myLocaleResolver.resolveLocale(request)),null);
					}	
				} else {
					return new Result("DIE",messageSource.getMessage("message20", null, myLocaleResolver.resolveLocale(request)),null);
				}
				
			}
			
			
			designationRepository.saveAll(designations);
			return new Result("OK",messageSource.getMessage("message21", null, myLocaleResolver.resolveLocale(request)),designations);
		}
		
	public Result updateDesignationValidation(Designation designation, String desId, HttpServletRequest request) {
			
			Designation designation2 = designationRepository.findById(desId).get();
				
			// Validation For Code
					
			if(Objects.nonNull(designation.getDesCode()) && !designation.getDesCode().equalsIgnoreCase("")) {
				if((designation.getDesCode().length()<31 && designation.getDesCode().length()>0)) {
					if(designationRepository.findByDesCode(designation.getDesCode())!=null) {
						return new Result("DIP",messageSource.getMessage("message5", null, myLocaleResolver.resolveLocale(request))+designation.getDesCode(),null);
					}
				} else {
					return new Result("DIL",messageSource.getMessage("message6", null, myLocaleResolver.resolveLocale(request)),null);
				}	
			} 
					
					
					
			// Validation For Name
					
			if(Objects.nonNull(designation.getDesName()) && !designation.getDesName().equalsIgnoreCase("")) {
				if((designation.getDesName().length()<31 && designation.getDesName().length()>0)) {
					if(designationRepository.findByDesName(designation.getDesName())!=null) {
						return new Result("DIP",messageSource.getMessage("message8", null, myLocaleResolver.resolveLocale(request))+designation.getDesName(),null);
					}
				} else {
					return new Result("DIL",messageSource.getMessage("message9", null, myLocaleResolver.resolveLocale(request)),null);
				}	
			}
			
			if(Objects.nonNull(designation.getDesName()) && 
					!"".equalsIgnoreCase(designation.getDesName())) {
				designation2.setDesName(designation.getDesName());
			}
			if(Objects.nonNull(designation.getDesCode()) && 
					!"".equalsIgnoreCase(designation.getDesCode())) {
				designation2.setDesCode(designation.getDesCode());
			}
			if(Objects.nonNull(designation.getDesCreateDate())) {
				designation2.setDesCreateDate(designation.getDesCreateDate());
			}
			if(Objects.nonNull(designation.getDesUpdateDate())) {
				designation2.setDesUpdateDate(designation.getDesUpdateDate());
			}
			
			designationRepository.save(designation2);
			return new Result("OK",messageSource.getMessage("message50", null, myLocaleResolver.resolveLocale(request)),designation2);
			
		}
		
		//-------------------------------------------VALIDATIONS FOR DESIGNATION ENDS HERE-------------------------------------------------//
		
}

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

import com.zkteco.entity.Designation;
import com.zkteco.entity.Result;

import com.zkteco.exceptions.DesignationException;

import com.zkteco.service.DesignationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class DesignationController {

	@Autowired
	DesignationService designationService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@PostMapping("/designation")
	public Result createDesignationController(@Valid @RequestBody Designation designation,HttpServletRequest request) {
		LOGGER.info("Inside Create Designation Controller");
		return designationService.createDesignation(designation,request);
	}
	
	@PostMapping("/designationlist")
	public Result createDesignationBatchController(@RequestBody List<Designation> designations, HttpServletRequest request) {
		LOGGER.info("Inside Create Designation Batch Controller");
		return designationService.createBatchDesignation(designations, request);
	}
	
	@PutMapping("/designation/{id}")
	public Result updateDesignationByIdController(@PathVariable("id") String Id,
											  @RequestBody Designation designation,HttpServletRequest request) throws DesignationException {
		LOGGER.info("Inside Update Designation By Designation Id Controller");
	    return designationService.updateDesignationById(designation, Id, request);
	}
	
	@GetMapping("/designation/{id}")
	public Result getDesignationByIdController(@PathVariable("id") String Id, HttpServletRequest request) throws DesignationException {
		LOGGER.info("Inside Fetch Designation by Designation Id Controller");
		return designationService.fetchDesignationDetailsById(Id, request);
	}
	
	@GetMapping("/alldesignation")
	public Result fetchAllDesignationController(HttpServletRequest request) throws DesignationException {
		LOGGER.info("Inside Fetch Designation List Controller");
		return designationService.fetchAllDesignation(request);
	}
	
	@DeleteMapping("/designation/{id}")
	public Result deleteDepartmentById(@PathVariable("id") String id, HttpServletRequest request) throws DesignationException {
		LOGGER.info("Inside Delete Designation By DesignationId Controller");
		return designationService.deleteDesignationById(id, request);
	}
	
}

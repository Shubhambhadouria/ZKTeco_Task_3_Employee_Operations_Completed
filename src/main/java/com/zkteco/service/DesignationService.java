package com.zkteco.service;

import java.util.List;

import com.zkteco.entity.Designation;
import com.zkteco.entity.Result;
import com.zkteco.exceptions.DesignationException;

import jakarta.servlet.http.HttpServletRequest;

public interface DesignationService {

	public Result createDesignation(Designation designation, HttpServletRequest request);
	
	public Result createBatchDesignation(List<Designation> designations, HttpServletRequest request);
	
	public Result updateDesignationById(Designation designation, String desId,  HttpServletRequest request) throws DesignationException;
	
	public Result fetchDesignationDetailsById(String desId, HttpServletRequest request) throws DesignationException;
	
	public Result fetchAllDesignation(HttpServletRequest request) throws DesignationException;
	
	public Result deleteDesignationById(String desId, HttpServletRequest request) throws DesignationException;
	
}

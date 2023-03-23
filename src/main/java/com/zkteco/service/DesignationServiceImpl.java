package com.zkteco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.zkteco.config.MyLocaleResolver;
import com.zkteco.entity.Designation;
import com.zkteco.entity.Result;
import com.zkteco.exceptions.DesignationException;
import com.zkteco.repository.DesignationRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DesignationServiceImpl implements DesignationService{

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MyLocaleResolver myLocaleResolver;
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	DesignationRepository designationRepository;
	
	@Override
	public Result createDesignation(Designation designation,HttpServletRequest request) {
		return validationService.validationDesignation(designation, request);
	}

	@Override
	public Result createBatchDesignation(List<Designation> designations,HttpServletRequest request) {
		return validationService.validationBatchDesignation(designations, request);
	}

	@Override
	public Result updateDesignationById(Designation designation, String desId, HttpServletRequest request) throws DesignationException {
		Optional<Designation> opt = designationRepository.findById(desId);
		if(opt.isPresent()) {
			return validationService.updateDesignationValidation(designation, desId, request);
		}else {
			throw new DesignationException(messageSource.getMessage("message1", null, myLocaleResolver.resolveLocale(request))+desId);
		}
	}

	@Override
	public Result fetchDesignationDetailsById(String desId, HttpServletRequest request) throws DesignationException {
		Optional<Designation> opt = designationRepository.findById(desId);
		if(opt.isPresent()) {
			return new Result("OK",messageSource.getMessage("message65", null, myLocaleResolver.resolveLocale(request)),opt);
		}else {
			throw new DesignationException(messageSource.getMessage("message66", null, myLocaleResolver.resolveLocale(request))+desId);
		}
	}

	@Override
	public Result fetchAllDesignation(HttpServletRequest request) throws DesignationException {
		List<Designation> designations = designationRepository.findAll();
		if(designations.size()!=0) {
			return new Result("OK",messageSource.getMessage("message67", null, myLocaleResolver.resolveLocale(request)),designations);
		} else {
			throw new DesignationException(messageSource.getMessage("message68", null, myLocaleResolver.resolveLocale(request)));
		}
	}

	@Override
	public Result deleteDesignationById(String desId, HttpServletRequest request) throws DesignationException {
		Optional<Designation> opt =  designationRepository.findById(desId);
		if(opt.isPresent()) {
			designationRepository.deleteById(desId);
			return new Result("OK",messageSource.getMessage("message69", null, myLocaleResolver.resolveLocale(request))+desId,opt.get());
		}else {
			throw new DesignationException(messageSource.getMessage("message66", null, myLocaleResolver.resolveLocale(request))+desId);
		}
	}

	
}

package com.example.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AdminRepositiory;
import com.example.service.Adminservice;
import com.example.BusTicketBookingApplication.BusTicketBookingApplication;
import com.example.dto.AdminDTO;
import com.example.dto.AdminLoginDTO;
import com.example.entity.Admin;
import com.example.mapper.AdminMapper;

import jakarta.validation.Valid;

@Service
public class AdminServiceImpl implements Adminservice {

	public static final Logger logger = LoggerFactory.getLogger(BusTicketBookingApplication.class);

	@Autowired
	private AdminRepositiory adminrepo;

	@Autowired
	private AdminMapper adminmap;

	
	@Override
	public void registerAdmin(AdminDTO admindto) throws IllegalArgumentException 
	{
		logger.info("You are enter in Register process");

		if (adminrepo.existsByAdminname(admindto.getAdminname())) {

			logger.warn("This admin  name presented already try it another adminname ");

			throw new IllegalArgumentException("Admin Already present");
		}

		if (adminrepo.existsByEmail(admindto.getEmail())) {
			logger.warn("This email Id presented already try it another email ");

			throw new IllegalArgumentException("EmailId Already present");
		}

		Admin admin = adminmap.convertToEntity(admindto);
		adminrepo.save(admin);
		
		logger.info("SucessFully Register The Admin");
	}

	
	@Override
	public void login(@Valid AdminLoginDTO adminlogindto) throws IllegalArgumentException 
	{
		logger.info("You are enter in Login process");
		
		if (!adminrepo.existsByAdminname(adminlogindto.getAdminname())) 
		{
			logger.warn("Admin does Not Exist");
			
			throw new IllegalArgumentException("Admin does not exist");
		}

		Admin existingAdmin = adminrepo.findByAdminname(adminlogindto.getAdminname());

		if (!existingAdmin.getPassword().equals(adminlogindto.getPassword())) 
		{
			logger.warn("Please provide correct password");
			
			throw new IllegalArgumentException("Incorrect Password");
		}
		
		logger.debug("SucessFully Logging Of Admin");
	}

	
	public void updateAdmin(long adminId, AdminDTO updatedAdmin) throws IllegalArgumentException 
	{
		logger.debug("Entering For Update The Data Of Admin");

		Admin existingadmin = adminrepo.findById(adminId).orElse(null);

		if (existingadmin != null) 
		{
			// Update only the fields that are allowed to be updated
			logger.debug("Updating The Data Of Admin");

			existingadmin.setAdminname(updatedAdmin.getAdminname());
			existingadmin.setEmail(updatedAdmin.getEmail());
			existingadmin.setFirstName(updatedAdmin.getFirstName());
			existingadmin.setLastName(updatedAdmin.getLastName());
			existingadmin.setPassword(updatedAdmin.getPassword());

			adminrepo.save(existingadmin);
			logger.debug("Update Sucessfully Done");
		} 
		else 
		{
			logger.debug("Inside IllegalArgumentException For updation");
			
			throw new IllegalArgumentException("Admin Not Found");
		}
	}

	@Override
	public void deleteAdmin(long adminId) 
	{
		logger.debug("Admin Enter For Deletion");

		if (adminrepo.existsById(adminId)) 
		{
			adminrepo.deleteById(adminId);
		} 
		else 
		{
			logger.warn("Please add first and then delete it.");
			
			throw new IllegalArgumentException("Admin does not exist");
		}

		logger.debug("Deletion Done SucessFully");
	}
}

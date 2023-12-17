package com.example.service;

import org.springframework.stereotype.Service;

import com.example.dto.AdminDTO;
import com.example.dto.AdminLoginDTO;


import jakarta.validation.Valid;

@Service
public interface Adminservice {
	
	void registerAdmin(@Valid  AdminDTO admin);
	
	void login(@Valid AdminLoginDTO adminlogindto);
	
	void deleteAdmin(long adminId);
	
	void updateAdmin(long adminId, AdminDTO updatedAdmin);
	
	

}

package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.entity.Admin;





@Repository
public interface AdminRepositiory extends JpaRepository<Admin,Long>{
	
	boolean existsByAdminname(String adminname);
	
	boolean existsByEmail(String email);
	
	Admin findByAdminname(String adminname);

}
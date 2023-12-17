package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.entity.Bus;


@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

	
	boolean existsById(Long busId);
	
	boolean existsByBusNo(String busNo);
	
	Bus findByBusName(String busname);
}

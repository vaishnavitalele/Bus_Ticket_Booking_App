package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
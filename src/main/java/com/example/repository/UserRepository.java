package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.UserDTO;
import com.example.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    
    
     User findByUserUsername(String username);

   
     UserDTO findByUserPassword(String password);
    
     boolean existsByUserUsername(String username);
    
     boolean existsByEmailId(String emailId);
     
    
    


}
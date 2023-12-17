package com.example.service;


import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.BusTicketBookingApplication.BusTicketBookingApplication;
import com.example.dto.UserDTO;
import com.example.dto.UserLoginDTO;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;

import jakarta.validation.Valid;



@Service
public class UserService {
	
	public static Logger logger = LoggerFactory.getLogger(BusTicketBookingApplication.class);

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper usermap;
	

	public void registerUser(@Valid UserDTO user1) throws IllegalArgumentException 
	{
		logger.info("You are enter in Register process");
		
	    if (userRepository.existsByUserUsername(user1.getUserUsername())) 
	    {
	    	logger.warn("This username already Registerd, try with another ");
	    	
	        throw new IllegalArgumentException("Username  is already taken");
	    }
	    else if(userRepository.existsByEmailId(user1.getEmailId())) 
	    {
	    	logger.warn("This EmailId already Registerd, try with another ");
	    	
	    	throw new IllegalArgumentException("EmailId is already taken");
	    }
	    
	    User existingUser = userRepository.findByUserUsername(user1.getUserUsername());
	    
	    if (existingUser != null) 
	    {
	    	logger.warn("This User already Registerd, try with another ");
	    	
	        throw new IllegalArgumentException("Username is already registered, register again");
	    }
	    
	    User newUser = new User(user1.getFirstName(), user1.getLastName(), user1.getUserUsername(), user1.getUserPassword(), user1.getMobNo(), user1.getEmailId());
	    
	    userRepository.save(newUser);
	    
	    logger.info("Registerd Sucessfully Done");
	    
	}

	
	public void authenticateUser(@Valid UserLoginDTO user) throws IllegalArgumentException 
	{
		logger.info("You are enter in Login process");
		
		 if (!userRepository.existsByUserUsername(user.getUserUsername())) 
		 {
			 logger.warn("This user name does not exit ,try it another username ");
			 
		     throw new IllegalArgumentException("Username does not exist");
		 }

		 User existingUser = userRepository.findByUserUsername(user.getUserUsername());
		 
		 if (!existingUser.getUserPassword().equals(user.getUserPassword())) 
		 {
			 logger.warn("This password is incorrect, please give perfect password ");
			 
		     throw new IllegalArgumentException("Incorrect password");
		 }
		 
		 logger.info("User Login Sucessfully Done");
	}

	public List<UserDTO> viewAllUser() 
	{
		logger.info("You are able to view all user");
		
		List<User> users = userRepository.findAll();
		
		if(users.isEmpty())
		{
			logger.warn("This user name does not exit ,try it another username ");
			
			throw new IllegalArgumentException("No Users Found");
		}
		
		return users.stream().map(usermap::convertToDTO).collect((Collectors.toList()));
	}


	public User getUserById(long userId) 
	{
		logger.info("You are able to view user by userId");
		
		User user = userRepository.findById(userId).orElse(null);
		
		if(user!=null)
		{
			return user;
		}
		else
		{
			logger.warn("This user name does not exit ,try it another username ");
			
			throw new IllegalArgumentException("User Not Found");
		}
		
	}


	
	public UserDTO updateUser(long userId, @Valid UserDTO userdto) 
	{
		logger.info("You are entered into update user");
		
		User existingUser = userRepository.findById(userId).orElse(null);
		
		if(existingUser!=null)
		{
			existingUser.setFirstName(userdto.getFirstName());
			existingUser.setLastName(userdto.getLastName());
			existingUser.setEmailId(userdto.getEmailId());
			existingUser.setMobNo(userdto.getMobNo());
			existingUser.setUserUsername(userdto.getUserUsername());
			existingUser.setUserPassword(userdto.getUserPassword());
			
			userRepository.save(existingUser);
			
			logger.info("user Details Updated Sucessfully");
			
			return usermap.convertToDTO(existingUser);
		}
		else
		{
			logger.warn("This user name does not exit ,try it another username ");
			
			throw new IllegalArgumentException("User Not Found");
		}
	}
}




package com.nagarro.notesbackend.service.impl;

import com.nagarro.notesbackend.entity.User;
import com.nagarro.notesbackend.repository.UserRepository;
import com.nagarro.notesbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
 
	@Autowired
	private UserRepository repo;
	
	//This method is used to save user
	@Override
	public User saveUser(User user)
	{
	return repo.save(user);	
	}
	
	//This method is used to get User with help of user emailId
	@Override
	public User fetchUserByEmailId(String emailId) {
		return repo.findByEmailId(emailId);
	}
	
	//This method is used to get count of all Registrated User
	@Override
	public Long countAllRegistrated()
	{
		return repo.count();
	}
    
	
}

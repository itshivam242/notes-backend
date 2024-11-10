package com.nagarro.notesbackend.service;

import com.nagarro.notesbackend.entity.User;

public interface UserService {

	public User saveUser(User user);
	
	public User fetchUserByEmailId(String emailId);
	
	public Long countAllRegistrated();
}

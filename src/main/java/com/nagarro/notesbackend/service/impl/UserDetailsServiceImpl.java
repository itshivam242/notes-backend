package com.nagarro.notesbackend.service.impl;

import com.nagarro.notesbackend.entity.User;
import com.nagarro.notesbackend.dto.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

@Autowired
private UserServiceImpl repository;

@Override
public UserDetailsImpl loadUserByUsername(String emailId) throws UsernameNotFoundException {
final User user=this.repository.fetchUserByEmailId(emailId);
if(user==null) {
	return null;
}
else {
return new UserDetailsImpl(user);
}
}

}
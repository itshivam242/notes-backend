package com.nagarro.notesbackend.repository;

import com.nagarro.notesbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    
	public User findByEmailId(String emailId);
    public User findByEmailIdAndPassword(String emailId,String password);
}

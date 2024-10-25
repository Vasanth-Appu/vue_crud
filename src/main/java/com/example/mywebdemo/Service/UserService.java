package com.example.mywebdemo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mywebdemo.Repository.UserRepository;
import com.example.mywebdemo.entity.User;

@Service
public class UserService {
	@Autowired 
	UserRepository ur;
	public User saveUser(User user) {
	     if (!ur.existsById(user.getId()) &
	    		 !ur.existsByEmail(user.getEmail())) {
					return ur.save(user);
	     }else {
	            throw new IllegalArgumentException("UserId or Email has already exists") ;	    	 
		}
		
		
	}
	public List<User> getuser(){
		return ur.findAll();
	}
	public Optional<User> getrecord(Integer ide)
	{
		return ur.findById(ide);
		}
	
	public User updateId(Integer id,User user) {
	User oldData= ur.findById(id)
			.orElseThrow(()->new ResourceNotFoundException("User Not Found"));
	     
	     oldData.setName(user.getName());
		oldData.setAddress(user.getAddress());
	    oldData.setContact(user.getContact());
		oldData.setEmail(user.getEmail());
		return ur.save(oldData);
	}
	public void delete(Integer id) {
		
		ur.deleteById(id);
	}	
	
	}


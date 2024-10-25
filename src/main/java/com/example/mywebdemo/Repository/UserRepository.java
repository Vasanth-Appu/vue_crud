package com.example.mywebdemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mywebdemo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	boolean existsById(Long id);
	boolean existsByEmail(String string);


}


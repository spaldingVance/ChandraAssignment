package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User saveNewUser(User newUser) {
		User user = userRepository.save(newUser);
		return user;
	}

	public User findById(String userid) {
		User user = userRepository.findByUserid(userid);
		return user;
	}
}


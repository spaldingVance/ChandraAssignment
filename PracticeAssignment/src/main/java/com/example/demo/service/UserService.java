package com.example.demo.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User saveNewUser(User newUser) {
		System.out.println("got to here");
		User user = userRepository.save(newUser);
		return user;

	}

	public User findById(String userid) {
		User user = userRepository.findByUserid(userid);
		return user;
	}

	public User updateUser(@Valid User user) {
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
}


package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

@Service
public class BcryptGenerator {

	@Autowired
	private UserRepository userRepository;

	// TODO: setup spring security
	public String passwordEncoder(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	public Boolean passwordDecoder(String currentPassword, String ExistingPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(currentPassword, ExistingPassword);
	}
}

package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.config.BcryptGenerator;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	BcryptGenerator bcryptGenerator;

	@GetMapping("/{userid}")
	public ResponseEntity<?> getUser(@PathVariable @Valid String userid) {
		User user = userService.findById(userid);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return (ResponseEntity<?>) ResponseEntity.notFound();
		}
		
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid User user) {
		User registeredUser = userService.saveNewUser(user);
		
		if(registeredUser != null) {
			return new ResponseEntity<String>("Successfully registered", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@Valid @RequestBody User user) {
		User UseridExists = userService.findById(user.getUserid());

		String existingPassword = UseridExists.getPassword();
		String currentPassword = user.getPassword();

		if (bcryptGenerator.passwordDecoder(currentPassword, existingPassword)) {
			return new ResponseEntity<String>("Logged In Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Incorrect Username or Password", HttpStatus.UNAUTHORIZED);
		}
	}

}
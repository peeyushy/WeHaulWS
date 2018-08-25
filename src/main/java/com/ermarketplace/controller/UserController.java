package com.ermarketplace.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ermarketplace.exception.ResourceNotFoundException;
import com.ermarketplace.model.User;
import com.ermarketplace.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/create")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping("/id/{id}")
	public User getUserById(@PathVariable(value = "id") Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}
	
	@GetMapping("/client/id/{clientid}")
	public List<User> getUserByClientId(@PathVariable(value = "clientid") Long clientid) {
		return userRepository.findUsersByclientid(clientid);
	}
	
	@GetMapping("/username/{username}")
	public User getUserByUserName(@PathVariable(value = "username") String username) {
		return userRepository.findUserByusername(username);
	}

	@PutMapping("/id/{id}")
	public User updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setName(userDetails.getName());
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		user.setEmail(userDetails.getEmail());
		user.setAvatar(userDetails.getAvatar());		
		user.setContactno(userDetails.getContactno());
		user.setNotificationtype(userDetails.getNotificationtype());
		user.setStatus(userDetails.getStatus());		
		
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		userRepository.delete(user);

		return ResponseEntity.ok().build();
	}
}
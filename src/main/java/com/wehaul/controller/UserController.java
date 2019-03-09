package com.wehaul.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wehaul.exception.ResourceNotFoundException;
import com.wehaul.model.Client;
import com.wehaul.model.GetClient;
import com.wehaul.model.User;
import com.wehaul.repository.RoleRepository;
import com.wehaul.repository.UserRepository;

@RestController
@RequestMapping("/wehaul/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/create")
	public User createUser(@Valid @RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@GetMapping("/id/{id}")
	public User getUserById(@PathVariable(value = "id") Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}
	
	@GetMapping("/getclientbyusername/{username}")
	public GetClient getClientByUserName(@PathVariable(value = "username") String username) {
		return userRepository.findClientByusername(username);
	}
	
	@GetMapping("/getclientidbyusername/{username}")
	public Long getClientIdByUserName(@PathVariable(value = "username") String username) {
		GetClient client=userRepository.findClientByusername(username);
		return client.getClient().getClientid();
	}

	@GetMapping("/username/{username}")
	public User getUserByUserName(@PathVariable(value = "username") String username) {
		return userRepository.findByusernameAndActive(username, true);
	}

	@GetMapping("/adminusername/{username}")
	public User getAdminOnlyUserByUserName(@PathVariable(value = "username") String username) {
		return userRepository.findAdminOnlyUserByusername(username);
	}

	@PutMapping("/id/{id}")
	public User updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setName(userDetails.getName());
		user.setUsername(userDetails.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		user.setEmail(userDetails.getEmail());
		user.setUseravatar(userDetails.getUseravatar());
		user.setContactno(userDetails.getContactno());
		user.setNotificationtype(userDetails.getNotificationtype());
		user.setActive(userDetails.isActive());
		// set rev on client
		Client client = user.getClient();
		client.setRevid(client.getRevid() + 1);
		user.setClient(client);
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
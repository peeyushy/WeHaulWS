package com.wehaul.controller;

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

import com.wehaul.exception.ResourceNotFoundException;
import com.wehaul.model.Role;
import com.wehaul.model.User;
import com.wehaul.repository.RoleRepository;

@RestController
@RequestMapping("/wehaul/role")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/all")
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@PostMapping("/create")
	public Role createRole(@Valid @RequestBody Role role) {
		return roleRepository.save(role);
	}

	@GetMapping("/id/{id}")
	public Role getRoleById(@PathVariable(value = "id") Long roleId) {
		return roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));
	}
	
	@GetMapping("/rolename/{rolename}")
	public Role getRoleByRolename(@PathVariable(value = "rolename") String rolename) {
		return roleRepository.findRoleByRolename(rolename);
	}

	@PutMapping("/id/{id}")
	public Role updateRole(@PathVariable(value = "id") Long roleId, @Valid @RequestBody Role roleDetails) {

		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));

		role.setRolename(roleDetails.getRolename());
		role.setRoledesc(roleDetails.getRoledesc());

		return roleRepository.save(role);
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable(value = "id") Long roleId) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));

		roleRepository.delete(role);

		return ResponseEntity.ok().build();
	}
}
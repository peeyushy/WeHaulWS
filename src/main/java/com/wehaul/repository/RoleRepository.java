package com.wehaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wehaul.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findRoleByRolename(String rolename);
}

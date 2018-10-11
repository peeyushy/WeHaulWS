package com.erstaticdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erstaticdata.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findRoleByRolename(String rolename);
}

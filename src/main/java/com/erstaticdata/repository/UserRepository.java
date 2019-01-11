package com.erstaticdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erstaticdata.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findUserByusername(String username);

	@Query("SELECT u FROM User u,Client c WHERE u.client.clientid=c.clientid AND c.active=true AND c.clienttype='A' AND LOWER(u.username) = LOWER(:username) AND u.active=true AND u.role.rolename = 'ADMIN'")
	User findAdminOnlyUserByusername(@Param("username") String username);
}

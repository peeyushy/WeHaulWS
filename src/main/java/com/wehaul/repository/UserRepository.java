package com.wehaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wehaul.constants.AppConstants;
import com.wehaul.model.GetClient;
import com.wehaul.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByusername(String username);

	User findByusernameAndActive(String username,boolean active);
	
	GetClient findClientByusername(String username);

	@Query("SELECT u FROM User u,Client c WHERE u.client.clientid=c.clientid AND c.active=true AND c.clienttype='A' AND LOWER(u.username) = LOWER(:username) AND u.active=true AND u.role.rolename = 'ADMIN'")
	User findAdminOnlyUserByusername(@Param("username") String username);
}

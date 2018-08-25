package com.ermarketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ermarketplace.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findUsersByclientid(Long clientid);
	User findUserByusername(String username);
}

package com.ermarketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ermarketplace.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	List<Client> findByclienttype(String clienttype);
}

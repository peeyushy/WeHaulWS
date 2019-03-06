package com.erstaticdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erstaticdata.constants.AppConstants.ClientType;
import com.erstaticdata.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	List<Client> findByclienttype(ClientType clientType);
	
	List<Client> findByclienttypeNot(ClientType clientType);
	
	List<Client> findByclientnameNot(String clientName);
	
	//List<Client> findByclienttypeNotAnd(ClientType clientType);
}

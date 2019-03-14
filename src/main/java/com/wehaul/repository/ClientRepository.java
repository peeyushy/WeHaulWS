package com.wehaul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wehaul.constants.AppConstants;
import com.wehaul.constants.AppConstants.ClientType;
import com.wehaul.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	List<Client> getClientsByActive(boolean active);

	List<Client> findByclienttype(ClientType clientType);

	List<Client> findByclienttypeNot(ClientType clientType);

	List<Client> findByclientnameNotIn(String clientname);

	@Query("SELECT c FROM Client c WHERE c.active=true AND c.clienttype <> 'A' AND c.clientid <> :clientid")
	List<Client> findNonAdminActiveClientsWhereclientidNotIn(Long clientid);

	@Query("SELECT c FROM Client c WHERE c.active=true AND c.clienttype <> 'A' AND c.clientid <> :clientid AND (LOWER(c.clientname) like LOWER(:queryStr) OR LOWER(c.address) like LOWER(:queryStr))")
	List<Client> findNonAdminActiveClientsWhereclientidNotIn(Long clientid, String queryStr);

	List<Client> findByclientnameNot(String clientName);

	// List<Client> findByclienttypeNotAnd(ClientType clientType);
}

package com.erstaticdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erstaticdata.model.Client;
import com.erstaticdata.model.Load;

@Repository
public interface LoadRepository extends JpaRepository<Load, Long>,LoadRepositoryCustom {	
	List<Load> findLoadByclient(Client client);
}

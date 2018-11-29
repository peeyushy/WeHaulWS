package com.erstaticdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erstaticdata.model.Client;
import com.erstaticdata.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>,VehicleRepositoryCustom {
	List<Vehicle> findVehicleByclient(Client client);
	
	
}

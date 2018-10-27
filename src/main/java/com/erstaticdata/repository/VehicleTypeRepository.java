package com.erstaticdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erstaticdata.model.VehicleType;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
	@Query("SELECT vt FROM VehicleType vt WHERE vt.status='ACTIVE'")
	List<VehicleType> findAllActiveVehicleType();
}

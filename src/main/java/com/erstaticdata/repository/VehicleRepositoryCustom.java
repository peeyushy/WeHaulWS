package com.erstaticdata.repository;

import java.util.List;

import com.erstaticdata.model.Vehicle;

public interface VehicleRepositoryCustom {
	List<Vehicle> getVehicleByLoadId(Long lid);
}

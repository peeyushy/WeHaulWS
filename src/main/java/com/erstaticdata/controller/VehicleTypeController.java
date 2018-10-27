package com.erstaticdata.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erstaticdata.exception.ResourceNotFoundException;
import com.erstaticdata.model.VehicleType;
import com.erstaticdata.repository.VehicleTypeRepository;

@RestController
@RequestMapping("/ERStaticData/vehicletype")
public class VehicleTypeController {

	@Autowired
	VehicleTypeRepository vehicleTypeRepository;

	@GetMapping("/all")
	public List<VehicleType> getAllVehicleType() {
		return vehicleTypeRepository.findAll();
	}
	
	@GetMapping("/all-active")
	public List<VehicleType> getAllActiveVehicleType() {
		return vehicleTypeRepository.findAllActiveVehicleType();
	}

	@PostMapping("/create")
	public VehicleType createNewVehicleType(@Valid @RequestBody VehicleType vehicleType) {
		return vehicleTypeRepository.save(vehicleType);
	}

	@GetMapping("/id/{id}")
	public VehicleType getVehicleTypeById(@PathVariable(value = "id") Long vehicletypeId) {
		return vehicleTypeRepository.findById(vehicletypeId).orElseThrow(() -> new ResourceNotFoundException("VehicleType", "id", vehicletypeId));
	}

	@PutMapping("/id/{id}")
	public VehicleType updateVehicleType(@PathVariable(value = "id") Long vehicleTypeId, @Valid @RequestBody VehicleType vehicleTypeDetails) {

		VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("VehicleType", "id", vehicleTypeId));

		vehicleType.setVtypename(vehicleTypeDetails.getVtypename());
		vehicleType.setVtypedesc(vehicleTypeDetails.getVtypedesc());
		vehicleType.setStatus(vehicleTypeDetails.getStatus());
		return vehicleTypeRepository.save(vehicleType);
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteVehicleType(@PathVariable(value = "id") Long vehicleTypeId) {
		VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("VehicleType", "id", vehicleTypeId));

		vehicleTypeRepository.delete(vehicleType);

		return ResponseEntity.ok().build();
	}
}
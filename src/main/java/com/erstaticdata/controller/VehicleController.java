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
import com.erstaticdata.model.Vehicle;
import com.erstaticdata.repository.ClientRepository;
import com.erstaticdata.repository.VehicleRepository;

@RestController
@RequestMapping("/ERStaticData/vehicle")
public class VehicleController {

	@Autowired
	VehicleRepository vehicleRepository;
	
	@Autowired
	ClientRepository clientRepository;

	@GetMapping("/all")
	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll();
	}

	@PostMapping("/create")
	public Vehicle addVehicle(@Valid @RequestBody Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	@GetMapping("/id/{id}")
	public Vehicle getVehicleById(@PathVariable(value = "id") Long vId) {
		return vehicleRepository.findById(vId).orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", vId));
	}
	
	@GetMapping("/clientid/{cid}")
	public List<Vehicle> getVehicleByclient(@PathVariable(value = "cid") Long cid) {		
		return vehicleRepository.findVehicleByclient(clientRepository.findById(cid).get());
	}
	
	@GetMapping("/loadid/{lid}")
	public List<Vehicle> getVehicleByLoadid(@PathVariable(value = "lid") Long lid) {
		return vehicleRepository.getVehicleByLoadId(lid);
	}

	@PutMapping("/id/{vid}")
	public Vehicle updateVehicle(@PathVariable(value = "vid") Long vid, @Valid @RequestBody Vehicle vehicleDetails) {

		Vehicle vehicle = vehicleRepository.findById(vid)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "vid", vid));

		vehicle.setVtype(vehicleDetails.getVtype());
		vehicle.setLtype(vehicleDetails.getLtype());
		vehicle.setOpcost(vehicleDetails.getOpcost());
		vehicle.setVehicleavatar(vehicleDetails.getVehicleavatar());
		vehicle.setStatus(vehicleDetails.getStatus());

		return vehicleRepository.save(vehicle);
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable(value = "id") Long vId) {
		Vehicle vehicle = vehicleRepository.findById(vId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", vId));

		vehicleRepository.delete(vehicle);

		return ResponseEntity.ok().build();
	}
}
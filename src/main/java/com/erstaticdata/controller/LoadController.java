package com.erstaticdata.controller;

import java.util.List;
import java.util.Optional;

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

import com.erstaticdata.dto.LoadSearchOptionsDto;
import com.erstaticdata.exception.ResourceNotFoundException;
import com.erstaticdata.model.Client;
import com.erstaticdata.model.Load;
import com.erstaticdata.model.Vehicle;
import com.erstaticdata.repository.ClientRepository;
import com.erstaticdata.repository.LoadRepository;

@RestController
@RequestMapping("/ERStaticData/load")
public class LoadController {

	@Autowired
	LoadRepository loadRepository;
	
	@Autowired
	ClientRepository clientRepository;

	@GetMapping("/all")
	public List<Load> getAllLoads() {
		return loadRepository.findAll();
	}

	@PostMapping("/create")
	public Load addLoad(@Valid @RequestBody Load load) {
		return loadRepository.save(load);
	}

	@GetMapping("/id/{id}")
	public Load getLoadById(@PathVariable(value = "id") Long lid) {
		return loadRepository.findById(lid).orElseThrow(() -> new ResourceNotFoundException("Load", "id", lid));
	}
	
	@GetMapping("/clientid/{cid}")
	public List<Load> getLoadByclient(@PathVariable(value = "cid") Long cid) {		
		return loadRepository.findLoadByclient(clientRepository.findById(cid).get());
	}
	
	@GetMapping("/vehicleid/{vid}")
	public List<Load> getLoadByVehicleid(@PathVariable(value = "vid") Long vid) {
		return loadRepository.getLoadByVehicleId(vid);
	}
	
	@PostMapping("/searchoptions")
	public List<Load> getLoadBySearchOptions(@Valid @RequestBody LoadSearchOptionsDto loadSearchOptionsDto) {
		return loadRepository.getLoadBySearchOptions(loadSearchOptionsDto);
	}

	@PutMapping("/id/{id}")
	public Load updateLoad(@PathVariable(value = "id") Long lid, @Valid @RequestBody Load loadDetails) {

		Load load = loadRepository.findById(lid)
				.orElseThrow(() -> new ResourceNotFoundException("Load", "lid", lid));

		load.setClient(loadDetails.getClient());
		load.setComments(loadDetails.getComments());
		load.setLassistance(loadDetails.isLassistance());
		load.setLastupdatedby(loadDetails.getLastupdatedby());
		load.setLdatetime(loadDetails.getLdatetime());
		load.setLdatetimeflexi(loadDetails.isLdatetimeflexi());
		load.setLdroploc(loadDetails.getLdroploc());
		load.setLpickuploc(loadDetails.getLpickuploc());
		load.setLpickupdropflexi(loadDetails.isLpickupdropflexi());
		load.setLtype(loadDetails.getLtype());		
		load.setStatus(loadDetails.getStatus());

		return loadRepository.save(load);
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteLoad(@PathVariable(value = "id") Long lid) {
		Load load = loadRepository.findById(lid)
				.orElseThrow(() -> new ResourceNotFoundException("Load", "id", lid));

		loadRepository.delete(load);

		return ResponseEntity.ok().build();
	}
}
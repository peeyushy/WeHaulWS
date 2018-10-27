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
import com.erstaticdata.model.LoadType;
import com.erstaticdata.repository.LoadTypeRepository;

@RestController
@RequestMapping("/ERStaticData/loadtype")
public class LoadTypeController {

	@Autowired
	LoadTypeRepository loadTypeRepository;

	@GetMapping("/all")
	public List<LoadType> getAllLoadType() {
		return loadTypeRepository.findAll();
	}
	
	@GetMapping("/all-active")
	public List<LoadType> getAllActiveLoadType() {
		return loadTypeRepository.findAllActiveLoadType();
	}

	@PostMapping("/create")
	public LoadType createNewLoadType(@Valid @RequestBody LoadType loadType) {
		return loadTypeRepository.save(loadType);
	}

	@GetMapping("/id/{id}")
	public LoadType getLoadTypeById(@PathVariable(value = "id") Long loadtypeId) {
		return loadTypeRepository.findById(loadtypeId).orElseThrow(() -> new ResourceNotFoundException("LoadType", "id", loadtypeId));
	}

	@PutMapping("/id/{id}")
	public LoadType updateLoadType(@PathVariable(value = "id") Long loadTypeId, @Valid @RequestBody LoadType loadTypeDetails) {

		LoadType loadType = loadTypeRepository.findById(loadTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("LoadType", "id", loadTypeId));

		loadType.setLtypename(loadTypeDetails.getLtypename());
		loadType.setLtypedesc(loadTypeDetails.getLtypedesc());
		loadType.setStatus(loadTypeDetails.getStatus());
		return loadTypeRepository.save(loadType);
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteLoadType(@PathVariable(value = "id") Long loadTypeId) {
		LoadType loadType = loadTypeRepository.findById(loadTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("LoadType", "id", loadTypeId));

		loadTypeRepository.delete(loadType);

		return ResponseEntity.ok().build();
	}
}
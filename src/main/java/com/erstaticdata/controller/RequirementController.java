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
import com.erstaticdata.model.Requirement;
import com.erstaticdata.model.GetClient;
import com.erstaticdata.repository.ClientRepository;
import com.erstaticdata.repository.RequirementRepository;

@RestController
@RequestMapping("/ERStaticData/req")
public class RequirementController {

	@Autowired
	RequirementRepository reqRepository;

	@Autowired
	ClientRepository clientRepository;

	@GetMapping("/all")
	public List<Requirement> getAllRequirements() {
		return reqRepository.findAll();
	}

	@PostMapping("/create")
	public Requirement addReq(@Valid @RequestBody Requirement req) {
		return reqRepository.save(req);
	}

	@GetMapping("/id/{id}")
	public Requirement getReqById(@PathVariable(value = "id") Long reqid) {
		return reqRepository.findById(reqid)
				.orElseThrow(() -> new ResourceNotFoundException("Requirement", "id", reqid));
	}

	@GetMapping("/clientid/{cid}")
	public List<Requirement> getReqByclient(@PathVariable(value = "cid") Long cid) {
		return reqRepository.findRequirementByclient(clientRepository.findById(cid).get());
	}
	
	@GetMapping("/getclientidbyreqid/{reqid}")
	public Long getClientIdByReqId(@PathVariable(value = "reqid") Long reqid) {
		GetClient client= reqRepository.findClientidByReqid(reqid);
		return client.getClient().getClientid();
				
	}

	/*
	 * @GetMapping("/vehicleid/{vid}") public List<Load>
	 * getLoadByVehicleid(@PathVariable(value = "vid") Long vid) { return
	 * reqRepository.getLoadByVehicleId(vid); }
	 */

	/*
	 * @PostMapping("/searchoptions") public List<Load>
	 * getLoadBySearchOptions(@Valid @RequestBody LoadSearchOptionsDto
	 * loadSearchOptionsDto) { return
	 * reqRepository.getLoadBySearchOptions(loadSearchOptionsDto); }
	 */

	@PutMapping("/id/{id}")
	public Requirement updateReq(@PathVariable(value = "id") Long reqid, @Valid @RequestBody Requirement reqDetails) {

		Requirement req = reqRepository.findById(reqid)
				.orElseThrow(() -> new ResourceNotFoundException("Requirement", "reqid", reqid));

		req.setReqtype(reqDetails.getReqtype());
		req.setComments(reqDetails.getComments());
		req.setLastupdatedby(reqDetails.getLastupdatedby());
		req.setReqdatetime(reqDetails.getReqdatetime());
		//req.setReqdatetimeflexi(reqDetails.isReqdatetimeflexi());
		req.setReqdroploc(reqDetails.getReqdroploc());
		req.setReqpickuploc(reqDetails.getReqpickuploc());
		//req.setReqpickupdropflexi(reqDetails.isReqpickupdropflexi());
		req.setLtype(reqDetails.getLtype());
		req.setVtype(reqDetails.getVtype());
		req.setStatus(reqDetails.getStatus());

		return reqRepository.save(req);
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteReq(@PathVariable(value = "id") Long reqid) {
		Requirement req = reqRepository.findById(reqid)
				.orElseThrow(() -> new ResourceNotFoundException("Requirement", "id", reqid));

		reqRepository.delete(req);

		return ResponseEntity.ok().build();
	}
}
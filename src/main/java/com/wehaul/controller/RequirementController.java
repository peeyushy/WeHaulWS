package com.wehaul.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.wehaul.constants.AppConstants;
import com.wehaul.constants.AppConstants.ReqStatus;
import com.wehaul.dto.RequirementDto;
import com.wehaul.exception.ResourceNotFoundException;
import com.wehaul.model.GetClient;
import com.wehaul.model.Requirement;
import com.wehaul.repository.ClientRepository;
import com.wehaul.repository.RequirementRepository;
import com.wehaul.service.RequirementService;

@RestController
@RequestMapping("/wehaul/req")
public class RequirementController {

	@Autowired
	RequirementRepository reqRepository;

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	RequirementService requirementService;

	@GetMapping("/all")
	public List<Requirement> getAllRequirements() {
		return reqRepository.findAll();
	}

	@GetMapping("/status/{status}")
	public List<Requirement> getAllRequirementsByStatus(@PathVariable(value = "status") String status) {
		if (status.equalsIgnoreCase(AppConstants.ReqStatus.CLOSED.toString())) {
			return reqRepository.findRequirementByStatus(AppConstants.ReqStatus.CLOSED);
		} else if (status.equalsIgnoreCase(AppConstants.ReqStatus.EXPIRED.toString())) {
			return reqRepository.findRequirementByStatus(AppConstants.ReqStatus.EXPIRED);
		} else if (status.equalsIgnoreCase(AppConstants.ReqStatus.NEW.toString())) {
			return reqRepository.findRequirementByStatus(AppConstants.ReqStatus.NEW);
		} else if (status.equalsIgnoreCase(AppConstants.ReqStatus.OPEN.toString())) {
			return reqRepository.findRequirementByStatus(AppConstants.ReqStatus.OPEN);
		} else if (status.equalsIgnoreCase(AppConstants.ReqStatus.QUOTED.toString())) {
			return reqRepository.findRequirementByStatus(AppConstants.ReqStatus.QUOTED);
		} else {
			return new ArrayList<Requirement>();
		}
	}
	
	@GetMapping("/statusin/{statusin}")
	public List<Requirement> getAllRequirementsByStatusIn(@PathVariable(value = "statusin") String statusin) {
		List<ReqStatus> statusLst=new ArrayList<AppConstants.ReqStatus>();
		List<String> items = Arrays.asList(statusin.split("\\s*#\\s*"));
		for (String status : items) {
			if (status.equalsIgnoreCase(AppConstants.ReqStatus.CLOSED.toString())) {
				statusLst.add(AppConstants.ReqStatus.CLOSED);
			} else if (status.equalsIgnoreCase(AppConstants.ReqStatus.EXPIRED.toString())) {
				statusLst.add(AppConstants.ReqStatus.EXPIRED);
			} else if (status.equalsIgnoreCase(AppConstants.ReqStatus.NEW.toString())) {
				statusLst.add(AppConstants.ReqStatus.NEW);
			} else if (status.equalsIgnoreCase(AppConstants.ReqStatus.OPEN.toString())) {
				statusLst.add(AppConstants.ReqStatus.OPEN);
			} else if (status.equalsIgnoreCase(AppConstants.ReqStatus.QUOTED.toString())) {
				statusLst.add(AppConstants.ReqStatus.QUOTED);
			}
		}
		if (!statusLst.isEmpty()) {
			return reqRepository.findRequirementBystatusIn(statusLst);
		} else {
			return new ArrayList<Requirement>();
		}
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

	@GetMapping("/byclientidandstatus/{cidandstatus}")
	public List<Requirement> getReqByClientAndStatus(@PathVariable(value = "cidandstatus") String cidandstatus) {
		/* cidandstatus format: 11,OPEN#CLOSE */
		String[] args = cidandstatus.split(",");
		String cid;
		ArrayList<ReqStatus> statusLst = new ArrayList<AppConstants.ReqStatus>();
		
		if (args.length > 1) {
			cid = args[0];
			List<String> items = Arrays.asList(args[1].split("\\s*#\\s*"));
			for (String status : items) {
				if (status.equalsIgnoreCase(AppConstants.ReqStatus.CLOSED.toString())) {
					statusLst.add(AppConstants.ReqStatus.CLOSED);
				} else if (status.equalsIgnoreCase(AppConstants.ReqStatus.EXPIRED.toString())) {
					statusLst.add(AppConstants.ReqStatus.EXPIRED);
				} else if (status.equalsIgnoreCase(AppConstants.ReqStatus.NEW.toString())) {
					statusLst.add(AppConstants.ReqStatus.NEW);
				} else if (status.equalsIgnoreCase(AppConstants.ReqStatus.OPEN.toString())) {
					statusLst.add(AppConstants.ReqStatus.OPEN);
				} else if (status.equalsIgnoreCase(AppConstants.ReqStatus.QUOTED.toString())) {
					statusLst.add(AppConstants.ReqStatus.QUOTED);
				}
			}
			if (!statusLst.isEmpty()) {
				return reqRepository.findRequirementByclientAndStatusIn(
						clientRepository.findById(Long.valueOf(cid)).get(), statusLst);
			} else {
				return new ArrayList<Requirement>();
			}
		} else {
			return new ArrayList<Requirement>();
		}
	}

	@GetMapping("/getclientidbyreqid/{reqid}")
	public Long getClientIdByReqId(@PathVariable(value = "reqid") Long reqid) {
		GetClient client = reqRepository.findClientidByReqid(reqid);
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
		// req.setReqdatetimeflexi(reqDetails.isReqdatetimeflexi());
		req.setReqdroploc(reqDetails.getReqdroploc());
		req.setReqpickuploc(reqDetails.getReqpickuploc());
		// req.setReqpickupdropflexi(reqDetails.isReqpickupdropflexi());
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
	
	@GetMapping("/getOpenAndQuotedReq/{encryptedCID}")
	public List<RequirementDto> getOpenAndQuotedReq(@PathVariable(value = "encryptedCID") String encryptedCID) throws Exception {
		
		List<RequirementDto> requirementList  = requirementService.getRequirementList(encryptedCID);
	
		return requirementList;	
	}
	
	
}
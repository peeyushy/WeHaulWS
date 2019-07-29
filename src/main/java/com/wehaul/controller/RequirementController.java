package com.wehaul.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.wehaul.dto.QuoteDto;
import com.wehaul.dto.RequirementDto;
import com.wehaul.exception.ResourceNotFoundException;
import com.wehaul.exception.WeHaulAPIServiceException;
import com.wehaul.model.GetClient;
import com.wehaul.model.Requirement;
import com.wehaul.repository.ClientRepository;
import com.wehaul.repository.RequirementRepository;
import com.wehaul.service.RequirementService;

@RestController
@RequestMapping("/wehaul/req")
public class RequirementController {

	private static final Logger logReqControler = LoggerFactory.getLogger(RequirementController.class);

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

	/**
	 * 
	 * @param statusin 'OPEN,CLOSE'
	 * @return
	 */
	@GetMapping("/statusin/[{statusin}]")
	public List<Requirement> getAllRequirementsByStatusIn(@PathVariable(value = "statusin") String[] statusin) {
		List<ReqStatus> statusLst = new ArrayList<AppConstants.ReqStatus>();
		for (String status : statusin) {
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
		req.getReqDetails().setReq(req);
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

	/**
	 * 
	 * @param cid
	 * @param status [OPEN,QUOTED]
	 * @return
	 */

	@GetMapping("/byclientidandstatus/{cid}/[{status}]")
	public List<Requirement> getReqByClientAndStatus(@PathVariable(value = "cid") Long cid,
			@PathVariable(value = "status") String[] status) {

		ArrayList<ReqStatus> statusLst = new ArrayList<AppConstants.ReqStatus>();

		for (String statusStr : status) {
			if (statusStr.equalsIgnoreCase(AppConstants.ReqStatus.CLOSED.toString())) {
				statusLst.add(AppConstants.ReqStatus.CLOSED);
			} else if (statusStr.equalsIgnoreCase(AppConstants.ReqStatus.EXPIRED.toString())) {
				statusLst.add(AppConstants.ReqStatus.EXPIRED);
			} else if (statusStr.equalsIgnoreCase(AppConstants.ReqStatus.NEW.toString())) {
				statusLst.add(AppConstants.ReqStatus.NEW);
			} else if (statusStr.equalsIgnoreCase(AppConstants.ReqStatus.OPEN.toString())) {
				statusLst.add(AppConstants.ReqStatus.OPEN);
			} else if (statusStr.equalsIgnoreCase(AppConstants.ReqStatus.QUOTED.toString())) {
				statusLst.add(AppConstants.ReqStatus.QUOTED);
			}
		}
		if (!statusLst.isEmpty()) {
			return reqRepository.findRequirementByclientAndStatusIn(clientRepository.findById(cid).get(), statusLst);
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
		//req.setReqdroplocid(reqDetails.getrgetReqdroplocid());
		req.setReqpickuploc(reqDetails.getReqpickuploc());
		//req.setReqpickuplocid(reqDetails.getReqpickuplocid());
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

	@GetMapping("/getOpenAndQuotedReq/{webUniqueCode}")
	public List<RequirementDto> getOpenAndQuotedReq(@PathVariable(value = "webUniqueCode") String webUniqueCode)
			throws Exception {

		List<RequirementDto> requirementList = null;
		try {
			requirementList = requirementService.getRequirementList(URLDecoder.decode(webUniqueCode, "UTF-8"));
		} catch (WeHaulAPIServiceException ex) {
			throw new WeHaulAPIServiceException(HttpServletResponse.SC_NOT_FOUND, " Client Not Verified");
		} catch (Exception e) {
			throw new Exception("Exception Occured");
		}

		return requirementList;
	}

	@GetMapping("/getOpenAndQuotedReq/{webUniqueCode}/{reqid}")
	public RequirementDto getReqByWebUniqueCodeAndReqId(@PathVariable(value = "webUniqueCode") String webUniqueCode,
			@PathVariable(value = "reqid") String reqid) throws Exception {

		RequirementDto requirement = null;
		try {
			requirement = requirementService.getRequirement(URLDecoder.decode(webUniqueCode, "UTF-8"), reqid);
		} catch (WeHaulAPIServiceException ex) {
			throw new WeHaulAPIServiceException(HttpServletResponse.SC_NOT_FOUND, " Client Not Verified");
		} catch (Exception e) {
			throw new Exception("Exception Occured");
		}

		return requirement;
	}

	@PostMapping("/addquotes/{webUniqueCode}")
	public int addQuotesToRequirement(@Valid @RequestBody RequirementDto reqdto,
			@PathVariable(value = "webUniqueCode") String webUniqueCode) throws Exception {
		int i = 0;
		try {
			i = requirementService.addQuotesToRequirement(reqdto, URLDecoder.decode(webUniqueCode, "UTF-8"));
			if (i == 1) {
				// get req by ID
				Requirement req = reqRepository.findById(Long.parseLong(reqdto.getReqid())).orElseThrow(
						() -> new ResourceNotFoundException("Requirement", "id", Long.parseLong(reqdto.getReqid())));
				// update requirement status to QUOTED
				req.setLastupdatedby("SYSTEM");
				req.setStatus(AppConstants.ReqStatus.QUOTED);
				req.setRetryAttempts(req.getRetryAttempts() + 1);
				reqRepository.save(req);
				logReqControler.info("req status updated from OPEN to QUOTED {}", req.toString());
			}
		} catch (WeHaulAPIServiceException ex) {
			i = 0;
			throw new WeHaulAPIServiceException(HttpServletResponse.SC_NOT_FOUND, " Client Not Verified");
		} catch (Exception e) {
			i = 0;
			throw new Exception("Exception Occured");
		}
		return i;
	}

	@GetMapping("/getLatestQuotesByReqId/{reqid}")
	public List<QuoteDto> getLatestQuotesByReqId(@PathVariable(value = "reqid") String reqid) throws Exception {

		List<QuoteDto> latestQuotesLst = null;
		try {
			latestQuotesLst = requirementService.getLatestQuotesByReqId(reqid);
		} catch (Exception e) {
			throw new Exception("Exception Occured");
		}

		return latestQuotesLst;
	}

}
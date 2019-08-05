/**
 * 
 */
package com.wehaul.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wehaul.dao.RequirementDao;
import com.wehaul.dto.ClientDto;
import com.wehaul.dto.QuoteDto;
import com.wehaul.dto.RequirementDto;
import com.wehaul.dto.hereapi.LocationDetails;
import com.wehaul.exception.WeHaulAPIServiceException;
import com.wehaul.model.Requirement;
import com.wehaul.model.RequirementDetails;
import com.wehaul.service.RequirementService;
import com.wehaul.service.hereapi.HereApiService;
import com.wehaul.util.EncryptionUtils;

/**
 * @author as.singh
 *
 */
@Service
@Transactional
public class RequirementServiceImpl implements RequirementService {

	private static final Logger logReqService = LoggerFactory.getLogger(RequirementServiceImpl.class);

	@Autowired
	RequirementDao requirementDao;

	@Autowired
	HereApiService hereApiService;

	public List<RequirementDto> getRequirementList(String webUniqueCode) throws Exception {
		// find client by phone .
		String clientId = requirementDao.getClientDetailsByPhone(webUniqueCode);
		if (StringUtils.isEmpty(clientId)) {
			logReqService.info("Client Not Verified {}", webUniqueCode);
			throw new WeHaulAPIServiceException(HttpServletResponse.SC_NOT_FOUND, " Client Not Verified");
		}
		// call the backend to get the
		List<RequirementDto> requiremntList = requirementDao.getRequirementsByClientId(clientId);
		return requiremntList;
	}

	@Override
	public RequirementDto getRequirement(String webUniqueCode, String reqid) throws Exception {
		// find client by phone .
		String clientId = requirementDao.getClientDetailsByPhone(webUniqueCode);
		if (StringUtils.isEmpty(clientId)) {
			logReqService.info("Client Not Verified {}, reqid {}", webUniqueCode, reqid);
			throw new WeHaulAPIServiceException(HttpServletResponse.SC_NOT_FOUND, " Client Not Verified");
		}
		// call the backend to get the
		RequirementDto requiremnt = requirementDao.getReqByClientIdAndReqId(clientId, reqid);
		return requiremnt;
	}

	@Override
	public int addQuotesToRequirement(RequirementDto reqDto, String webUniqueCode) throws Exception {
		// save quotes
		int i = 0;
		try {
			String clientId = requirementDao.getClientDetailsByPhone(webUniqueCode);
			i = requirementDao.addQuote(reqDto, clientId);
		} catch (Exception ex) {
			i = 0;
			logReqService.error("Exception in RequirementServiceImpl.addQuotesToRequirement:::" + ex);
			throw new Exception();
		}
		return i;
	}

	@Override
	public List<QuoteDto> getLatestQuotesByReqId(String reqid) throws Exception {
		return requirementDao.getLatestQuotesByReqId(reqid);
	}

	@Override
	public RequirementDetails getRequirementDetails(Requirement req) {
		RequirementDetails reqdetails = new RequirementDetails();
		// pickup loc details
		LocationDetails pickupLocDetails = hereApiService.getdetailsByLocationId(req.getReqpickuplocid());
		// drop loc details
		LocationDetails dropLocDetails = hereApiService.getdetailsByLocationId(req.getReqdroplocid());

		reqdetails.setPickupaddresslable(pickupLocDetails.getLable());
		reqdetails.setPickupcity(pickupLocDetails.getCity());
		reqdetails.setPickupcountry(pickupLocDetails.getCountry());
		reqdetails.setPickupcounty(pickupLocDetails.getCounty());
		reqdetails.setPickuplat(pickupLocDetails.getLat());
		reqdetails.setPickuplong(pickupLocDetails.getLon());
		reqdetails.setPickuppostcode(pickupLocDetails.getPostcode());
		reqdetails.setPickupstate(pickupLocDetails.getState());

		reqdetails.setDropaddresslable(dropLocDetails.getLable());
		reqdetails.setDropcity(dropLocDetails.getCity());
		reqdetails.setDropcountry(dropLocDetails.getCountry());
		reqdetails.setDropcounty(dropLocDetails.getCounty());
		reqdetails.setDroplat(dropLocDetails.getLat());
		reqdetails.setDroplong(dropLocDetails.getLon());
		reqdetails.setDroppostcode(dropLocDetails.getPostcode());
		reqdetails.setDropstate(dropLocDetails.getState());
		
		reqdetails.setReq(req);
		logReqService.debug("requirement details : {} ", reqdetails);
		return reqdetails;
	}
}

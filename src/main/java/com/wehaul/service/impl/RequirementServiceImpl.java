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
import com.wehaul.exception.WeHaulAPIServiceException;
import com.wehaul.service.RequirementService;
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

	public List<RequirementDto> getRequirementList(String webUniqueCode) throws Exception {
		// find client by phone .
		String clientId = requirementDao.getClientDetailsByPhone(webUniqueCode);
		if (StringUtils.isEmpty(clientId)) {
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
			logReqService.info("Exception in RequirementServiceImpl.addQuotesToRequirement:::" + ex);
			throw new Exception();
		}
		return i;
	}

	@Override
	public List<QuoteDto> getLatestQuotesByReqId(String reqid) throws Exception {
		return requirementDao.getLatestQuotesByReqId(reqid);
	}

}

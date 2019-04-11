/**
 * 
 */
package com.wehaul.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wehaul.dao.RequirementDao;
import com.wehaul.dto.RequirementDto;
import com.wehaul.exception.WeHaulAPIServiceException;
import com.wehaul.service.RequirementService;

/**
 * @author as.singh
 *
 */
@Service
@Transactional
public class RequirementServiceImpl implements RequirementService{
	
	@Autowired
	RequirementDao requirementDao;
	public List<RequirementDto> getRequirementList(String webUniqueCode) throws Exception{
		// find client by phone .
		String clientId = requirementDao.getClientDetailsByPhone(webUniqueCode);
		if(StringUtils.isEmpty(clientId)) {
			throw new WeHaulAPIServiceException(HttpServletResponse.SC_NOT_FOUND," Client Not Verified");
		}
		// call the backend to get the 
		List<RequirementDto> requiremntList = requirementDao.getReqAndQuote(clientId);
		return requiremntList;
	}

}

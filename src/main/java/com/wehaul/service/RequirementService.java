/**
 * 
 */
package com.wehaul.service;

import java.util.List;

import com.wehaul.dto.QuoteDto;
import com.wehaul.dto.RequirementDto;
import com.wehaul.model.Requirement;
import com.wehaul.model.RequirementDetails;

/**
 * @author as.singh
 *
 */
public interface RequirementService {

	public List<RequirementDto> getRequirementList(String webUniqueCode) throws Exception;

	public RequirementDto getRequirement(String webUniqueCode, String reqid) throws Exception;

	public int addQuotesToRequirement(RequirementDto reqDto, String webUniqueCode) throws Exception;

	public List<QuoteDto> getLatestQuotesByReqId(String reqid) throws Exception;

	public RequirementDetails getRequirementDetails(Requirement req) throws Exception;
}

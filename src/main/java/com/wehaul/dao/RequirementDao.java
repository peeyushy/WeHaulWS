/**
 * 
 */
package com.wehaul.dao;

import java.util.List;

import com.wehaul.dto.QuoteDto;
import com.wehaul.dto.RequirementDto;

/**
 * @author as.singh
 *
 */
public interface RequirementDao {
	
	public String getClientDetailsByPhone(String webUniqueCode) throws Exception;
	public List<RequirementDto> getRequirementsByClientId(String clientId) throws Exception;
	public RequirementDto getReqByClientIdAndReqId(String clientId,String reqid) throws Exception;
	public int addQuote(RequirementDto reqDto,String clientId) throws Exception;
	public List<QuoteDto> getLatestQuotesByReqId(String reqid) throws Exception;
}

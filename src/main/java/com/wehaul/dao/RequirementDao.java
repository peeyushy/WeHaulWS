/**
 * 
 */
package com.wehaul.dao;

import java.util.List;

import com.wehaul.dto.RequirementDto;

/**
 * @author as.singh
 *
 */
public interface RequirementDao {
	
	public String getClientDetailsByPhone(String webUniqueCode) throws Exception;
	public List<RequirementDto> getReqAndQuote(String clientId) throws Exception;
	

}

/**
 * 
 */
package com.wehaul.service;

import java.util.List;

import com.wehaul.dto.RequirementDto;

/**
 * @author as.singh
 *
 */
public interface RequirementService {
	
	public List<RequirementDto> getRequirementList(String encryptedId) throws Exception;
   
}

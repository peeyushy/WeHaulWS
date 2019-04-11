/**
 * 
 */
package com.wehaul.dao;

import java.util.List;

import com.wehaul.dto.ClientDto;

/**
 * @author as.singh
 *
 */
public interface RequirementBroadcastDao {
	
	public List<ClientDto> getClientDetails() throws Exception;
	public int insertEncryptedPhone(String encryptedPhone, String clientId, String reqId);
	 public List<String> getEncryptedPhone(String reqId) throws Exception;

}

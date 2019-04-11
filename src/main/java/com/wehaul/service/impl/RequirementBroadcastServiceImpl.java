package com.wehaul.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wehaul.dao.RequirementBroadcastDao;
import com.wehaul.dto.ClientDto;
import com.wehaul.model.Requirement;
import com.wehaul.scheduler.ScheduledTasks;
import com.wehaul.service.RequirementBroadcastService;
import com.wehaul.util.EncryptionUtils;

@Service
@Transactional
public class RequirementBroadcastServiceImpl implements RequirementBroadcastService {
	private static final Logger log = LoggerFactory.getLogger(RequirementBroadcastServiceImpl.class);
	@Autowired
	RequirementBroadcastDao requirementBroadcastDao;
	
	public int createClientWebLink(Requirement requirement) throws Exception{
		log.info("==Starting RequirementBroadcastServiceImpl.createClientWebLink====");
		int i = 0;
		try {
		List<ClientDto> clientList = requirementBroadcastDao.getClientDetails();
		for(ClientDto clientDto : clientList) {
			String encryptedPhone = EncryptionUtils.encrypt(clientDto.getContactNumber());
			requirementBroadcastDao.insertEncryptedPhone(encryptedPhone, clientDto.getClientId().toString(), requirement.getReqid().toString());
			i++;
		  }
		 } catch(Exception ex) {
			 i=0;
			 log.info("==Exception occured in RequirementBroadcastServiceImpl.createClientWebLink===="+ex);
			throw new Exception();
		}
		return i;
	}

}

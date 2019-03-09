package com.wehaul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wehaul.constants.AppConstants;
import com.wehaul.model.Client;
import com.wehaul.model.GetClient;
import com.wehaul.model.Requirement;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {	
	List<Requirement> findRequirementByclient(Client client);
	List<Requirement> findRequirementBystatusIn(List<AppConstants.ReqStatus> statusin);
	List<Requirement> findRequirementBystatusNotIn(List<AppConstants.ReqStatus> statusnotin);
	GetClient findClientidByReqid(Long reqid);
}

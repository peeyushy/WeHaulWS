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
	GetClient findClientidByReqid(Long reqid);
	List<Requirement> findRequirementByclient(Client client);
	List<Requirement> findRequirementByStatus(AppConstants.ReqStatus status);
	List<Requirement> findRequirementBystatusIn(List<AppConstants.ReqStatus> statusin);
	List<Requirement> findRequirementBystatusNotIn(List<AppConstants.ReqStatus> statusnotin);
	List<Requirement> findRequirementByclientAndStatus(Client client,AppConstants.ReqStatus status);
	List<Requirement> findRequirementByclientAndStatusIn(Client client,List<AppConstants.ReqStatus> statusLst);	
}

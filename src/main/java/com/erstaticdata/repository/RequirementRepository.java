package com.erstaticdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erstaticdata.constants.AppConstants;
import com.erstaticdata.model.Client;
import com.erstaticdata.model.Requirement;
import com.erstaticdata.model.GetClient;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {	
	List<Requirement> findRequirementByclient(Client client);
	List<Requirement> findRequirementBystatusIn(List<AppConstants.ReqStatus> statusin);
	List<Requirement> findRequirementBystatusNotIn(List<AppConstants.ReqStatus> statusnotin);
	GetClient findClientidByReqid(Long reqid);
}

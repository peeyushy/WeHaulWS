package com.wehaul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wehaul.constants.AppConstants;
import com.wehaul.model.Client;
import com.wehaul.model.GetClient;
import com.wehaul.model.Requirement;
import com.wehaul.model.RequirementDetails;

@Repository
public interface RequirementDetailsRepository extends JpaRepository<RequirementDetails, Long> {

}

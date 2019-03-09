package com.wehaul.scheduler;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wehaul.constants.AppConstants;
import com.wehaul.constants.AppConstants.ReqStatus;
import com.wehaul.model.Requirement;
import com.wehaul.repository.RequirementRepository;

@Component
public class ScheduledTasks {

	@Autowired
	RequirementRepository reqRepo;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(cron = "${cron.expression.outmessage}")
	public void sendMessages() {
		log.info("The time is now to sendMessages {}", dateFormat.format(new Date()));
		List<AppConstants.ReqStatus> newAndOpenReqStatusLst = new ArrayList<>();
		newAndOpenReqStatusLst.add(AppConstants.ReqStatus.NEW);
		newAndOpenReqStatusLst.add(AppConstants.ReqStatus.OPEN);
		List<Requirement> reqLst = reqRepo.findRequirementBystatusIn(newAndOpenReqStatusLst);
		for (Requirement req : reqLst) {
			req.setLastupdatedby("SYSTEM");			
			req.setStatus(AppConstants.ReqStatus.OPEN);	
			req.setRetryAttempts(req.getRetryAttempts()+1);
			reqRepo.save(req);
			log.info("req open {}", req.toString());
		}
	}

	@Scheduled(cron = "${cron.expression.setexpiry}")
	public void setExpiryForRequirement() {
		log.info("The time is now for setExpiryForRequirement {}", dateFormat.format(new Date()));

		List<AppConstants.ReqStatus> newAndOpenReqStatusLst = new ArrayList<>();
		newAndOpenReqStatusLst.add(AppConstants.ReqStatus.NEW);
		newAndOpenReqStatusLst.add(AppConstants.ReqStatus.OPEN);
		List<Requirement> reqLst = reqRepo.findRequirementBystatusIn(newAndOpenReqStatusLst);
		
		for (Requirement req : reqLst) {
			if (req.getReqdatetime().isBefore(LocalDateTime.now())) {
				req.setLastupdatedby("SYSTEM");
				req.setStatus(AppConstants.ReqStatus.EXPIRED);
				reqRepo.save(req);
				log.info("old request(s) set to EXPIRED {}", req.toString());
			}

		}
	}
}

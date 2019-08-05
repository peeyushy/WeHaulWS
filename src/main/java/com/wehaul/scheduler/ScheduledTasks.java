package com.wehaul.scheduler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wehaul.constants.AppConstants;
import com.wehaul.constants.AppConstants.ClientType;
import com.wehaul.model.Client;
import com.wehaul.model.Requirement;
import com.wehaul.model.RequirementDetails;
import com.wehaul.repository.ClientRepository;
import com.wehaul.repository.RequirementDetailsRepository;
import com.wehaul.repository.RequirementRepository;
import com.wehaul.service.RequirementService;
import com.wehaul.util.EMailUtils;

@Component
public class ScheduledTasks {

	@Autowired
	RequirementRepository reqRepo;

	@Autowired
	RequirementDetailsRepository reqDeatilsRepo;

	@Autowired
	RequirementService reqService;

	@Autowired
	ClientRepository clientRepo;

	@Autowired
	EMailUtils emailUtils;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private static final String EMAIL_SUBJECT = "New load/vehicle available";

	@Value("${weblinkbaseurl}")
	private String WEBLINK_BASE_URL;

	@Value("${email}")
	private boolean EMAIL;

	@Scheduled(cron = "${cron.expression.outmessage}")
	public void sendMessages() {
		log.info("The time is now to sendMessages {}", dateFormat.format(new Date()));
		List<AppConstants.ReqStatus> newReqStatusLst = new ArrayList<>();
		newReqStatusLst.add(AppConstants.ReqStatus.NEW);
		try {
			List<Requirement> reqLst = reqRepo.findRequirementBystatusIn(newReqStatusLst);
			/* Add requirement details & Update status to OPEN */
			for (Requirement req : reqLst) {
				try {
					// get requirement details
					RequirementDetails reqDetails = reqService.getRequirementDetails(req);
					reqDeatilsRepo.save(reqDetails);
					log.info("req details updated {}", reqDetails);
				} catch (Exception ex) {
					ex.printStackTrace();
					log.error("Exception in getting Requirement details for {} , {}", req, ex);
				} finally {
					// update status always even when we had exception while getting details
					req.setLastupdatedby("SYSTEM");
					req.setStatus(AppConstants.ReqStatus.OPEN);
					req.setRetryAttempts(req.getRetryAttempts() + 1);
					reqRepo.save(req);
					log.info("req updated form new to open {}", req);
				}
			}

			/* Send email */
			if (!reqLst.isEmpty() && EMAIL) {
				// call the mail service or SMS service
				List<Client> nonAdminClientsLst = clientRepo.findByclienttypeNot(ClientType.A);
				for (Client client : nonAdminClientsLst) {
					try {
						if (client.getEmail().isEmpty()) {
							log.info(" no email found for client {}", client.getClientname());
						} else {
							if (client.getWebuniquecode().isEmpty()) {
								log.info(" Webuniquecode is empty, Not sending email for: {}", client.getClientname());
							} else {
								emailUtils.sendMail(client.getEmail(), getMessageBody(client), EMAIL_SUBJECT);
								log.info(" email sent to : {} , email address: {}", client.getClientname(),
										client.getEmail());
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						log.error(" Exception in scheduler while sending emails to " + client.getClientname() + " : "
								+ ex);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(" Exception occured in schedular " + ex);
		}
	}

	private String getMessageBody(Client client) throws UnsupportedEncodingException {
		return "Dear " + client.getClientname()
				+ ",\n\nWe have new load/vehicle requirements. Please check the following link and send us your quotes.\n\n"
				+ WEBLINK_BASE_URL + "?cid=" + URLEncoder.encode(client.getWebuniquecode(), "UTF-8") + "\n\nRegards,\n"
				+ "TrucksNLorries";
	}

	@Scheduled(cron = "${cron.expression.setexpiry}")
	public void setExpiryForRequirement() {
		log.info("The time is now for setExpiryForRequirement {}", dateFormat.format(new Date()));

		List<AppConstants.ReqStatus> newAndOpenReqStatusLst = new ArrayList<>();
		newAndOpenReqStatusLst.add(AppConstants.ReqStatus.QUOTED);
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

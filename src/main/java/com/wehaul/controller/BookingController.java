package com.wehaul.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wehaul.dto.BookingDto;
import com.wehaul.exception.WeHaulAPIServiceException;
import com.wehaul.service.BookingService;

@RestController
@RequestMapping("/wehaul/booking")
public class BookingController {

	private static final Logger logReqControler = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	BookingService bookingService;

	@GetMapping("/getBookingByQuoteId/{qid}")
	public BookingDto getBookingByQuoteId(@PathVariable(value = "qid") String qid) throws Exception {

		BookingDto bookingDto = null;
		try {
			bookingDto = bookingService.getBookingDetails(qid);
		} catch (WeHaulAPIServiceException ex) {
			throw new WeHaulAPIServiceException(HttpServletResponse.SC_NOT_FOUND, " Booking details not found");
		} catch (Exception e) {
			throw new Exception("Exception Occured while getting booking details.");
		}

		return bookingDto;
	}
}
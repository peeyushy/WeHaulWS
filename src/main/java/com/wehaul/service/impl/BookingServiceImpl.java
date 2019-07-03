/**
 * 
 */
package com.wehaul.service.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wehaul.dao.BookingDao;
import com.wehaul.dto.BookingDto;
import com.wehaul.service.BookingService;

/**
 * @author as.singh
 *
 */
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	private static final Logger logBookingService = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	BookingDao bookingDao;

	@Override
	public BookingDto getBookingDetails(String qid) throws Exception {
		BookingDto booking = bookingDao.getBookingByQId(qid);
		return booking;
	}

}

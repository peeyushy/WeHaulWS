/**
 * 
 */
package com.wehaul.service;

import com.wehaul.dto.BookingDto;

/**
 * 
 * @author Peeyush
 *
 */
public interface BookingService {

	public BookingDto getBookingDetails(String qid) throws Exception;

}

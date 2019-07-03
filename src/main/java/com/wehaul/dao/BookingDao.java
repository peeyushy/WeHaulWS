/**
 * 
 */
package com.wehaul.dao;

import com.wehaul.dto.BookingDto;

/**
 * 
 * @author Peeyush
 *
 */

public interface BookingDao {

	public BookingDto getBookingByQId(String qid) throws Exception;

}

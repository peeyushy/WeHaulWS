/**
 * 
 */
package com.wehaul.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wehaul.dao.BookingDao;
import com.wehaul.dto.BookingDto;

/**
 * 
 * @author Peeyush
 *
 */
@Repository
public class BookingDaoImpl implements BookingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public final String GET_BOOKING_BY_QUOTEID = "SELECT req.reqid,req.reqdatetime,req.comments,req.reqdroploc,req.reqpickuploc,"
			+ " req.reqdatetimeflexi,req.reqpickupdropflexi,req.reqtype,vtype.vtypename,ltype.ltypename,clients.clientname,"
			+ " clients.contactno,req_quo.quote,req_quo.comment,req_quo.createdat FROM t_requirements req,t_requirement_quotes req_quo,"
			+ " t_clients clients,t_vehicletype vtype,t_loadtype ltype WHERE req.reqid = req_quo.reqid AND "
			+ " clients.clientid = req_quo.clientid AND req.vtypeid = vtype.vtypeid AND req.ltypeid = ltype.ltypeid AND "
			+ " req.status = 'QUOTED' AND req_quo.req_quote_id = :qid";

	@Override
	public BookingDto getBookingByQId(String qid) throws Exception {

		final MapSqlParameterSource params = new MapSqlParameterSource().addValue("qid", qid);
		final NamedParameterJdbcTemplate queryInterface = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		try {
			BookingDto result = (BookingDto) queryInterface.queryForObject(GET_BOOKING_BY_QUOTEID, params,
					new RowMapper<BookingDto>() {
						@Override
						public BookingDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							BookingDto booking = new BookingDto();
							booking.setReqId(rs.getString("reqid"));
							booking.setReqDateTime(rs.getTimestamp("reqdatetime").toLocalDateTime());
							booking.setReqComment(rs.getString("comments"));
							booking.setReqDropLoc(rs.getString("reqdroploc"));
							booking.setReqPickupLoc(rs.getString("reqpickuploc"));
							booking.setReqDateTimeFlexi(rs.getBoolean("reqdatetimeflexi"));
							booking.setReqPickupDropFlexi(rs.getBoolean("reqpickupdropflexi"));
							booking.setReqType(rs.getString("reqtype"));
							booking.setvType(rs.getString("vtypename"));
							booking.setlType(rs.getString("ltypename"));

							booking.setQuoteOwner(rs.getString("clientname"));
							booking.setQuoteContact(rs.getString("contactno"));
							booking.setQuote(rs.getString("quote"));
							booking.setQuoteComment(rs.getString("comment"));
							booking.setQuoteReceivedDateTime(rs.getTimestamp("createdat").toLocalDateTime());

							return booking;
						}

					});
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}

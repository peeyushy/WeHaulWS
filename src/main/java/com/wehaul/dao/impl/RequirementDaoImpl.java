/**
 * 
 */
package com.wehaul.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wehaul.dao.RequirementDao;
import com.wehaul.dto.QuoteDto;
import com.wehaul.dto.RequirementDto;

/**
 * @author as.singh
 *
 */
@Repository
public class RequirementDaoImpl implements RequirementDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public final String SELECT_CLIENT_BY_ENCRYPTED_PHONE = " select clientid from t_clients where active = 1 and webuniquecode = :webUniqueCode";

	public final String SELECT_REQ_BY_CLIENTID = " select  req.reqid,req.reqdatetime, req.comments, req.reqdroploc , req.reqpickuploc, req.reqdatetimeflexi "
			+ " , req.reqpickupdropflexi, req.reqtype, vtype.vtypename, ltype.ltypename, latestquotes.quote,latestquotes.comment "
			+ " from t_requirements req left outer join "
			+ " (SELECT t_requirement_quotes.* FROM (SELECT reqid,clientid, MAX(createdat) AS createdat FROM t_requirement_quotes GROUP BY reqid,clientid) AS latest_quotes INNER JOIN t_requirement_quotes ON t_requirement_quotes.reqid = latest_quotes.reqid AND t_requirement_quotes.clientid = latest_quotes.clientid AND t_requirement_quotes.createdat = latest_quotes.createdat) latestquotes on req.reqid = latestquotes.reqid and latestquotes.clientid =  :clientId, "
			+ " t_vehicletype vtype, t_loadtype ltype "
			+ "  where req.status in ('OPEN', 'QUOTED') AND req.clientid!= :clientId "
			+ " and vtype.vtypeid = req.vtypeid and ltype.ltypeid = req.ltypeid";

	public final String SELECT_REQ_BY_CLIENTID_AND_REQID = " select  req.reqid,req.reqdatetime, req.comments, req.reqdroploc , req.reqpickuploc, req.reqdatetimeflexi "
			+ " , req.reqpickupdropflexi, req.reqtype, vtype.vtypename, ltype.ltypename, latestquotes.quote,latestquotes.comment "
			+ " from t_requirements req left outer join "
			+ " (SELECT t_requirement_quotes.* FROM (SELECT reqid,clientid, MAX(createdat) AS createdat FROM t_requirement_quotes GROUP BY reqid,clientid) AS latest_quotes INNER JOIN t_requirement_quotes ON t_requirement_quotes.reqid = latest_quotes.reqid AND t_requirement_quotes.clientid = latest_quotes.clientid AND t_requirement_quotes.createdat = latest_quotes.createdat) latestquotes on req.reqid = latestquotes.reqid and latestquotes.clientid =  :clientId, "
			+ " t_vehicletype vtype, t_loadtype ltype "
			+ "  where req.status in ('OPEN', 'QUOTED') AND req.reqid= :reqid "
			+ " and vtype.vtypeid = req.vtypeid and ltype.ltypeid = req.ltypeid";

	public final String INSERT_QUOTES = " insert into t_requirement_quotes (reqid, clientid, quote,comment)"
			+ " values (:reqId, :clientId, :quote,:comment)";

	public final String SELECT_ALL_QUOTES_FOR_REQ = " SELECT latestquotes.req_quote_id,c.clientid,c.clientname,c.contactno,latestquotes.quote,latestquotes.comment,latestquotes.createdat FROM "
			+ " t_clients c, (SELECT t_requirement_quotes.* FROM (SELECT reqid, clientid, MAX(createdat) AS createdat FROM t_requirement_quotes GROUP BY reqid , clientid) AS latest_quotes "
			+ " INNER JOIN t_requirement_quotes ON t_requirement_quotes.reqid = latest_quotes.reqid AND t_requirement_quotes.clientid = latest_quotes.clientid AND t_requirement_quotes.createdat = latest_quotes.createdat AND t_requirement_quotes.reqid = :reqid) latestquotes "
			+ " WHERE c.clientid = latestquotes.clientid ORDER BY latestquotes.quote asc";

	@Override
	public String getClientDetailsByPhone(String webUniqueCode) throws Exception {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("webUniqueCode", webUniqueCode);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		String clientId = null;
		try {
			clientId = namedParameterJdbcTemplate.queryForObject(SELECT_CLIENT_BY_ENCRYPTED_PHONE, params,
					String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return clientId;
	}

	@Override
	public List<RequirementDto> getRequirementsByClientId(String clientId) throws Exception {

		final MapSqlParameterSource params = new MapSqlParameterSource().addValue("clientId", clientId);
		final NamedParameterJdbcTemplate queryInterface = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

		List<RequirementDto> result = queryInterface.query(SELECT_REQ_BY_CLIENTID, params,
				new RowMapper<RequirementDto>() {
					@Override
					public RequirementDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						RequirementDto requirement = new RequirementDto();
						requirement.setReqid(rs.getString("reqid"));
						requirement.setReqdatetime(rs.getTimestamp("reqdatetime").toLocalDateTime());
						requirement.setReqComment(rs.getString("comments"));
						requirement.setReqdroploc(rs.getString("reqdroploc"));
						requirement.setReqpickuploc(rs.getString("reqpickuploc"));
						requirement.setReqdatetimeflexi(rs.getBoolean("reqdatetimeflexi"));
						requirement.setReqpickupdropflexi(rs.getBoolean("reqpickupdropflexi"));
						requirement.setReqtype(rs.getString("reqtype"));
						requirement.setVtype(rs.getString("vtypename"));
						requirement.setLtype(rs.getString("ltypename"));
						requirement.setClientComment(rs.getString("comment"));
						requirement.setQuote(rs.getString("quote"));
						return requirement;
					}

				});
		return result;
	}

	@Override
	public RequirementDto getReqByClientIdAndReqId(String clientId, String reqid) throws Exception {
		final MapSqlParameterSource params = new MapSqlParameterSource().addValue("clientId", clientId)
				.addValue("reqid", reqid);
		final NamedParameterJdbcTemplate queryInterface = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

		RequirementDto result = (RequirementDto) queryInterface.queryForObject(SELECT_REQ_BY_CLIENTID_AND_REQID, params,
				new RowMapper<RequirementDto>() {
					@Override
					public RequirementDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						RequirementDto requirement = new RequirementDto();
						requirement.setReqid(rs.getString("reqid"));
						requirement.setReqdatetime(rs.getTimestamp("reqdatetime").toLocalDateTime());
						requirement.setReqComment(rs.getString("comments"));
						requirement.setReqdroploc(rs.getString("reqdroploc"));
						requirement.setReqpickuploc(rs.getString("reqpickuploc"));
						requirement.setReqdatetimeflexi(rs.getBoolean("reqdatetimeflexi"));
						requirement.setReqpickupdropflexi(rs.getBoolean("reqpickupdropflexi"));
						requirement.setReqtype(rs.getString("reqtype"));
						requirement.setVtype(rs.getString("vtypename"));
						requirement.setLtype(rs.getString("ltypename"));
						requirement.setClientComment(rs.getString("comment"));
						requirement.setQuote(rs.getString("quote"));
						return requirement;
					}

				});
		return result;
	}

	@Override
	public int addQuote(RequirementDto reqDto, String clientId) throws Exception {
		// save quotes
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("reqId", reqDto.getReqid())
				.addValue("clientId", clientId).addValue("quote", reqDto.getQuote())
				.addValue("comment", reqDto.getClientComment());

		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		return namedParameterJdbcTemplate.update(INSERT_QUOTES, params);
	}

	@Override
	public List<QuoteDto> getLatestQuotesByReqId(String reqid) throws Exception {

		final MapSqlParameterSource params = new MapSqlParameterSource().addValue("reqid", reqid);
		final NamedParameterJdbcTemplate queryInterface = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

		List<QuoteDto> result = queryInterface.query(SELECT_ALL_QUOTES_FOR_REQ, params, new RowMapper<QuoteDto>() {
			@Override
			public QuoteDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				QuoteDto quote = new QuoteDto();
				quote.setqId(rs.getString("req_quote_id"));
				quote.setqOwnerId(rs.getString("clientid"));
				quote.setqOwnerName(rs.getString("clientname"));
				quote.setqOwnerContactNo(rs.getString("contactno"));
				quote.setQuote(rs.getString("quote"));
				quote.setqComment(rs.getString("comment"));
				quote.setqDatetime(rs.getTimestamp("createdat").toLocalDateTime());
				return quote;
			}
		});
		return result;
	}
}

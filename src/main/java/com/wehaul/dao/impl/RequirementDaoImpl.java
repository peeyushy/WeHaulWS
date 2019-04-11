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
import com.wehaul.dto.RequirementDto;

/**
 * @author as.singh
 *
 */
@Repository
public class RequirementDaoImpl implements RequirementDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public final String SLECT_CLIENT_BY_ENCRYPTED_PHONE = " select webuniquecode from tnldb.t_clients where active = 1 and webuniquecode = :webUniqueCode";

	public final String SLECT_REQ_AND_QUOTE = " select  req.reqid, req.comments, req.reqdroploc , req.reqpickuploc, req.reqdatetimeflexi "
			+ " , req.reqpickupdropflexi, req.reqtype, vtype.vtypename, ltype.ltypename, reqQuotes.quote,reqQuotes.comment "
			+ " from tnldb.t_requirements req left outer join "
			+ " tnldb.t_requirement_quotes reqQuotes  on req.reqid = reqQuotes.reqid and reqQuotes.clientid =  :clientId, "
			+ " tnldb.t_vehicletype vtype, tnldb.t_loadtype ltype " + "  where req.status in ('OPEN', 'QUOTED')  "
			+ " and vtype.vtypeid = req.vtypeid and ltype.ltypeid = req.ltypeid";

	@Override
	public String getClientDetailsByPhone(String webUniqueCode) throws Exception {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("webUniqueCode", webUniqueCode);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		String clientId = null;
		try {
		clientId = namedParameterJdbcTemplate.queryForObject(SLECT_CLIENT_BY_ENCRYPTED_PHONE, params,
				String.class);
		}
		catch (EmptyResultDataAccessException e) {
			   return null;
		  }
		return clientId;
	}

	@Override
	public List<RequirementDto> getReqAndQuote(String clientId) throws Exception {

		final MapSqlParameterSource params = new MapSqlParameterSource().addValue("clientId", clientId);
		final NamedParameterJdbcTemplate queryInterface = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

		List<RequirementDto> result = queryInterface.query(SLECT_REQ_AND_QUOTE, params,
				new RowMapper<RequirementDto>() {
					@Override
					public RequirementDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						RequirementDto requirement = new RequirementDto();
						requirement.setReqid(rs.getString("reqid"));
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

}

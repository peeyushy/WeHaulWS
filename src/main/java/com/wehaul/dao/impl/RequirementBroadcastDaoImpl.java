/**
 * 
 */
package com.wehaul.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wehaul.dao.RequirementBroadcastDao;
import com.wehaul.dto.ClientDto;

/**
 * @author as.singh
 *
 */
@Repository
public class RequirementBroadcastDaoImpl implements RequirementBroadcastDao{
	
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	 public final String SELECT_CLIENT_SQL = " select clientid, clientname, contactno, email from t_clients where active = 1 "; 
	 public final String INSERT_ENCRYPTED_PHONE = " insert into t_weblink_details (reqid, clientid, webuniquecode)"
	 		+ " values (:reqId, :clientId, :encryptedPhone)";
	 
	 public final String SLECT_CLIENT_ENCRYPTED_PHONE = " select webuniquecode from t_weblink_details where reqid = :reqId";

	 @Override
	public List<ClientDto> getClientDetails() throws Exception{
		List<ClientDto> result = jdbcTemplate.query(
				SELECT_CLIENT_SQL, new RowMapper<ClientDto>() {
					@Override
					public ClientDto mapRow(ResultSet rs, int rowNum) throws SQLException{
						ClientDto clientDto  = new ClientDto();
						clientDto.setClientId(rs.getLong("clientid"));
						clientDto.setClientName(rs.getString("clientname"));
						clientDto.setContactNumber(rs.getString("contactno"));
						clientDto.setEmail(rs.getString("email"));
						return clientDto;
					}
					
				});
        return result;
	}
	 
	 @Override
	 public int insertEncryptedPhone(String encryptedPhone, String clientId, String reqId) {
		 
		 MapSqlParameterSource params = new MapSqlParameterSource()
				 .addValue("reqId", reqId)
				 .addValue("clientId", clientId)
				 .addValue("encryptedPhone", encryptedPhone);
		 
		 NamedParameterJdbcTemplate  namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		 return namedParameterJdbcTemplate.update(INSERT_ENCRYPTED_PHONE, params);
	 }
	 
	 public List<String> getEncryptedPhone(String reqId) throws Exception{
		 
		 MapSqlParameterSource params = new MapSqlParameterSource().addValue("reqId", reqId);
		 NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		 List<String> encryptedPhone = namedParameterJdbcTemplate.queryForList(SLECT_CLIENT_ENCRYPTED_PHONE, params, String.class);
		 return encryptedPhone;
	 }
}

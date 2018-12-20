package com.erstaticdata.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erstaticdata.dto.LoadSearchOptionsDto;
import com.erstaticdata.model.Load;

@Repository
@Transactional(readOnly = true)
public class LoadRepositoryImpl implements LoadRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Load> getLoadByVehicleId(Long vid) {
		Query query = entityManager.createNativeQuery("select L.* "
				+ "from T_LOADS L,T_VEHICLES V,T_VEHICLE_LOADTYPE VLT,T_LOADTYPE LT,T_VEHICLETYPE VT,T_CLIENTS C "
				+ "where V.VID=? AND V.CLIENTID=C.CLIENTID AND L.LTYPEID=VLT.LTYPEID AND V.VID=VLT.VID AND L.STATUS='ACTIVE' AND V.STATUS='ACTIVE' AND "
				+ "V.VTYPEID=VT.VTYPEID AND VT.STATUS='ACTIVE' AND L.LTYPEID=LT.LTYPEID AND LT.STATUS='ACTIVE' ",
				Load.class);
		query.setParameter(1, vid);

		return query.getResultList();
	}

	@Override
	public List<Load> getLoadBySearchOptions(LoadSearchOptionsDto loadSearchOptionsDto) {
		String queryStr = "select L.* "
				+ "from T_LOADS L,T_VEHICLES V,T_VEHICLE_LOADTYPE VLT,T_LOADTYPE LT,T_VEHICLETYPE VT,T_CLIENTS C "
				+ "where L.CLIENTID=C.CLIENTID AND L.LTYPEID=VLT.LTYPEID AND V.VID=VLT.VID AND L.STATUS='ACTIVE' AND V.STATUS='ACTIVE' AND "
				+ "V.VTYPEID=VT.VTYPEID AND VT.STATUS='ACTIVE' AND L.LTYPEID=LT.LTYPEID AND LT.STATUS='ACTIVE' ";

		if (!loadSearchOptionsDto.getLoad().getLpickuploc().isEmpty()) {
			queryStr = queryStr + "AND L.LPICKUPLOC=:LPICKUPLOC ";
		}
		if (!loadSearchOptionsDto.getLoad().getLdroploc().isEmpty()) {
			queryStr = queryStr + "AND L.LDROPLOC=:LDROPLOC ";
		}
		if (loadSearchOptionsDto.getLdatetime_start() != null) {
			queryStr = queryStr + "AND L.LDATETIME > :LDATETIME_START ";
		}
		if (loadSearchOptionsDto.getLdatetime_end() != null) {
			queryStr = queryStr + "AND L.LDATETIME < :LDATETIME_END ";
		}
		if (loadSearchOptionsDto.getVehicle().getVtype() != null
				&& loadSearchOptionsDto.getVehicle().getVtype().getVtypeid() != null) {
			queryStr = queryStr + "AND V.VTYPEID=:VTYPEID ";
		}

		Query query = entityManager.createNativeQuery(queryStr, Load.class);

		if (!loadSearchOptionsDto.getLoad().getLpickuploc().isEmpty()) {
			query.setParameter("LPICKUPLOC", loadSearchOptionsDto.getLoad().getLpickuploc());
		}
		if (!loadSearchOptionsDto.getLoad().getLdroploc().isEmpty()) {
			query.setParameter("LDROPLOC", loadSearchOptionsDto.getLoad().getLdroploc());
		}
		if (loadSearchOptionsDto.getLdatetime_start() != null) {
			query.setParameter("LDATETIME_START", loadSearchOptionsDto.getLdatetime_start());
		}
		if (loadSearchOptionsDto.getLdatetime_end() != null) {
			query.setParameter("LDATETIME_END", loadSearchOptionsDto.getLdatetime_end());
		}
		if (loadSearchOptionsDto.getVehicle().getVtype() != null
				&& loadSearchOptionsDto.getVehicle().getVtype().getVtypeid() != null) {
			query.setParameter("VTYPEID", loadSearchOptionsDto.getVehicle().getVtype().getVtypeid());
		}

		return query.getResultList();

	}

}

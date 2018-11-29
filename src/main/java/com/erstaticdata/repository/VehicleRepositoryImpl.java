package com.erstaticdata.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erstaticdata.model.Vehicle;

@Repository
@Transactional(readOnly = true)
public class VehicleRepositoryImpl implements VehicleRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Vehicle> getVehicleByLoadId(Long lid) {
		Query query = entityManager.createNativeQuery("select V.* "
				+ "from T_LOADS L,T_VEHICLES V,T_VEHICLE_LOADTYPE VLT,T_LOADTYPE LT,T_VEHICLETYPE VT,T_CLIENTS C "
				+ "where L.LID=? AND V.CLIENTID=C.CLIENTID AND L.LTYPEID=VLT.LTYPEID AND V.VID=VLT.VID AND L.STATUS='ACTIVE' AND V.STATUS='ACTIVE' AND "
				+ "V.VTYPEID=VT.VTYPEID AND VT.STATUS='ACTIVE' AND L.LTYPEID=LT.LTYPEID AND LT.STATUS='ACTIVE' ",
				Vehicle.class);
		query.setParameter(1, lid);

		return query.getResultList();
	}

}

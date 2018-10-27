package com.erstaticdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erstaticdata.model.LoadType;

@Repository
public interface LoadTypeRepository extends JpaRepository<LoadType, Long> {
	@Query("SELECT lt FROM LoadType lt WHERE lt.status='ACTIVE'")
	List<LoadType> findAllActiveLoadType();
}

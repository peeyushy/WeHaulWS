package com.erstaticdata.repository;

import java.util.List;

import com.erstaticdata.dto.LoadSearchOptionsDto;
import com.erstaticdata.model.Load;

public interface LoadRepositoryCustom {
	List<Load> getLoadByVehicleId(Long vid);
	
	List<Load> getLoadBySearchOptions(LoadSearchOptionsDto loadSearchOptionsDto);	
	
}
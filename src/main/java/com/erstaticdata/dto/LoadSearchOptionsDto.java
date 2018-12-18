package com.erstaticdata.dto;

import java.time.LocalDateTime;

import com.erstaticdata.model.Load;
import com.erstaticdata.model.Vehicle;

public class LoadSearchOptionsDto {
	
	private Load load;
	private Vehicle vehicle;
	private LocalDateTime ldatetime_start;
	private LocalDateTime ldatetime_end;
	
	/**
	 * @return the load
	 */
	public Load getLoad() {
		return load;
	}
	/**
	 * @param load the load to set
	 */
	public void setLoad(Load load) {
		this.load = load;
	}
	/**
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}
	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	/**
	 * @return the ldatetime_start
	 */
	public LocalDateTime getLdatetime_start() {
		return ldatetime_start;
	}
	/**
	 * @param ldatetime_start the ldatetime_start to set
	 */
	public void setLdatetime_start(LocalDateTime ldatetime_start) {
		this.ldatetime_start = ldatetime_start;
	}
	/**
	 * @return the ldatetime_end
	 */
	public LocalDateTime getLdatetime_end() {
		return ldatetime_end;
	}
	/**
	 * @param ldatetime_end the ldatetime_end to set
	 */
	public void setLdatetime_end(LocalDateTime ldatetime_end) {
		this.ldatetime_end = ldatetime_end;
	}
}

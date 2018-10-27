package com.erstaticdata.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import constants.AppConstants;

@Entity
@Table(name = "T_VEHICLETYPE")
public class VehicleType implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vtypeid;
	
	@NotBlank
	private String vtypename;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private AppConstants.Status status;

	private String vtypedesc;

	/**
	 * @return the vtypeid
	 */
	public Long getVtypeid() {
		return vtypeid;
	}

	/**
	 * @param vtypeid the vtypeid to set
	 */
	public void setVtypeid(Long vtypeid) {
		this.vtypeid = vtypeid;
	}

	/**
	 * @return the vtypename
	 */
	public String getVtypename() {
		return vtypename;
	}

	/**
	 * @param vtypename the vtypename to set
	 */
	public void setVtypename(String vtypename) {
		this.vtypename = vtypename;
	}	

	public AppConstants.Status getStatus() {
		return status;
	}

	public void setStatus(AppConstants.Status status) {
		this.status = status;
	}

	/**
	 * @return the vtypedesc
	 */
	public String getVtypedesc() {
		return vtypedesc;
	}

	/**
	 * @param vtypedesc the vtypedesc to set
	 */
	public void setVtypedesc(String vtypedesc) {
		this.vtypedesc = vtypedesc;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((vtypedesc == null) ? 0 : vtypedesc.hashCode());
		result = prime * result + ((vtypeid == null) ? 0 : vtypeid.hashCode());
		result = prime * result + ((vtypename == null) ? 0 : vtypename.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof VehicleType))
			return false;
		VehicleType other = (VehicleType) obj;
		if (status != other.status)
			return false;
		if (vtypedesc == null) {
			if (other.vtypedesc != null)
				return false;
		} else if (!vtypedesc.equals(other.vtypedesc))
			return false;
		if (vtypeid == null) {
			if (other.vtypeid != null)
				return false;
		} else if (!vtypeid.equals(other.vtypeid))
			return false;
		if (vtypename == null) {
			if (other.vtypename != null)
				return false;
		} else if (!vtypename.equals(other.vtypename))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VehicleType [vtypeid=" + vtypeid + ", vtypename=" + vtypename + ", status=" + status + ", vtypedesc="
				+ vtypedesc + "]";
	}	
}

package com.erstaticdata.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import constants.AppConstants;

@Entity
@Table(name = "T_LOADTYPE")
public class LoadType implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ltypeid;
	
	@NotBlank
	private String ltypename;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private AppConstants.Status status;
	
	private String ltypedesc;
	
	/**
	 * @return the ltypeid
	 */
	public Long getLtypeid() {
		return ltypeid;
	}

	/**
	 * @param ltypeid the ltypeid to set
	 */
	public void setLtypeid(Long ltypeid) {
		this.ltypeid = ltypeid;
	}

	/**
	 * @return the ltypename
	 */
	public String getLtypename() {
		return ltypename;
	}

	/**
	 * @param ltypename the ltypename to set
	 */
	public void setLtypename(String ltypename) {
		this.ltypename = ltypename;
	}
	
	/**
	 * @return the status
	 */
	public AppConstants.Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(AppConstants.Status status) {
		this.status = status;
	}

	/**
	 * @return the ltypedesc
	 */
	public String getLtypedesc() {
		return ltypedesc;
	}

	/**
	 * @param ltypedesc the ltypedesc to set
	 */
	public void setLtypedesc(String ltypedesc) {
		this.ltypedesc = ltypedesc;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ltypedesc == null) ? 0 : ltypedesc.hashCode());
		result = prime * result + ((ltypeid == null) ? 0 : ltypeid.hashCode());
		result = prime * result + ((ltypename == null) ? 0 : ltypename.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (!(obj instanceof LoadType))
			return false;
		LoadType other = (LoadType) obj;
		if (ltypedesc == null) {
			if (other.ltypedesc != null)
				return false;
		} else if (!ltypedesc.equals(other.ltypedesc))
			return false;
		if (ltypeid == null) {
			if (other.ltypeid != null)
				return false;
		} else if (!ltypeid.equals(other.ltypeid))
			return false;
		if (ltypename == null) {
			if (other.ltypename != null)
				return false;
		} else if (!ltypename.equals(other.ltypename))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public LoadType() {
		super();
		// TODO Auto-generated constructor stub
	}
				
}

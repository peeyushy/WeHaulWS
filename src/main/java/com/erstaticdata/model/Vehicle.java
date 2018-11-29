package com.erstaticdata.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EntityResult;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import constants.AppConstants;

@Entity
@Table(name = "T_VEHICLES")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)

public class Vehicle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vid;

	@NotBlank
	private String regno;

	@OneToOne
	@JoinColumn(name = "vtypeid")
	private VehicleType vtype;

	@ManyToMany
	@JoinTable(name = "T_VEHICLE_LOADTYPE", joinColumns = @JoinColumn(name = "VID"), inverseJoinColumns = @JoinColumn(name = "LTYPEID"))
	private List<LoadType> ltype = new ArrayList<>();

	private String vehicleavatar;

	private int opcost;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AppConstants.Status status;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "clientid", referencedColumnName = "clientid")
	@JsonBackReference
	private Client client;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date CREATEDAT;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date UPDATEDAT;

	@NotBlank
	private String createdby;

	@NotBlank
	private String lastupdatedby;

	/**
	 * @return the vid
	 */
	public Long getVid() {
		return vid;
	}

	/**
	 * @param vid the vid to set
	 */
	public void setVid(Long vid) {
		this.vid = vid;
	}

	/**
	 * @return the regno
	 */
	public String getRegno() {
		return regno;
	}

	/**
	 * @param regno the regno to set
	 */
	public void setRegno(String regno) {
		this.regno = regno;
	}

	/**
	 * @return the vtype
	 */
	public VehicleType getVtype() {
		return vtype;
	}

	/**
	 * @param vtype the vtype to set
	 */
	public void setVtype(VehicleType vtype) {
		this.vtype = vtype;
	}

	/**
	 * @return the ltype
	 */
	public List<LoadType> getLtype() {
		return ltype;
	}

	/**
	 * @param ltype the ltype to set
	 */
	public void setLtype(List<LoadType> ltype) {
		this.ltype = ltype;
	}

	public String getVehicleavatar() {
		return vehicleavatar;
	}

	public void setVehicleavatar(String vehicleavatar) {
		this.vehicleavatar = vehicleavatar;
	}

	/**
	 * @return the opcost
	 */
	public int getOpcost() {
		return opcost;
	}

	/**
	 * @param opcost the opcost to set
	 */
	public void setOpcost(int opcost) {
		this.opcost = opcost;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the cREATEDAT
	 */
	public Date getCREATEDAT() {
		return CREATEDAT;
	}

	/**
	 * @param cREATEDAT the cREATEDAT to set
	 */
	public void setCREATEDAT(Date cREATEDAT) {
		CREATEDAT = cREATEDAT;
	}

	/**
	 * @return the uPDATEDAT
	 */
	public Date getUPDATEDAT() {
		return UPDATEDAT;
	}

	/**
	 * @param uPDATEDAT the uPDATEDAT to set
	 */
	public void setUPDATEDAT(Date uPDATEDAT) {
		UPDATEDAT = uPDATEDAT;
	}

	/**
	 * @return the createdby
	 */
	public String getCreatedby() {
		return createdby;
	}

	/**
	 * @param createdby the createdby to set
	 */
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	/**
	 * @return the lastupdatedby
	 */
	public String getLastupdatedby() {
		return lastupdatedby;
	}

	/**
	 * @param lastupdatedby the lastupdatedby to set
	 */
	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CREATEDAT == null) ? 0 : CREATEDAT.hashCode());
		result = prime * result + ((UPDATEDAT == null) ? 0 : UPDATEDAT.hashCode());
		result = prime * result + ((vehicleavatar == null) ? 0 : vehicleavatar.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((createdby == null) ? 0 : createdby.hashCode());
		result = prime * result + ((lastupdatedby == null) ? 0 : lastupdatedby.hashCode());
		result = prime * result + ((ltype == null) ? 0 : ltype.hashCode());
		result = prime * result + opcost;
		result = prime * result + ((regno == null) ? 0 : regno.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((vid == null) ? 0 : vid.hashCode());
		result = prime * result + ((vtype == null) ? 0 : vtype.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vehicle))
			return false;
		Vehicle other = (Vehicle) obj;
		if (CREATEDAT == null) {
			if (other.CREATEDAT != null)
				return false;
		} else if (!CREATEDAT.equals(other.CREATEDAT))
			return false;
		if (UPDATEDAT == null) {
			if (other.UPDATEDAT != null)
				return false;
		} else if (!UPDATEDAT.equals(other.UPDATEDAT))
			return false;
		if (vehicleavatar == null) {
			if (other.vehicleavatar != null)
				return false;
		} else if (!vehicleavatar.equals(other.vehicleavatar))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (createdby == null) {
			if (other.createdby != null)
				return false;
		} else if (!createdby.equals(other.createdby))
			return false;
		if (lastupdatedby == null) {
			if (other.lastupdatedby != null)
				return false;
		} else if (!lastupdatedby.equals(other.lastupdatedby))
			return false;
		if (ltype == null) {
			if (other.ltype != null)
				return false;
		} else if (!ltype.equals(other.ltype))
			return false;
		if (opcost != other.opcost)
			return false;
		if (regno == null) {
			if (other.regno != null)
				return false;
		} else if (!regno.equals(other.regno))
			return false;
		if (status != other.status)
			return false;
		if (vid == null) {
			if (other.vid != null)
				return false;
		} else if (!vid.equals(other.vid))
			return false;
		if (vtype == null) {
			if (other.vtype != null)
				return false;
		} else if (!vtype.equals(other.vtype))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vehicle [vid=" + vid + ", regno=" + regno + ", vtype=" + vtype + ", ltype=" + ltype + ", avatar="
				+ vehicleavatar + ", opcost=" + opcost + ", status=" + status + ", client=" + client + ", CREATEDAT="
				+ CREATEDAT + ", UPDATEDAT=" + UPDATEDAT + ", createdby=" + createdby + ", lastupdatedby="
				+ lastupdatedby + "]";
	}

}

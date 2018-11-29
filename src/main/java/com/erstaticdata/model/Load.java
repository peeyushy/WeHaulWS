package com.erstaticdata.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "T_LOADS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Load implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lid;

	@NotBlank
	private String lpickuploc;

	@NotBlank
	private String ldroploc;

	private boolean lpickupdropflexi = true;

	@OneToOne
	@JoinColumn(name = "ltypeid")
	private LoadType ltype;

	private boolean lassistance = false;
	
	@NotNull
	private LocalDateTime ldatetime;

	private boolean ldatetimeflexi = true;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AppConstants.LoadStatus status;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "clientid", referencedColumnName = "clientid")
	@JsonBackReference
	private Client client;

	private String comments;

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
	 * @return the lid
	 */
	public Long getLid() {
		return lid;
	}

	/**
	 * @param lid the lid to set
	 */
	public void setLid(Long lid) {
		this.lid = lid;
	}

	/**
	 * @return the lpickuploc
	 */
	public String getLpickuploc() {
		return lpickuploc;
	}

	/**
	 * @param lpickuploc the lpickuploc to set
	 */
	public void setLpickuploc(String lpickuploc) {
		this.lpickuploc = lpickuploc;
	}

	/**
	 * @return the ldroploc
	 */
	public String getLdroploc() {
		return ldroploc;
	}

	/**
	 * @param ldroploc the ldroploc to set
	 */
	public void setLdroploc(String ldroploc) {
		this.ldroploc = ldroploc;
	}

	/**
	 * @return the lpickupdropflexi
	 */
	public boolean isLpickupdropflexi() {
		return lpickupdropflexi;
	}

	/**
	 * @param lpickupdropflexi the lpickupdropflexi to set
	 */
	public void setLpickupdropflexi(boolean lpickupdropflexi) {
		this.lpickupdropflexi = lpickupdropflexi;
	}

	/**
	 * @return the ltype
	 */
	public LoadType getLtype() {
		return ltype;
	}

	/**
	 * @param ltype the ltype to set
	 */
	public void setLtype(LoadType ltype) {
		this.ltype = ltype;
	}

	/**
	 * @return the lassistance
	 */
	public boolean isLassistance() {
		return lassistance;
	}

	/**
	 * @param lassistance the lassistance to set
	 */
	public void setLassistance(boolean lassistance) {
		this.lassistance = lassistance;
	}

	/**
	 * @return the ldatetime
	 */
	public LocalDateTime getLdatetime() {
		return ldatetime;
	}

	/**
	 * @param ldatetime the ldatetime to set
	 */
	public void setLdatetime(LocalDateTime ldatetime) {
		this.ldatetime = ldatetime;
	}

	/**
	 * @return the ldatetimeflexi
	 */
	public boolean isLdatetimeflexi() {
		return ldatetimeflexi;
	}

	/**
	 * @param ldatetimeflexi the ldatetimeflexi to set
	 */
	public void setLdatetimeflexi(boolean ldatetimeflexi) {
		this.ldatetimeflexi = ldatetimeflexi;
	}

	/**
	 * @return the status
	 */
	public AppConstants.LoadStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(AppConstants.LoadStatus status) {
		this.status = status;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
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
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((createdby == null) ? 0 : createdby.hashCode());
		result = prime * result + (lassistance ? 1231 : 1237);
		result = prime * result + ((lastupdatedby == null) ? 0 : lastupdatedby.hashCode());
		result = prime * result + ((ldatetime == null) ? 0 : ldatetime.hashCode());
		result = prime * result + (ldatetimeflexi ? 1231 : 1237);
		result = prime * result + ((ldroploc == null) ? 0 : ldroploc.hashCode());
		result = prime * result + ((lid == null) ? 0 : lid.hashCode());
		result = prime * result + (lpickupdropflexi ? 1231 : 1237);
		result = prime * result + ((lpickuploc == null) ? 0 : lpickuploc.hashCode());
		result = prime * result + ((ltype == null) ? 0 : ltype.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (!(obj instanceof Load))
			return false;
		Load other = (Load) obj;
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
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (createdby == null) {
			if (other.createdby != null)
				return false;
		} else if (!createdby.equals(other.createdby))
			return false;
		if (lassistance != other.lassistance)
			return false;
		if (lastupdatedby == null) {
			if (other.lastupdatedby != null)
				return false;
		} else if (!lastupdatedby.equals(other.lastupdatedby))
			return false;
		if (ldatetime == null) {
			if (other.ldatetime != null)
				return false;
		} else if (!ldatetime.equals(other.ldatetime))
			return false;
		if (ldatetimeflexi != other.ldatetimeflexi)
			return false;
		if (ldroploc == null) {
			if (other.ldroploc != null)
				return false;
		} else if (!ldroploc.equals(other.ldroploc))
			return false;
		if (lid == null) {
			if (other.lid != null)
				return false;
		} else if (!lid.equals(other.lid))
			return false;
		if (lpickupdropflexi != other.lpickupdropflexi)
			return false;
		if (lpickuploc == null) {
			if (other.lpickuploc != null)
				return false;
		} else if (!lpickuploc.equals(other.lpickuploc))
			return false;
		if (ltype == null) {
			if (other.ltype != null)
				return false;
		} else if (!ltype.equals(other.ltype))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public Load(@NotNull Client client) {
		super();
		this.client = client;
	}

	public Load() {
		super();
		// TODO Auto-generated constructor stub
	}
}

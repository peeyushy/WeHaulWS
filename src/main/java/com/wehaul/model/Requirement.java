package com.wehaul.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wehaul.constants.AppConstants;

@Entity
@Table(name = "T_REQUIREMENTS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Requirement implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reqid;

	@NotBlank
	private String reqtype;

	@NotBlank
	private String reqpickuploc;

	@NotBlank
	private String reqdroploc;

	private boolean reqpickupdropflexi = true;

	@NotNull
	private LocalDateTime reqdatetime;

	private boolean reqdatetimeflexi = true;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AppConstants.ReqStatus status;

	private String comments;

	@OneToOne
	@JoinColumn(name = "vtypeid")
	private VehicleType vtype;

	@OneToOne
	@JoinColumn(name = "ltypeid")
	private LoadType ltype;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "clientid", referencedColumnName = "clientid")
	@JsonBackReference
	private Client client;

	@NotNull
	private int retryAttempts = 0;

	@OneToOne(mappedBy = "req", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private RequirementDetails reqDetails;

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

	public Requirement() {
		super();
	}

	public Requirement(Client client) {
		super();
		this.client = client;
	}

	/**
	 * @return the reqid
	 */
	public Long getReqid() {
		return reqid;
	}

	/**
	 * @param reqid the reqid to set
	 */
	public void setReqid(Long reqid) {
		this.reqid = reqid;
	}

	/**
	 * @return the reqtype
	 */
	public String getReqtype() {
		return reqtype;
	}

	/**
	 * @param reqtype the reqtype to set
	 */
	public void setReqtype(String reqtype) {
		this.reqtype = reqtype;
	}

	/**
	 * @return the reqpickuploc
	 */
	public String getReqpickuploc() {
		return reqpickuploc;
	}

	/**
	 * @param reqpickuploc the reqpickuploc to set
	 */
	public void setReqpickuploc(String reqpickuploc) {
		this.reqpickuploc = reqpickuploc;
	}

	/**
	 * @return the reqdroploc
	 */
	public String getReqdroploc() {
		return reqdroploc;
	}

	/**
	 * @param reqdroploc the reqdroploc to set
	 */
	public void setReqdroploc(String reqdroploc) {
		this.reqdroploc = reqdroploc;
	}

	/**
	 * @return the reqpickupdropflexi
	 */
	public boolean isReqpickupdropflexi() {
		return reqpickupdropflexi;
	}

	/**
	 * @param reqpickupdropflexi the reqpickupdropflexi to set
	 */
	public void setReqpickupdropflexi(boolean reqpickupdropflexi) {
		this.reqpickupdropflexi = reqpickupdropflexi;
	}

	/**
	 * @return the reqdatetime
	 */
	public LocalDateTime getReqdatetime() {
		return reqdatetime;
	}

	/**
	 * @param reqdatetime the reqdatetime to set
	 */
	public void setReqdatetime(LocalDateTime reqdatetime) {
		this.reqdatetime = reqdatetime;
	}

	/**
	 * @return the reqdatetimeflexi
	 */
	public boolean isReqdatetimeflexi() {
		return reqdatetimeflexi;
	}

	/**
	 * @param reqdatetimeflexi the reqdatetimeflexi to set
	 */
	public void setReqdatetimeflexi(boolean reqdatetimeflexi) {
		this.reqdatetimeflexi = reqdatetimeflexi;
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
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the reqDetails
	 */
	public RequirementDetails getReqDetails() {
		return reqDetails;
	}

	/**
	 * @param reqDetails the reqDetails to set
	 */
	public void setReqDetails(RequirementDetails reqDetails) {
		this.reqDetails = reqDetails;
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

	/**
	 * @return the status
	 */
	public AppConstants.ReqStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(AppConstants.ReqStatus status) {
		this.status = status;
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
	public LoadType getLtype() {
		return ltype;
	}

	/**
	 * @param ltype the ltype to set
	 */
	public void setLtype(LoadType ltype) {
		this.ltype = ltype;
	}

	public int getRetryAttempts() {
		return retryAttempts;
	}

	public void setRetryAttempts(int retryAttempts) {
		this.retryAttempts = retryAttempts;
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
		result = prime * result + ((lastupdatedby == null) ? 0 : lastupdatedby.hashCode());
		result = prime * result + ((ltype == null) ? 0 : ltype.hashCode());
		result = prime * result + ((reqDetails == null) ? 0 : reqDetails.hashCode());
		result = prime * result + ((reqdatetime == null) ? 0 : reqdatetime.hashCode());
		result = prime * result + (reqdatetimeflexi ? 1231 : 1237);
		result = prime * result + ((reqdroploc == null) ? 0 : reqdroploc.hashCode());
		result = prime * result + ((reqid == null) ? 0 : reqid.hashCode());
		result = prime * result + (reqpickupdropflexi ? 1231 : 1237);
		result = prime * result + ((reqpickuploc == null) ? 0 : reqpickuploc.hashCode());
		result = prime * result + ((reqtype == null) ? 0 : reqtype.hashCode());
		result = prime * result + retryAttempts;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (getClass() != obj.getClass())
			return false;
		Requirement other = (Requirement) obj;
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
		if (reqDetails == null) {
			if (other.reqDetails != null)
				return false;
		} else if (!reqDetails.equals(other.reqDetails))
			return false;
		if (reqdatetime == null) {
			if (other.reqdatetime != null)
				return false;
		} else if (!reqdatetime.equals(other.reqdatetime))
			return false;
		if (reqdatetimeflexi != other.reqdatetimeflexi)
			return false;
		if (reqdroploc == null) {
			if (other.reqdroploc != null)
				return false;
		} else if (!reqdroploc.equals(other.reqdroploc))
			return false;
		if (reqid == null) {
			if (other.reqid != null)
				return false;
		} else if (!reqid.equals(other.reqid))
			return false;
		if (reqpickupdropflexi != other.reqpickupdropflexi)
			return false;
		if (reqpickuploc == null) {
			if (other.reqpickuploc != null)
				return false;
		} else if (!reqpickuploc.equals(other.reqpickuploc))
			return false;
		if (reqtype == null) {
			if (other.reqtype != null)
				return false;
		} else if (!reqtype.equals(other.reqtype))
			return false;
		if (retryAttempts != other.retryAttempts)
			return false;
		if (status != other.status)
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
		return "Requirement [reqid=" + reqid + ", reqtype=" + reqtype + ", reqpickuploc=" + reqpickuploc
				+ ", reqdroploc=" + reqdroploc + ", reqpickupdropflexi=" + reqpickupdropflexi + ", reqdatetime="
				+ reqdatetime + ", reqdatetimeflexi=" + reqdatetimeflexi + ", status=" + status + ", comments="
				+ comments + ", vtype=" + vtype + ", ltype=" + ltype + ", client=" + client + ", retryAttempts="
				+ retryAttempts + ", reqDetails=" + reqDetails + ", CREATEDAT=" + CREATEDAT + ", UPDATEDAT=" + UPDATEDAT
				+ ", createdby=" + createdby + ", lastupdatedby=" + lastupdatedby + "]";
	}

}

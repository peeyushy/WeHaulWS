package com.wehaul.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "T_REQUIREMENTS_DETAIL")
public class RequirementDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "reqid")
	private Long reqid;

	@OneToOne
	@MapsId
	@JsonBackReference
	private Requirement req;

	@NotBlank
	private String pickuplocid;
	@NotNull
	private double pickuplat;
	@NotNull
	private double pickuplong;
	@NotNull
	private String pickupaddresslable;

	private String pickuppostcode;

	private String pickupcity;

	private String pickupcounty;

	private String pickupstate;

	private String pickupcountry;
	@NotBlank
	private String droplocid;
	@NotNull
	private double droplat;
	@NotNull
	private double droplong;
	@NotNull
	private String dropaddresslable;

	private String droppostcode;

	private String dropcity;

	private String dropcounty;

	private String dropstate;

	private String dropcountry;

	public RequirementDetails() {
		super();
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
	 * @return the req
	 */
	public Requirement getReq() {
		return req;
	}

	/**
	 * @param req the req to set
	 */
	public void setReq(Requirement req) {
		this.req = req;
	}

	/**
	 * @return the pickuplocid
	 */
	public String getPickuplocid() {
		return pickuplocid;
	}

	/**
	 * @param pickuplocid the pickuplocid to set
	 */
	public void setPickuplocid(String pickuplocid) {
		this.pickuplocid = pickuplocid;
	}

	/**
	 * @return the pickuplat
	 */
	public double getPickuplat() {
		return pickuplat;
	}

	/**
	 * @param pickuplat the pickuplat to set
	 */
	public void setPickuplat(double pickuplat) {
		this.pickuplat = pickuplat;
	}

	/**
	 * @return the pickuplong
	 */
	public double getPickuplong() {
		return pickuplong;
	}

	/**
	 * @param pickuplong the pickuplong to set
	 */
	public void setPickuplong(double pickuplong) {
		this.pickuplong = pickuplong;
	}

	/**
	 * @return the pickupaddresslable
	 */
	public String getPickupaddresslable() {
		return pickupaddresslable;
	}

	/**
	 * @param pickupaddresslable the pickupaddresslable to set
	 */
	public void setPickupaddresslable(String pickupaddresslable) {
		this.pickupaddresslable = pickupaddresslable;
	}

	/**
	 * @return the pickuppostcode
	 */
	public String getPickuppostcode() {
		return pickuppostcode;
	}

	/**
	 * @param pickuppostcode the pickuppostcode to set
	 */
	public void setPickuppostcode(String pickuppostcode) {
		this.pickuppostcode = pickuppostcode;
	}

	/**
	 * @return the pickupcity
	 */
	public String getPickupcity() {
		return pickupcity;
	}

	/**
	 * @param pickupcity the pickupcity to set
	 */
	public void setPickupcity(String pickupcity) {
		this.pickupcity = pickupcity;
	}

	/**
	 * @return the pickupcounty
	 */
	public String getPickupcounty() {
		return pickupcounty;
	}

	/**
	 * @param pickupcounty the pickupcounty to set
	 */
	public void setPickupcounty(String pickupcounty) {
		this.pickupcounty = pickupcounty;
	}

	/**
	 * @return the pickupstate
	 */
	public String getPickupstate() {
		return pickupstate;
	}

	/**
	 * @param pickupstate the pickupstate to set
	 */
	public void setPickupstate(String pickupstate) {
		this.pickupstate = pickupstate;
	}

	/**
	 * @return the pickupcountry
	 */
	public String getPickupcountry() {
		return pickupcountry;
	}

	/**
	 * @param pickupcountry the pickupcountry to set
	 */
	public void setPickupcountry(String pickupcountry) {
		this.pickupcountry = pickupcountry;
	}

	/**
	 * @return the droplocid
	 */
	public String getDroplocid() {
		return droplocid;
	}

	/**
	 * @param droplocid the droplocid to set
	 */
	public void setDroplocid(String droplocid) {
		this.droplocid = droplocid;
	}

	/**
	 * @return the droplat
	 */
	public double getDroplat() {
		return droplat;
	}

	/**
	 * @param droplat the droplat to set
	 */
	public void setDroplat(double droplat) {
		this.droplat = droplat;
	}

	/**
	 * @return the droplong
	 */
	public double getDroplong() {
		return droplong;
	}

	/**
	 * @param droplong the droplong to set
	 */
	public void setDroplong(double droplong) {
		this.droplong = droplong;
	}

	/**
	 * @return the dropaddresslable
	 */
	public String getDropaddresslable() {
		return dropaddresslable;
	}

	/**
	 * @param dropaddresslable the dropaddresslable to set
	 */
	public void setDropaddresslable(String dropaddresslable) {
		this.dropaddresslable = dropaddresslable;
	}

	/**
	 * @return the droppostcode
	 */
	public String getDroppostcode() {
		return droppostcode;
	}

	/**
	 * @param droppostcode the droppostcode to set
	 */
	public void setDroppostcode(String droppostcode) {
		this.droppostcode = droppostcode;
	}

	/**
	 * @return the dropcity
	 */
	public String getDropcity() {
		return dropcity;
	}

	/**
	 * @param dropcity the dropcity to set
	 */
	public void setDropcity(String dropcity) {
		this.dropcity = dropcity;
	}

	/**
	 * @return the dropcounty
	 */
	public String getDropcounty() {
		return dropcounty;
	}

	/**
	 * @param dropcounty the dropcounty to set
	 */
	public void setDropcounty(String dropcounty) {
		this.dropcounty = dropcounty;
	}

	/**
	 * @return the dropstate
	 */
	public String getDropstate() {
		return dropstate;
	}

	/**
	 * @param dropstate the dropstate to set
	 */
	public void setDropstate(String dropstate) {
		this.dropstate = dropstate;
	}

	/**
	 * @return the dropcountry
	 */
	public String getDropcountry() {
		return dropcountry;
	}

	/**
	 * @param dropcountry the dropcountry to set
	 */
	public void setDropcountry(String dropcountry) {
		this.dropcountry = dropcountry;
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
		result = prime * result + ((dropaddresslable == null) ? 0 : dropaddresslable.hashCode());
		result = prime * result + ((dropcity == null) ? 0 : dropcity.hashCode());
		result = prime * result + ((dropcountry == null) ? 0 : dropcountry.hashCode());
		result = prime * result + ((dropcounty == null) ? 0 : dropcounty.hashCode());
		long temp;
		temp = Double.doubleToLongBits(droplat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((droplocid == null) ? 0 : droplocid.hashCode());
		temp = Double.doubleToLongBits(droplong);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((droppostcode == null) ? 0 : droppostcode.hashCode());
		result = prime * result + ((dropstate == null) ? 0 : dropstate.hashCode());
		result = prime * result + ((pickupaddresslable == null) ? 0 : pickupaddresslable.hashCode());
		result = prime * result + ((pickupcity == null) ? 0 : pickupcity.hashCode());
		result = prime * result + ((pickupcountry == null) ? 0 : pickupcountry.hashCode());
		result = prime * result + ((pickupcounty == null) ? 0 : pickupcounty.hashCode());
		temp = Double.doubleToLongBits(pickuplat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((pickuplocid == null) ? 0 : pickuplocid.hashCode());
		temp = Double.doubleToLongBits(pickuplong);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((pickuppostcode == null) ? 0 : pickuppostcode.hashCode());
		result = prime * result + ((pickupstate == null) ? 0 : pickupstate.hashCode());
		result = prime * result + ((req == null) ? 0 : req.hashCode());
		result = prime * result + ((reqid == null) ? 0 : reqid.hashCode());
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
		RequirementDetails other = (RequirementDetails) obj;
		if (dropaddresslable == null) {
			if (other.dropaddresslable != null)
				return false;
		} else if (!dropaddresslable.equals(other.dropaddresslable))
			return false;
		if (dropcity == null) {
			if (other.dropcity != null)
				return false;
		} else if (!dropcity.equals(other.dropcity))
			return false;
		if (dropcountry == null) {
			if (other.dropcountry != null)
				return false;
		} else if (!dropcountry.equals(other.dropcountry))
			return false;
		if (dropcounty == null) {
			if (other.dropcounty != null)
				return false;
		} else if (!dropcounty.equals(other.dropcounty))
			return false;
		if (Double.doubleToLongBits(droplat) != Double.doubleToLongBits(other.droplat))
			return false;
		if (droplocid == null) {
			if (other.droplocid != null)
				return false;
		} else if (!droplocid.equals(other.droplocid))
			return false;
		if (Double.doubleToLongBits(droplong) != Double.doubleToLongBits(other.droplong))
			return false;
		if (droppostcode == null) {
			if (other.droppostcode != null)
				return false;
		} else if (!droppostcode.equals(other.droppostcode))
			return false;
		if (dropstate == null) {
			if (other.dropstate != null)
				return false;
		} else if (!dropstate.equals(other.dropstate))
			return false;
		if (pickupaddresslable == null) {
			if (other.pickupaddresslable != null)
				return false;
		} else if (!pickupaddresslable.equals(other.pickupaddresslable))
			return false;
		if (pickupcity == null) {
			if (other.pickupcity != null)
				return false;
		} else if (!pickupcity.equals(other.pickupcity))
			return false;
		if (pickupcountry == null) {
			if (other.pickupcountry != null)
				return false;
		} else if (!pickupcountry.equals(other.pickupcountry))
			return false;
		if (pickupcounty == null) {
			if (other.pickupcounty != null)
				return false;
		} else if (!pickupcounty.equals(other.pickupcounty))
			return false;
		if (Double.doubleToLongBits(pickuplat) != Double.doubleToLongBits(other.pickuplat))
			return false;
		if (pickuplocid == null) {
			if (other.pickuplocid != null)
				return false;
		} else if (!pickuplocid.equals(other.pickuplocid))
			return false;
		if (Double.doubleToLongBits(pickuplong) != Double.doubleToLongBits(other.pickuplong))
			return false;
		if (pickuppostcode == null) {
			if (other.pickuppostcode != null)
				return false;
		} else if (!pickuppostcode.equals(other.pickuppostcode))
			return false;
		if (pickupstate == null) {
			if (other.pickupstate != null)
				return false;
		} else if (!pickupstate.equals(other.pickupstate))
			return false;
		if (req == null) {
			if (other.req != null)
				return false;
		} else if (!req.equals(other.req))
			return false;
		if (reqid == null) {
			if (other.reqid != null)
				return false;
		} else if (!reqid.equals(other.reqid))
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
		return "RequirementDetails [reqid=" + reqid + ", req=" + req + ", pickuplocid=" + pickuplocid + ", pickuplat="
				+ pickuplat + ", pickuplong=" + pickuplong + ", pickupaddresslable=" + pickupaddresslable
				+ ", pickuppostcode=" + pickuppostcode + ", pickupcity=" + pickupcity + ", pickupcounty=" + pickupcounty
				+ ", pickupstate=" + pickupstate + ", pickupcountry=" + pickupcountry + ", droplocid=" + droplocid
				+ ", droplat=" + droplat + ", droplong=" + droplong + ", dropaddresslable=" + dropaddresslable
				+ ", droppostcode=" + droppostcode + ", dropcity=" + dropcity + ", dropcounty=" + dropcounty
				+ ", dropstate=" + dropstate + ", dropcountry=" + dropcountry + "]";
	}
}

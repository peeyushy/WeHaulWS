package com.erstaticdata.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import constants.AppConstants;

@Entity
@Table(name = "T_CLIENTS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientid;

	@NotBlank
	private String clientname;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AppConstants.ClientType clienttype;

	@NotBlank
	private String contactno;

	private String country;

	private String city;

	private String addressline1;

	private String addressline2;

	private String postcode;

	private String website;

	// blob column
	private String clientlogo;

	// blob
	private String comments;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AppConstants.Status status;

	private int revid;

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

	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<User> users = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vehicle> vehicles = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Load> loads = new ArrayList<>();

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		users.add(user);
		user.setClient(this);
	}

	public void removeUser(User user) {
		users.remove(user);
		user.setClient(null);
	}
	

	/**
	 * @return the vehicles
	 */
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * @param vehicles the vehicles to set
	 */
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
		vehicle.setClient(this);
	}

	public void removeVehicle(Vehicle vehicle) {
		vehicles.remove(vehicle);
		vehicle.setClient(null);
	}	

	/**
	 * @return the loads
	 */
	public List<Load> getLoads() {
		return loads;
	}

	/**
	 * @param loads the loads to set
	 */
	public void setLoads(List<Load> loads) {
		this.loads = loads;
	}
	
	public void addLoad(Load load) {
		loads.add(load);
		load.setClient(this);
	}

	public void removeLoad(Load load) {
		loads.remove(load);
		load.setClient(null);
	}

	public Long getClientid() {
		return clientid;
	}

	public void setClientid(Long clientid) {
		this.clientid = clientid;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public AppConstants.ClientType getClienttype() {
		return clienttype;
	}

	public void setClienttype(AppConstants.ClientType clienttype) {
		this.clienttype = clienttype;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getClientlogo() {
		return clientlogo;
	}

	public void setClientlogo(String clientlogo) {
		this.clientlogo = clientlogo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public AppConstants.Status getStatus() {
		return status;
	}

	public void setStatus(AppConstants.Status status) {
		this.status = status;
	}

	public int getRevid() {
		return revid;
	}

	public void setRevid(int revid) {
		this.revid = revid;
	}

	public Date getCREATEDAT() {
		return CREATEDAT;
	}

	public void setCREATEDAT(Date cREATEDAT) {
		CREATEDAT = cREATEDAT;
	}

	public Date getUPDATEDAT() {
		return UPDATEDAT;
	}

	public void setUPDATEDAT(Date uPDATEDAT) {
		UPDATEDAT = uPDATEDAT;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getLastupdatedby() {
		return lastupdatedby;
	}

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
		result = prime * result + ((addressline1 == null) ? 0 : addressline1.hashCode());
		result = prime * result + ((addressline2 == null) ? 0 : addressline2.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((clientid == null) ? 0 : clientid.hashCode());
		result = prime * result + ((clientlogo == null) ? 0 : clientlogo.hashCode());
		result = prime * result + ((clientname == null) ? 0 : clientname.hashCode());
		result = prime * result + ((clienttype == null) ? 0 : clienttype.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((contactno == null) ? 0 : contactno.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((createdby == null) ? 0 : createdby.hashCode());
		result = prime * result + ((lastupdatedby == null) ? 0 : lastupdatedby.hashCode());
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + revid;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
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
		if (addressline1 == null) {
			if (other.addressline1 != null)
				return false;
		} else if (!addressline1.equals(other.addressline1))
			return false;
		if (addressline2 == null) {
			if (other.addressline2 != null)
				return false;
		} else if (!addressline2.equals(other.addressline2))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (clientid == null) {
			if (other.clientid != null)
				return false;
		} else if (!clientid.equals(other.clientid))
			return false;
		if (clientlogo == null) {
			if (other.clientlogo != null)
				return false;
		} else if (!clientlogo.equals(other.clientlogo))
			return false;
		if (clientname == null) {
			if (other.clientname != null)
				return false;
		} else if (!clientname.equals(other.clientname))
			return false;
		if (clienttype == null) {
			if (other.clienttype != null)
				return false;
		} else if (!clienttype.equals(other.clienttype))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (contactno == null) {
			if (other.contactno != null)
				return false;
		} else if (!contactno.equals(other.contactno))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
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
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (revid != other.revid)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}
}

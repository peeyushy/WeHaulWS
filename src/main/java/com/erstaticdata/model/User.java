package com.erstaticdata.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

@Entity
@Table(name = "T_USERS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;

	@NotBlank
	private String name;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String email;

	private String contactno;

	private String notificationtype;

	private String avatar;

	@NotBlank
	private String status;

	@NotNull
	@OneToOne
	@JoinTable(name = "T_USERS_ROLES", joinColumns = @JoinColumn(name = "userid", referencedColumnName = "userid"), inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "roleid"))
	private Role role;
	
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

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getNotificationtype() {
		return notificationtype;
	}

	public void setNotificationtype(String notificationtype) {
		this.notificationtype = notificationtype;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CREATEDAT == null) ? 0 : CREATEDAT.hashCode());
		result = prime * result + ((UPDATEDAT == null) ? 0 : UPDATEDAT.hashCode());
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((contactno == null) ? 0 : contactno.hashCode());
		result = prime * result + ((createdby == null) ? 0 : createdby.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((lastupdatedby == null) ? 0 : lastupdatedby.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notificationtype == null) ? 0 : notificationtype.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
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
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (contactno == null) {
			if (other.contactno != null)
				return false;
		} else if (!contactno.equals(other.contactno))
			return false;
		if (createdby == null) {
			if (other.createdby != null)
				return false;
		} else if (!createdby.equals(other.createdby))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (lastupdatedby == null) {
			if (other.lastupdatedby != null)
				return false;
		} else if (!lastupdatedby.equals(other.lastupdatedby))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notificationtype == null) {
			if (other.notificationtype != null)
				return false;
		} else if (!notificationtype.equals(other.notificationtype))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}		
}

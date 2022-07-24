package com.troika.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the UserLog database table.
 * 
 */
@Entity
@Table(name = "USERLOG")
@NamedQuery(name = "UserLog.findAll", query = "SELECT c FROM UserLog c")
@XmlRootElement
public class UserLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String sessionId;

	private String userEmailId;

	private RoleEnum role;

	private Date loggedInTime;

	public UserLog() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the userEmailId
	 */
	public String getUserEmailId() {
		return userEmailId;
	}

	/**
	 * @param userEmailId
	 *            the userEmailId to set
	 */
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	/**
	 * @return the loggedInTime
	 */
	public Date getLoggedInTime() {
		return loggedInTime;
	}

	/**
	 * @param loggedInTime
	 *            the loggedInTime to set
	 */
	public void setLoggedInTime(Date loggedInTime) {
		this.loggedInTime = loggedInTime;
	}

	/**
	 * @return the role
	 */
	public RoleEnum getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(RoleEnum role) {
		this.role = role;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserLog [sessionId=" + sessionId + ", userEmailId=" + userEmailId + ", loggedInTime=" + loggedInTime
				+ "]";
	}

}
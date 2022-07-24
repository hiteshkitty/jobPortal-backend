package com.troika.domain.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.troika.domain.model.RoleEnum;

@XmlRootElement
public class UserInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	private String credentials;

	private String emailId;

	private RoleEnum role;

	private Boolean isRecruiter;

	public UserInformation() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the credentials
	 */
	public String getCredentials() {
		return credentials;
	}

	/**
	 * @param credentials
	 *            the credentials to set
	 */
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	/**
	 * @return the isRecruiter
	 */
	public Boolean getIsRecruiter() {
		return isRecruiter;
	}

	/**
	 * @param isRecruiter
	 *            the isRecruiter to set
	 */
	public void setIsRecruiter(Boolean isRecruiter) {
		this.isRecruiter = isRecruiter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserInformation [credentials=" + credentials + ", emailId=" + emailId + ", role=" + role
				+ ", isRecruiter=" + isRecruiter + "]";
	}

}

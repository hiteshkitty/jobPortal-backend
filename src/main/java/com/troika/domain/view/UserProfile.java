package com.troika.domain.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.troika.domain.model.RoleEnum;

/**
 *
 * 
 */
@XmlRootElement
public class UserProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long contactNumber;

	private String dateOfBirth;

	private String email;

	private String firstName;

	private String lastName;

	private String registrationDate;

	private String password;

	private Seeker seekerProfileRequest;

	private Integer userType;

	private RoleEnum role;

	private Boolean isResumeAvailable;

	public UserProfile() {
	}

	/**
	 * @return the contactNumber
	 */
	public Long getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 *            the contactNumber to set
	 */
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the registrationDate
	 */
	public String getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate
	 *            the registrationDate to set
	 */
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the seekerProfileRequest
	 */
	public Seeker getSeekerProfileRequest() {
		return seekerProfileRequest;
	}

	/**
	 * @param seekerProfileRequest
	 *            the seekerProfileRequest to set
	 */
	public void setSeekerProfileRequest(Seeker seekerProfileRequest) {
		this.seekerProfileRequest = seekerProfileRequest;
	}

	/**
	 * @return the userType
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the isResumeAvailable
	 */
	public Boolean getIsResumeAvailable() {
		return isResumeAvailable;
	}

	/**
	 * @param isResumeAvailable
	 *            the isResumeAvailable to set
	 */
	public void setIsResumeAvailable(Boolean isResumeAvailable) {
		this.isResumeAvailable = isResumeAvailable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserProfile [contactNumber=" + contactNumber + ", dateOfBirth=" + dateOfBirth + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", registrationDate=" + registrationDate
				+ ", password=" + password + ", seekerProfileRequest=" + seekerProfileRequest + ", userType=" + userType
				+ ", role=" + role + ", isResumeAvailable=" + isResumeAvailable + "]";
	}

}
package com.troika.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the user_account database table.
 * 
 */
@Entity
@Table(name = "user_account") // , uniqueConstraints =
								// @UniqueConstraint(columnNames = { "email",
								// "contactNumber" }))
@NamedQuery(name = "UserAccount.findAll", query = "SELECT u FROM UserAccount u")
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String emailId;

	private Long contactNumber;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	private String firstName;

	private String lastName;

	private String password;

	private Boolean isAuthorizedToPostJob;

	private Boolean isActive = Boolean.TRUE;

	@Temporal(TemporalType.DATE)
	@Column(name = "registration_date")
	private Date registrationDate;

	private RoleEnum role;

	// bi-directional many-to-one association to JobPost
	@OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
	private List<Job> jobPosts;

	// bi-directional many-to-one association to JobPostActivity
	@OneToMany(mappedBy = "userAccount")
	private List<JobPostActivity> jobPostActivities;

	// bi-directional one-to-one association to SeekerProfile
	@OneToOne(mappedBy = "userAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SeekerProfile seekerProfile;

	// bi-directional many-to-one association to UserType
	@ManyToOne
	@JoinColumn(name = "user_type_id")
	private UserType userType;

	public UserAccount() {
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
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	 * @return the jobPostActivities
	 */
	public List<JobPostActivity> getJobPostActivities() {
		return jobPostActivities;
	}

	/**
	 * @param jobPostActivities
	 *            the jobPostActivities to set
	 */
	public void setJobPostActivities(List<JobPostActivity> jobPostActivities) {
		this.jobPostActivities = jobPostActivities;
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
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate
	 *            the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
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
	 * @return the jobPosts
	 */
	public List<Job> getJobPosts() {
		return jobPosts;
	}

	/**
	 * @param jobPosts
	 *            the jobPosts to set
	 */
	public void setJobPosts(List<Job> jobPosts) {
		this.jobPosts = jobPosts;
	}

	/**
	 * @return the seekerProfile
	 */
	public SeekerProfile getSeekerProfile() {
		return seekerProfile;
	}

	/**
	 * @param seekerProfile
	 *            the seekerProfile to set
	 */
	public void setSeekerProfile(SeekerProfile seekerProfile) {
		this.seekerProfile = seekerProfile;
	}

	/**
	 * @return the isAuthorizedToPostJob
	 */
	public Boolean getIsAuthorizedToPostJob() {
		return isAuthorizedToPostJob;
	}

	/**
	 * @param isAuthorizedToPostJob
	 *            the isAuthorizedToPostJob to set
	 */
	public void setIsAuthorizedToPostJob(Boolean isAuthorizedToPostJob) {
		this.isAuthorizedToPostJob = isAuthorizedToPostJob;
	}

	/**
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
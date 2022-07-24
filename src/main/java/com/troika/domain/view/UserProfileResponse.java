package com.troika.domain.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.troika.domain.model.SkillSet;

@XmlRootElement
public class UserProfileResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;

	private String lastName;

	private String emailId;

	private String primaryMobileNumber;

	private Integer expInMonths;

	private String industryType;

	private Boolean isResumeAvailable;

	private String currentLocation;

	private String highestQualification;

	private Boolean isActive;

	private String profileSummary;

	private List<Integer> skillSets = new ArrayList<Integer>();

	public UserProfileResponse() {

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
	 * @return the primaryMobileNumber
	 */
	public String getPrimaryMobileNumber() {
		return primaryMobileNumber;
	}

	/**
	 * @param primaryMobileNumber
	 *            the primaryMobileNumber to set
	 */
	public void setPrimaryMobileNumber(String primaryMobileNumber) {
		this.primaryMobileNumber = primaryMobileNumber;
	}

	/**
	 * @return the expInMonths
	 */
	public Integer getExpInMonths() {
		return expInMonths;
	}

	/**
	 * @param expInMonths
	 *            the expInMonths to set
	 */
	public void setExpInMonths(Integer expInMonths) {
		this.expInMonths = expInMonths;
	}

	/**
	 * @return the industryType
	 */
	public String getIndustryType() {
		return industryType;
	}

	/**
	 * @param industryType
	 *            the industryType to set
	 */
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	/**
	 * @return the currentLocation
	 */
	public String getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param currentLocation
	 *            the currentLocation to set
	 */
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	/**
	 * @return the highestQualification
	 */
	public String getHighestQualification() {
		return highestQualification;
	}

	/**
	 * @param highestQualification
	 *            the highestQualification to set
	 */
	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
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
	 * @return the profileSummary
	 */
	public String getProfileSummary() {
		return profileSummary;
	}

	/**
	 * @param profileSummary
	 *            the profileSummary to set
	 */
	public void setProfileSummary(String profileSummary) {
		this.profileSummary = profileSummary;
	}

	/**
	 * @return the skillSets
	 */
	public List<Integer> getSkillSets() {
		return skillSets;
	}

	/**
	 * @param skillSets
	 *            the skillSets to set
	 */
	public void setSkillSets(List<Integer> skillSets) {
		this.skillSets = skillSets;
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
		return "UserProfileResponse [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", primaryMobileNumber=" + primaryMobileNumber + ", expInMonths=" + expInMonths + ", industryType="
				+ industryType + ", isResumeAvailable=" + isResumeAvailable + ", currentLocation=" + currentLocation
				+ ", highestQualification=" + highestQualification + ", isActive=" + isActive + ", profileSummary="
				+ profileSummary + ", skillSets=" + skillSets + "]";
	}

}
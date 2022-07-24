package com.troika.domain.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * 
 */
@XmlRootElement
public class Seeker implements Serializable {

	private static final long serialVersionUID = 1L;

	private String seekerId;

	private String firsName;

	private String lastName;

	private Integer expInMonths;

	private Long contactNumber;

	private String resume;

	private String currentLocation;

	private String preferredLocation;

	private String highestQualification;

	private Boolean isActive;

	private Boolean isResumeAvailable;

	private String profileSummary;

	private List<Integer> seekerSkillSets = new ArrayList<>();

	/**
	 * @return the seekerId
	 */
	public String getSeekerId() {
		return seekerId;
	}

	/**
	 * @param seekerId
	 *            the seekerId to set
	 */
	public void setSeekerId(String seekerId) {
		this.seekerId = seekerId;
	}

	/**
	 * @return the firsName
	 */
	public String getFirsName() {
		return firsName;
	}

	/**
	 * @param firsName
	 *            the firsName to set
	 */
	public void setFirsName(String firsName) {
		this.firsName = firsName;
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
	 * @return the resume
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * @param resume
	 *            the resume to set
	 */
	public void setResume(String resume) {
		this.resume = resume;
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
	 * @return the preferredLocation
	 */
	public String getPreferredLocation() {
		return preferredLocation;
	}

	/**
	 * @param preferredLocation
	 *            the preferredLocation to set
	 */
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
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
	 * @return the seekerSkillSets
	 */
	public List<Integer> getSeekerSkillSets() {
		return seekerSkillSets;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Seeker [seekerId=" + seekerId + ", firsName=" + firsName + ", lastName=" + lastName + ", expInMonths="
				+ expInMonths + ", resume=" + resume + ", currentLocation=" + currentLocation + ", preferredLocation="
				+ preferredLocation + ", highestQualification=" + highestQualification + ", isActive=" + isActive
				+ ", isResumeAvailable=" + isResumeAvailable + ", profileSummary=" + profileSummary + ", contactNumber="
				+ contactNumber + ", seekerSkillSets=" + seekerSkillSets + "]";
	}

}
package com.troika.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * The persistent class for the seeker_profile database table.
 * 
 */
@Entity
@Table(name = "SEEKER_PROFILE")
@NamedQuery(name = "SeekerProfile.findAll", query = "SELECT s FROM SeekerProfile s")
@XmlRootElement
public class SeekerProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "userAccount"))
	private String seekerProfileId;

	private Integer expInMonths;

	@Lob
	private byte[] resume;

	private String currentLocation;

	private String highestQualification;

	private Boolean isActive;

	private String profileSummary;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private UserAccount userAccount;

	@OneToMany(mappedBy = "seekerProfile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SeekerSkillSet> seekerSkillSets;

	public SeekerProfile() {

	}

	/**
	 * @return the seekerProfileId
	 */
	public String getSeekerProfileId() {
		return seekerProfileId;
	}

	/**
	 * @param seekerProfileId
	 *            the seekerProfileId to set
	 */
	public void setSeekerProfileId(String seekerProfileId) {
		this.seekerProfileId = seekerProfileId;
	}

	/**
	 * @return the userAccount
	 */
	public UserAccount getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount
	 *            the userAccount to set
	 */
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
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
	public byte[] getResume() {
		return resume;
	}

	/**
	 * @param resume
	 *            the resume to set
	 */
	public void setResume(byte[] resume) {
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
	public List<SeekerSkillSet> getSeekerSkillSets() {
		return seekerSkillSets;
	}

	/**
	 * @param seekerSkillSets
	 *            the seekerSkillSets to set
	 */
	public void setSeekerSkillSets(List<SeekerSkillSet> seekerSkillSets) {
		this.seekerSkillSets = seekerSkillSets;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SeekerProfile [ expInMonths=" + expInMonths + ", resume=" + resume + ", currentLocation="
				+ currentLocation + ", highestQualification=" + highestQualification + ", isActive=" + isActive
				+ ", profileSummary=" + profileSummary + "]";
	}

}
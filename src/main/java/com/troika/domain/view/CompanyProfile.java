package com.troika.domain.view;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompanyProfile implements Serializable {

	private static final long serialVersionUID = 6421510789456350512L;

	private Integer companyId;

	private String companyName;

	private String emailId;

	private String streetAddress;

	private String city;

	private String primaryMobileNumber;

	private String companyWebsite;

	private Date establishmentDate;

	private Integer companyType;

	private Boolean isActive;

	private String companyProfile;

	/**
	 * @return the companyId
	 */
	public Integer getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the recruterId to set
	 */
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
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
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * @param streetAddress
	 *            the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the companyWebsite
	 */
	public String getCompanyWebsite() {
		return companyWebsite;
	}

	/**
	 * @param companyWebsite
	 *            the companyWebsite to set
	 */
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	/**
	 * @return the establishmentDate
	 */
	public Date getEstablishmentDate() {
		return establishmentDate;
	}

	/**
	 * @param establishmentDate
	 *            the establishmentDate to set
	 */
	public void setEstablishmentDate(Date establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	/**
	 * @return the companyType
	 */
	public Integer getCompanyType() {
		return companyType;
	}

	/**
	 * @param companyType
	 *            the companyType to set
	 */
	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	/**
	 * @return the companyProfile
	 */
	public String getCompanyProfile() {
		return companyProfile;
	}

	/**
	 * @param companyProfile
	 *            the companyProfile to set
	 */
	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompanyProfile [companyId=" + companyId + ", companyName=" + companyName + ", emailId=" + emailId
				+ ", streetAddress=" + streetAddress + ", city=" + city + ", primaryMobileNumber=" + primaryMobileNumber
				+ ", companyWebsite=" + companyWebsite + ", establishmentDate=" + establishmentDate + ", companyType="
				+ companyType + ", isActive=" + isActive + ", companyProfile=" + companyProfile + "]";
	}

}

package com.troika.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@Table(name = "COMPANY", uniqueConstraints = @UniqueConstraint(columnNames = { "companyName", "companyWebsiteUrl" }))
@NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c")
@XmlRootElement
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer companyId;

	private String companyName;

	private String companyWebsiteUrl;

	private String emailId;

	private String companyAddress;

	private String city;

	private String primaryContactNumber;

	private Boolean isActive;

	@Temporal(TemporalType.DATE)
	private Date establishmentDate;

	private String profileDescription;

	@ManyToOne(optional = false)
	@JoinColumn(name = "BUSINESS_STREAM_ID")
	private BusinessStream businessStream;

	// bi-directional many-to-one association to Job
	@OneToMany(mappedBy = "company")
	@JsonIgnoreProperties("company")
	private List<Job> jobs;

	public Company() {
	}

	/**
	 * @return the companyId
	 */
	public Integer getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
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
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyWebsiteUrl
	 */
	public String getCompanyWebsiteUrl() {
		return companyWebsiteUrl;
	}

	/**
	 * @param companyWebsiteUrl
	 *            the companyWebsiteUrl to set
	 */
	public void setCompanyWebsiteUrl(String companyWebsiteUrl) {
		this.companyWebsiteUrl = companyWebsiteUrl;
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
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * @param companyAddress
	 *            the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
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
	 * @return the primaryContactNumber
	 */
	public String getPrimaryContactNumber() {
		return primaryContactNumber;
	}

	/**
	 * @param primaryContactNumber
	 *            the primaryContactNumber to set
	 */
	public void setPrimaryContactNumber(String primaryContactNumber) {
		this.primaryContactNumber = primaryContactNumber;
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
	 * @return the profileDescription
	 */
	public String getProfileDescription() {
		return profileDescription;
	}

	/**
	 * @param profileDescription
	 *            the profileDescription to set
	 */
	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}

	/**
	 * @return the businessStream
	 */
	public BusinessStream getBusinessStream() {
		return businessStream;
	}

	/**
	 * @param businessStream
	 *            the businessStream to set
	 */
	public void setBusinessStream(BusinessStream businessStream) {
		this.businessStream = businessStream;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", companyWebsiteUrl="
				+ companyWebsiteUrl + ", emailId=" + emailId + ", companyAddress=" + companyAddress + ", city=" + city
				+ ", primaryContactNumber=" + primaryContactNumber + ", establishmentDate=" + establishmentDate
				+ ", profileDescription=" + profileDescription + " isActive=" + isActive + "]";
	}

	/**
	 * @return the jobs
	 */
	public List<Job> getJobs() {
		return jobs;
	}

	/**
	 * @param jobs
	 *            the jobs to set
	 */
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

}
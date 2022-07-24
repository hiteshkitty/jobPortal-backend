package com.troika.domain.view;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JobResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer jobId;

	private Date createdDate;

	private String jobTitle;

	private String jobDescription;

	private Integer salary;

	private String city;

	private String jobIndustry;

	private String qualification;

	private Integer experience;

	private String companyName;

	private String isActive;

	private Boolean isCompanyNameHidden;

	private String company;

	private Map<Integer, String> jobType = new HashMap<>();

	private Map<Integer, String> jobSkillSets = new HashMap<>();

	/**
	 * @return the jobId
	 */
	public Integer getJobId() {
		return jobId;
	}

	/**
	 * @param jobId
	 *            the jobId to set
	 */
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle
	 *            the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the jobDescription
	 */
	public String getJobDescription() {
		return jobDescription;
	}

	/**
	 * @param jobDescription
	 *            the jobDescription to set
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	/**
	 * @return the salary
	 */
	public Integer getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(Integer salary) {
		this.salary = salary;
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
	 * @return the jobIndustry
	 */
	public String getJobIndustry() {
		return jobIndustry;
	}

	/**
	 * @param jobIndustry
	 *            the jobIndustry to set
	 */
	public void setJobIndustry(String jobIndustry) {
		this.jobIndustry = jobIndustry;
	}

	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * @param qualification
	 *            the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * @return the experience
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * @param experience
	 *            the experience to set
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
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
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the isCompanyNameHidden
	 */
	public Boolean getIsCompanyNameHidden() {
		return isCompanyNameHidden;
	}

	/**
	 * @param boolean1
	 *            the isCompanyNameHidden to set
	 */
	public void setIsCompanyNameHidden(Boolean isCompanyNameHidden) {
		this.isCompanyNameHidden = isCompanyNameHidden;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the jobSkillSets
	 */
	public Map<Integer, String> getJobType() {
		return jobType;
	}

	/**
	 * @return the jobSkillSets
	 */
	public Map<Integer, String> getJobSkillSets() {
		return jobSkillSets;
	}

}

package com.troika.domain.view;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class JobRequest implements Serializable {

	private static final long serialVersionUID = -6387841337372356933L;

	// private Integer userAccountId;

	private String emailId;

	private Integer jobId;

	private Integer memberId;

	private String jobTitle;

	private String jobDescription;

	private List<Integer> keySkills;

	private Integer salary;

	private String city;

	private String jobIndustry;

	private String functionalArea;

	private String numberOfVacancy;

	private String education;

	private Integer experience;

	private String companyName;

	private String isActive;

	private String aboutCompany;

	private String jobType;

	private Boolean isCompanyNameHidden;

	private MemberTypeEnum memberType;

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
	 * @return the keySkills
	 */
	public List<Integer> getKeySkills() {
		return keySkills;
	}

	/**
	 * @param keySkills
	 *            the keySkills to set
	 */
	public void setKeySkills(List<Integer> keySkills) {
		this.keySkills = keySkills;
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
	 * @return the functionalArea
	 */
	public String getFunctionalArea() {
		return functionalArea;
	}

	/**
	 * @param functionalArea
	 *            the functionalArea to set
	 */
	public void setFunctionalArea(String functionalArea) {
		this.functionalArea = functionalArea;
	}

	/**
	 * @return the numberOfVacancy
	 */
	public String getNumberOfVacancy() {
		return numberOfVacancy;
	}

	/**
	 * @param numberOfVacancy
	 *            the numberOfVacancy to set
	 */
	public void setNumberOfVacancy(String numberOfVacancy) {
		this.numberOfVacancy = numberOfVacancy;
	}

	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education
	 *            the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
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
	 * @return the aboutCompany
	 */
	public String getAboutCompany() {
		return aboutCompany;
	}

	/**
	 * @param aboutCompany
	 *            the aboutCompany to set
	 */
	public void setAboutCompany(String aboutCompany) {
		this.aboutCompany = aboutCompany;
	}

	/**
	 * @return the jobType
	 */
	public String getJobType() {
		return jobType;
	}

	/**
	 * @param jobType
	 *            the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	/**
	 * @return the isCompanyNameHidden
	 */
	public Boolean getIsCompanyNameHidden() {
		return isCompanyNameHidden;
	}

	/**
	 * @param isCompanyNameHidden
	 *            the isCompanyNameHidden to set
	 */
	public void setIsCompanyNameHidden(Boolean isCompanyNameHidden) {
		this.isCompanyNameHidden = isCompanyNameHidden;
	}

	/**
	 * @return the memberId
	 */
	public Integer getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId
	 *            the memberId to set
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the memberType
	 */
	public MemberTypeEnum getMemberType() {
		return memberType;
	}

	/**
	 * @param memberType
	 *            the memberType to set
	 */
	public void setMemberType(MemberTypeEnum memberType) {
		this.memberType = memberType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobRequest [emailId=" + emailId + " jobId=" + jobId + ", memberId=" + memberId + ", jobTitle="
				+ jobTitle + ", jobDescription=" + jobDescription + ", keySkills=" + keySkills + ", salary=" + salary
				+ ", city=" + city + ", jobIndustry=" + jobIndustry + ", functionalArea=" + functionalArea
				+ ", numberOfVacancy=" + numberOfVacancy + ", education=" + education + ", experience=" + experience
				+ ", companyName=" + companyName + ", isActive=" + isActive + ", aboutCompany=" + aboutCompany
				+ ", jobType=" + jobType + ", isCompanyNameHidden=" + isCompanyNameHidden + ", memberType=" + memberType
				+ "]";
	}

}

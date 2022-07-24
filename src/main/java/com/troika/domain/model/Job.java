package com.troika.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

/**
 * The persistent class for the job_post database table.
 * 
 */
@Entity
@Table(name = "job_post")
@NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jobId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	private String jobTitle;

	private String jobDescription;

	private String city;

	private String jobIndustry;

	private String qualification;

	private Integer experience;

	private String companyName;

	private String jobPostedByType;

	private Boolean isJobActive;

	@ManyToOne
	@JoinColumn(name = "COMPANY_ID")
	private Company company;

	@ManyToOne(optional = false)
	@JoinColumn(name = "JOB_TYPE_ID")
	private JobType jobType;

	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<JobSkillSet> jobSkillSets;

	@ManyToOne
	@JoinColumn(name = "posted_by_id")
	private UserAccount userAccount;

	// bi-directional many-to-one association to JobLocation
	@ManyToOne
	@JoinColumn(name = "job_location_id", insertable = false, updatable = false)
	private JobLocation jobLocation;

	// bi-directional many-to-one association to JobSkillSet

	public Job() {
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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
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
	 * @return the isJobActive
	 */
	public Boolean getIsJobActive() {
		return isJobActive;
	}

	/**
	 * @param isJobActive
	 *            the isJobActive to set
	 */
	public void setIsJobActive(Boolean isJobActive) {
		this.isJobActive = isJobActive;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * @return the jobType
	 */
	public JobType getJobType() {
		return jobType;
	}

	/**
	 * @param jobType
	 *            the jobType to set
	 */
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	/**
	 * @return the jobSkillSets
	 */
	public List<JobSkillSet> getJobSkillSets() {
		return jobSkillSets;
	}

	/**
	 * @param jobSkillSets
	 *            the jobSkillSets to set
	 */
	public void setJobSkillSets(List<JobSkillSet> jobSkillSets) {
		this.jobSkillSets = jobSkillSets;
	}

	/**
	 * @return the jobPostedByType
	 */
	public String getJobPostedByType() {
		return jobPostedByType;
	}

	/**
	 * @param jobPostedByType
	 *            the jobPostedByType to set
	 */
	public void setJobPostedByType(String jobPostedByType) {
		this.jobPostedByType = jobPostedByType;
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
	 * @return the jobLocation
	 */
	public JobLocation getJobLocation() {
		return jobLocation;
	}

	/**
	 * @param jobLocation
	 *            the jobLocation to set
	 */
	public void setJobLocation(JobLocation jobLocation) {
		this.jobLocation = jobLocation;
	}

}
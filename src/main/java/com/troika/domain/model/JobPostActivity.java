package com.troika.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the job_post_activity database table.
 * 
 */
@Entity
@Table(name = "job_post_activity", uniqueConstraints = @UniqueConstraint(columnNames = { "user_account_id",
		"job_post_id" }))
@NamedQuery(name = "JobPostActivity.findAll", query = "SELECT j FROM JobPostActivity j")
public class JobPostActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer jobActivityId;

	@Temporal(TemporalType.DATE)
	@Column(name = "apply_date")
	private Date applyDate;

	// bi-directional many-to-one association to UserAccount
	@ManyToOne
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	// bi-directional many-to-one association to JobPost
	@ManyToOne
	@JoinColumn(name = "job_post_id")
	private Job job;

	public JobPostActivity() {
	}

	/**
	 * @return the jobActivityId
	 */
	public Integer getJobActivityId() {
		return jobActivityId;
	}

	/**
	 * @param jobActivityId
	 *            the jobActivityId to set
	 */
	public void setJobActivityId(Integer jobActivityId) {
		this.jobActivityId = jobActivityId;
	}

	/**
	 * @return the applyDate
	 */
	public Date getApplyDate() {
		return applyDate;
	}

	/**
	 * @param applyDate
	 *            the applyDate to set
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
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
	 * @return the job
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * @param job
	 *            the job to set
	 */
	public void setJob(Job job) {
		this.job = job;
	}

}
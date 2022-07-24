package com.troika.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the job_type database table.
 * 
 */
@Entity
@Table(name = "job_type", uniqueConstraints = @UniqueConstraint(columnNames = { "jobType"}))
@NamedQuery(name = "JobType.findAll", query = "SELECT j FROM JobType j")
public class JobType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String jobType;

	// bi-directional many-to-one association to Job
	@OneToMany(mappedBy = "jobType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Job> jobs;

	public JobType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobType() {
		return this.jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public List<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job addJob(Job job) {
		getJobs().add(job);
		job.setJobType(this);

		return job;
	}

	public Job removeJob(Job job) {
		getJobs().remove(job);
		job.setJobType(null);

		return job;
	}

}
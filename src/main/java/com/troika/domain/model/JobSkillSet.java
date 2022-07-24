package com.troika.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the job_post_skill_set database table.
 * 
 */
@Entity
@Table(name = "job_post_skill_set", uniqueConstraints = @UniqueConstraint(columnNames = { "skill_set_id",
"job_post_id" }))
@NamedQuery(name = "JobSkillSet.findAll", query = "SELECT j FROM JobSkillSet j")
public class JobSkillSet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jobSkillSetId;

	// bi-directional many-to-one association to Job
	@ManyToOne
	@JoinColumn(name = "job_post_id")
	private Job job;

	// bi-directional many-to-one association to SkillSet
	@ManyToOne
	@JoinColumn(name = "skill_set_id")

	private SkillSet skillSet;

	public JobSkillSet() {
	}

	/**
	 * @return the jobSkillSetId
	 */
	public Integer getJobSkillSetId() {
		return jobSkillSetId;
	}

	/**
	 * @param jobSkillSetId
	 *            the jobSkillSetId to set
	 */
	public void setJobSkillSetId(Integer jobSkillSetId) {
		this.jobSkillSetId = jobSkillSetId;
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

	/**
	 * @return the skillSet
	 */
	public SkillSet getSkillSet() {
		return skillSet;
	}

	/**
	 * @param skillSet
	 *            the skillSet to set
	 */
	public void setSkillSet(SkillSet skillSet) {
		this.skillSet = skillSet;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((jobSkillSetId == null) ? 0 : jobSkillSetId.hashCode());
		result = prime * result + ((skillSet == null) ? 0 : skillSet.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobSkillSet other = (JobSkillSet) obj;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (jobSkillSetId == null) {
			if (other.jobSkillSetId != null)
				return false;
		} else if (!jobSkillSetId.equals(other.jobSkillSetId))
			return false;
		if (skillSet == null) {
			if (other.skillSet != null)
				return false;
		} else if (!skillSet.equals(other.skillSet))
			return false;
		return true;
	}

	
}
package com.troika.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the job_location database table.
 * 
 */
@Entity
@Table(name = "job_location")
@NamedQuery(name = "JobLocation.findAll", query = "SELECT j FROM JobLocation j")
public class JobLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String city;

	// bi-directional many-to-one association to JobPost
	@OneToMany(mappedBy = "jobLocation")
	private List<Job> jobPosts;

	public JobLocation() {

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the jobPosts
	 */
	public List<Job> getJobPosts() {
		return jobPosts;
	}

	/**
	 * @param jobPosts
	 *            the jobPosts to set
	 */
	public void setJobPosts(List<Job> jobPosts) {
		this.jobPosts = jobPosts;
	}

}
package com.troika.domain.view;

import java.io.Serializable;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JobIndustryTypeData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String jobType;

	public JobIndustryTypeData() {
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobTypeData [id=" + id + ", jobType=" + jobType + "]";
	}

}
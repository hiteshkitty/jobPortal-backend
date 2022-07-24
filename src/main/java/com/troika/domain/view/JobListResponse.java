package com.troika.domain.view;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class JobListResponse implements Serializable {

	private static final long serialVersionUID = 1l;

	private List<JobResponse> jobList;

	/**
	 * @return the jobList
	 */
	public List<JobResponse> getJobList() {
		return jobList;
	}

	/**
	 * @param jobList
	 *            the jobList to set
	 */
	public void setJobList(List<JobResponse> jobList) {
		this.jobList = jobList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobListResponse [jobList=" + jobList + "]";
	}

}

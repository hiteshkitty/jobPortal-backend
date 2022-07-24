package com.troika.services;

import java.util.List;

import com.troika.domain.model.Job;
import com.troika.domain.view.JobApplyRequest;
import com.troika.domain.view.SearchRequest;

public interface JobService {

	Job retrieveJobById(final Integer jobId);

	Job postJob(Job job);

	String deleteJobById(Integer jobId);

	Job updateJob(Job job);

	List<Job> findAll();

	List<Job> retrieveJobs(SearchRequest jobRequest);

	Job applyJob(JobApplyRequest applyRequest);

	List<Job> retrieveRecentJobs();

}

package com.troika.services;

import java.util.List;

import com.troika.domain.model.Job;
import com.troika.domain.model.JobPostActivity;
import com.troika.domain.model.UserAccount;

public interface JobPostActivityService {

	JobPostActivity retrieveJobPostActivityById(final Integer jobPostActivityId);

	JobPostActivity createJobPostActivity(JobPostActivity jobPostActivity);

	JobPostActivity deleteJobPostActivityById(Integer compId);

	JobPostActivity updateJobPostActivity(JobPostActivity jobPostActivity);

	public List<JobPostActivity> findByUserAccount(UserAccount userAccount);

	List<JobPostActivity> findByJob(Job job);

}

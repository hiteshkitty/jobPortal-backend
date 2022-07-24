package com.troika.dao;

import java.util.List;

import com.troika.domain.model.Job;
import com.troika.domain.model.JobPostActivity;
import com.troika.domain.model.UserAccount;

public interface JobPostActivityDao {

	JobPostActivity retireveJobPostActivityById(Integer jobPostActivityId);

	JobPostActivity retrieveJobPostActivityByName(String jobPostActivityName);

	JobPostActivity createJobPostActivity(JobPostActivity jobPostActivity);

	void deleteJobPostActivity(JobPostActivity jobPostActivityToDelete);

	JobPostActivity updateJobPostActivity(JobPostActivity jobPostActivityToUpdate);

	List<JobPostActivity> findByUserAccount(UserAccount userAccount);
	
	List<JobPostActivity> findByJob(Job job);

	List<JobPostActivity> retrieveJobPostActivitysPostedById(UserAccount userAccount);

}

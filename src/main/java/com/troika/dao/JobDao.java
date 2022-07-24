package com.troika.dao;

import java.util.List;

import com.troika.domain.model.Job;
import com.troika.domain.model.UserAccount;

public interface JobDao {

	Job retireveJobById(Integer jobId);

	Job retrieveJobByName(String jobName);

	Job createJob(Job job);

	void deleteJob(Job jobToDelete);

	Job updateJob(Job jobToUpdate);

	List<Job> findAll();

	List<Job> retrieveJobsPostedById(UserAccount userAccount);

	List<Job> retrieveRecentJobs();

}

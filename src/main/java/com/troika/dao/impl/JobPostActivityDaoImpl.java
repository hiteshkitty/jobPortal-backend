package com.troika.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troika.dao.JobPostActivityDao;
import com.troika.domain.model.Job;
import com.troika.domain.model.JobPostActivity;
import com.troika.domain.model.UserAccount;
import com.troika.repo.JobPostActivityRepo;

@Component
public class JobPostActivityDaoImpl implements JobPostActivityDao {

	private static final Logger LOGGER = Logger.getLogger(JobPostActivityDaoImpl.class);

	@Autowired
	private JobPostActivityRepo jobPostActivityRepo;

	@Override
	public JobPostActivity retireveJobPostActivityById(Integer jobPostActivityId) {

		JobPostActivity jobPostActivity = jobPostActivityRepo.findOne(jobPostActivityId);

		LOGGER.debug("jobPostActivity: " + jobPostActivity);

		return jobPostActivity;
	}

	@Override
	public JobPostActivity retrieveJobPostActivityByName(String jobPostActivityName) {

		return null;
	}

	@Override
	public JobPostActivity createJobPostActivity(JobPostActivity jobPostActivity) {

		JobPostActivity createdJobPostActivity = jobPostActivityRepo.save(jobPostActivity);
		return createdJobPostActivity;
	}

	@Override
	public void deleteJobPostActivity(JobPostActivity jobPostActivityToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public JobPostActivity updateJobPostActivity(JobPostActivity jobPostActivityToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobPostActivity> retrieveJobPostActivitysPostedById(UserAccount userAccount) {

		LOGGER.debug("fetching jobPostActivitys by: " + userAccount);

		List<JobPostActivity> jobPostActivitysList = new ArrayList<JobPostActivity>();

		// jobPostActivitysList =
		// jobPostActivityRepo.findByUserAccount(userAccount);

		LOGGER.debug("jobPostActivitysList: " + jobPostActivitysList);

		return jobPostActivitysList;
	}

	@Override
	public List<JobPostActivity> findByUserAccount(UserAccount userAccount) {

		LOGGER.debug("fetching jobPostActivitys by: " + userAccount);

		List<JobPostActivity> jobPostActivityList = jobPostActivityRepo.findByUserAccount(userAccount);

		if (jobPostActivityList != null) {

			LOGGER.debug("jobPostActivityList: " + jobPostActivityList.size());

		}

		return jobPostActivityList;

	}

	@Override
	public List<JobPostActivity> findByJob(Job job) {

		LOGGER.debug("fetching jobPostActivitys by: " + job);

		List<JobPostActivity> jobPostActivityList = jobPostActivityRepo.findByJob(job);

		if (jobPostActivityList != null) {

			LOGGER.debug("jobPostActivityList: " + jobPostActivityList.size());

		}

		return jobPostActivityList;

	}

}

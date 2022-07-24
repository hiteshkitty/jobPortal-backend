package com.troika.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.dao.JobPostActivityDao;
import com.troika.domain.model.Job;
import com.troika.domain.model.JobPostActivity;
import com.troika.domain.model.UserAccount;
import com.troika.services.JobPostActivityService;

@Service
public class JobPostActivityServiceImpl implements JobPostActivityService {

	private static final Logger LOGGER = Logger.getLogger(JobPostActivityServiceImpl.class);

	@Autowired
	private JobPostActivityDao jobPostActivityDao;

	@Override
	public JobPostActivity retrieveJobPostActivityById(Integer jobPostActivityId) {

		JobPostActivity jobPostActivity = jobPostActivityDao.retireveJobPostActivityById(jobPostActivityId);

		return jobPostActivity;
	}

	@Override
	public JobPostActivity createJobPostActivity(JobPostActivity jobPostActivity) {

		LOGGER.debug("creating jobPostActivity with details: " + jobPostActivity);

		JobPostActivity createdJobPostActivity = jobPostActivityDao.createJobPostActivity(jobPostActivity);

		return createdJobPostActivity;

	}

	@Override
	public JobPostActivity deleteJobPostActivityById(Integer compId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobPostActivity updateJobPostActivity(JobPostActivity jobPostActivity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobPostActivity> findByUserAccount(UserAccount userAccount) {

		LOGGER.debug("find jobs applied by userAccount: " + userAccount);

		List<JobPostActivity> jobDetails = new ArrayList<>();

		jobDetails = jobPostActivityDao.findByUserAccount(userAccount);

		LOGGER.debug(jobDetails);

		return jobDetails;
	}

	@Override
	public List<JobPostActivity> findByJob(Job job) {

		LOGGER.debug("find seekers applied for job: " + job);

		List<JobPostActivity> jobDetails = new ArrayList<>();

		jobDetails = jobPostActivityDao.findByJob(job);

		LOGGER.debug(jobDetails);

		return jobDetails;

	}

}

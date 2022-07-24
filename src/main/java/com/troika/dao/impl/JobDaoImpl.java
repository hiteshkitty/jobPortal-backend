package com.troika.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troika.dao.JobDao;
import com.troika.domain.model.Job;
import com.troika.domain.model.UserAccount;
import com.troika.repo.JobRepo;

@Component
public class JobDaoImpl implements JobDao {

	private static final Logger LOGGER = Logger.getLogger(JobDaoImpl.class);

	@Autowired
	private JobRepo jobRepo;

	@Override
	public Job retireveJobById(Integer jobId) {

		Job job = jobRepo.findOne(jobId);

		LOGGER.debug("job: " + job);

		return job;
	}

	@Override
	public Job retrieveJobByName(String jobName) {

		return null;
	}

	@Override
	public Job createJob(Job job) {

		Job createdJob = jobRepo.save(job);
		return createdJob;
	}

	@Override
	public void deleteJob(Job jobToDelete) {

		jobRepo.delete(jobToDelete);

	}

	@Override
	public Job updateJob(Job jobToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> findAll() {

		List<Job> allJobs = (List<Job>) jobRepo.findAll();

		return allJobs;
	}

	@Override
	public List<Job> retrieveJobsPostedById(UserAccount userAccount) {

		LOGGER.debug("fetching jobs by: " + userAccount);

		List<Job> jobsList = new ArrayList<Job>();

		jobsList = jobRepo.findByUserAccount(userAccount);

		LOGGER.debug("jobsList: " + jobsList);

		return jobsList;
	}

	@Override
	public List<Job> retrieveRecentJobs() {

		List<Job> jobsList = new ArrayList<Job>();

		jobsList = jobRepo.findFirst4ByOrderByCreatedDateDesc();

		LOGGER.debug("jobsList: " + jobsList);

		return jobsList;
	}

}

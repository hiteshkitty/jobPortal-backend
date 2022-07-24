package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troika.dao.JobTypeDao;
import com.troika.domain.model.JobType;
import com.troika.repo.JobTypeRepo;

@Component
public class JobTypeDaoImpl implements JobTypeDao {

	private static final Logger LOGGER = Logger.getLogger(JobTypeDaoImpl.class);

	@Autowired
	private JobTypeRepo jobTypeRepo;

	@Override
	public JobType retireveJobTypeById(Integer jobTypeId) {

		JobType jobType = jobTypeRepo.findOne(jobTypeId);

		LOGGER.trace("jobType: " + jobType);

		return jobType;
	}

	@Override
	public JobType retrieveJobTypeByName(String jobTypeName) {

		return null;
	}

	@Override
	public JobType createJobType(JobType jobType) {

		JobType createdJobType = jobTypeRepo.save(jobType);
		return createdJobType;
	}

	@Override
	public void deleteJobType(JobType jobTypeToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public JobType updateJobType(JobType jobTypeToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobType> findAll() {
		List<JobType> jobType = null;

		LOGGER.trace("fetching all JobType");

		jobType = (List) jobTypeRepo.findAll();

		LOGGER.trace("fetch all JobType: ");

		return jobType;
	}

}

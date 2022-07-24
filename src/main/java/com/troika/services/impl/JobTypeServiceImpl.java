package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.dao.JobTypeDao;
import com.troika.domain.model.JobType;
import com.troika.domain.model.State;
import com.troika.services.JobTypeService;

@Service
public class JobTypeServiceImpl implements JobTypeService {

	private static final Logger LOGGER = Logger.getLogger(JobTypeServiceImpl.class);

	@Autowired
	private JobTypeDao jobTypeDao;

	@Override
	public JobType retrieveJobTypeById(Integer jobTypeId) {

		JobType jobType = jobTypeDao.retireveJobTypeById(jobTypeId);

		return jobType;
	}

	@Override
	public JobType postJobType(JobType jobType) {
		LOGGER.trace("creating jobType with details: " + jobType);

		JobType createdJobType = jobTypeDao.createJobType(jobType);

		return createdJobType;
	}

	@Override
	public JobType deleteJobTypeById(Integer compId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobType updateJobType(JobType jobType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobType> findAll() {
		
		LOGGER.trace("fetching all states");

		List<JobType> jobTypeList = jobTypeDao.findAll();

		LOGGER.trace("fetched jobTypeList: " + jobTypeList);

		return jobTypeList;
	}

}

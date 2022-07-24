package com.troika.services;

import java.util.List;

import com.troika.domain.model.JobType;

public interface JobTypeService {

	JobType retrieveJobTypeById(final Integer jobTypeId);

	JobType postJobType(JobType jobType);

	JobType deleteJobTypeById(Integer jobTypeId);

	JobType updateJobType(JobType jobType);

	List<JobType> findAll();

}

package com.troika.dao;

import java.util.List;

import com.troika.domain.model.JobType;

public interface JobTypeDao {

	JobType retireveJobTypeById(Integer jobTypeId);

	JobType retrieveJobTypeByName(String jobTypeName);

	JobType createJobType(JobType jobType);

	void deleteJobType(JobType jobTypeToDelete);

	JobType updateJobType(JobType jobTypeToUpdate);

	List<JobType> findAll();
}

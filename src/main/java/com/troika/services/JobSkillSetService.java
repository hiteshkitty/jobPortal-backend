package com.troika.services;

import java.util.List;

import com.troika.domain.model.JobSkillSet;

public interface JobSkillSetService {

	JobSkillSet retrieveJobSkillSetById(final Integer jobSkillSetId);

	JobSkillSet registerJobSkillSet(JobSkillSet jobSkillSet);

	JobSkillSet deleteJobSkillSetById(Integer compId);

	JobSkillSet updateJobSkillSet(JobSkillSet jobSkillSet);

	List<JobSkillSet> findAll();

}

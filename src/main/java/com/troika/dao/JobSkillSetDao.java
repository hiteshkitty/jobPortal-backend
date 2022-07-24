package com.troika.dao;

import java.util.List;

import com.troika.domain.model.JobSkillSet;

public interface JobSkillSetDao {

	JobSkillSet retireveJobSkillSetById(Integer jobSkillSetId);

	JobSkillSet retrieveJobSkillSetByName(String jobSkillSetName);

	JobSkillSet createJobSkillSet(JobSkillSet jobSkillSet);

	void deleteJobSkillSet(JobSkillSet jobSkillSetToDelete);

	JobSkillSet updateJobSkillSet(JobSkillSet jobSkillSetToUpdate);

	List<JobSkillSet> findAll();

	List<JobSkillSet> findJobsBySkillList(List<Integer> skillIdList);
}

package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troika.dao.JobSkillSetDao;
import com.troika.domain.model.JobSkillSet;
import com.troika.repo.JobSkillSetRepo;

@Component
public class JobSkillSetDaoImpl implements JobSkillSetDao {

	private static final Logger LOGGER = Logger.getLogger(JobSkillSetDaoImpl.class);

	@Autowired
	private JobSkillSetRepo jobSkillSetRepo;

	@Override
	public JobSkillSet retireveJobSkillSetById(Integer jobSkillSetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobSkillSet retrieveJobSkillSetByName(String jobSkillSetName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobSkillSet createJobSkillSet(JobSkillSet jobSkillSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteJobSkillSet(JobSkillSet jobSkillSetToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public JobSkillSet updateJobSkillSet(JobSkillSet jobSkillSetToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobSkillSet> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobSkillSet> findJobsBySkillList(List<Integer> skillIdList) {

		List<JobSkillSet> jobsBySkillList = null;

		LOGGER.debug("fetching findJobsBySkillList: " + skillIdList);

		jobsBySkillList = jobSkillSetRepo.findJobsBySkillList(skillIdList);

		if (jobsBySkillList != null) {

			LOGGER.debug("fetch all jobsBySkillList: " + jobsBySkillList.size());

		}

		return jobsBySkillList;
	}

}

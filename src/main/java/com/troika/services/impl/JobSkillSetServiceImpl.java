package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.dao.JobSkillSetDao;
import com.troika.domain.model.JobSkillSet;
import com.troika.services.JobSkillSetService;

@Service
public class JobSkillSetServiceImpl implements JobSkillSetService {

	private static final Logger LOGGER = Logger.getLogger(JobSkillSetServiceImpl.class);

	@Autowired
	private JobSkillSetDao jobSkillSetDao;

	@Override
	public JobSkillSet retrieveJobSkillSetById(Integer jobSkillSetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobSkillSet registerJobSkillSet(JobSkillSet jobSkillSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobSkillSet deleteJobSkillSetById(Integer compId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobSkillSet updateJobSkillSet(JobSkillSet jobSkillSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobSkillSet> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

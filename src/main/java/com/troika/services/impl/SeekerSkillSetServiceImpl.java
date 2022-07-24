package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.dao.SeekerSkillSetDao;
import com.troika.domain.model.SeekerSkillSet;
import com.troika.services.SeekerSkillSetService;

@Service
public class SeekerSkillSetServiceImpl implements SeekerSkillSetService {

	private static final Logger LOGGER = Logger.getLogger(SeekerSkillSetServiceImpl.class);

	@Autowired
	private SeekerSkillSetDao seekerSkillSetDao;

	@Override
	public SeekerSkillSet retrieveSeekerSkillSetById(Integer seekerSkillSetId) {

		LOGGER.debug("finding seekerSkillSet details using seekerSkillSetId: " + seekerSkillSetId);

		SeekerSkillSet seekerSkillSet = seekerSkillSetDao.retireveSeekerSkillSetById(seekerSkillSetId);

		return seekerSkillSet;
	}

	@Override
	public SeekerSkillSet createSeekerSkillSet(SeekerSkillSet seekerSkillSet) {

		LOGGER.debug("creating seekerSkillSet with details: " + seekerSkillSet);

		SeekerSkillSet createdSeekerSkillSet = seekerSkillSetDao.createSeekerSkillSet(seekerSkillSet);

		return null;
	}

	@Override
	public SeekerSkillSet deleteSeekerSkillSetById(Integer compId) {

		LOGGER.debug("deleting seekerSkillSet with compId: " + compId);

		// check whether seekerSkillSet exists, if then retrieve it and delete
		// it.

		SeekerSkillSet seekerSkillSetToDelete = seekerSkillSetDao.retireveSeekerSkillSetById(compId);

		return null;
	}

	@Override
	public SeekerSkillSet updateSeekerSkillSet(SeekerSkillSet seekerSkillSet) {

		LOGGER.debug("Updating seekerSkillSet with seekerSkillSet: " + seekerSkillSet);

		// check whether seekerSkillSet exists, if then retrieve it and update
		// it.

		// SeekerSkillSet seekerSkillSetToUpdate =
		// seekerSkillSetDao.retireveSeekerSkillSetById(seekerSkillSet.getId());

		return null;
	}

	@Override
	public List<SeekerSkillSet> findAll() {

		LOGGER.debug("findAll skillSet");

		return seekerSkillSetDao.findAll();

	}

}

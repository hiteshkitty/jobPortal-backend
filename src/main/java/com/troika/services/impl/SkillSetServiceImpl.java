package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.dao.SkillSetDao;
import com.troika.domain.model.City;
import com.troika.domain.model.SkillSet;
import com.troika.services.SkillSetService;

@Service
public class SkillSetServiceImpl implements SkillSetService {

	private static final Logger LOGGER = Logger.getLogger(SkillSetServiceImpl.class);

	@Autowired
	private SkillSetDao skillSetDao;

	@Override
	public SkillSet retrieveSkillSetById(Integer skillSetId) {

		LOGGER.debug("finding skillSet details using skillSetId: " + skillSetId);

		SkillSet skillSet = skillSetDao.retireveSkillSetById(skillSetId);

		return skillSet;
	}

	@Override
	public SkillSet createSkillSet(SkillSet skillSet) {

		LOGGER.debug("creating skillSet with details: " + skillSet);

		SkillSet createdSkillSet = skillSetDao.createSkillSet(skillSet);

		return createdSkillSet;
	}

	@Override
	public Boolean deleteSkillSetById(Integer skillSetId) {

		LOGGER.debug("deleting skillSet with compId: " + skillSetId);

		Boolean isDeleted = Boolean.FALSE;

		// check whether skillSet exists, if then retrieve it and delete it.

		SkillSet skillSetToDelete = skillSetDao.retireveSkillSetById(skillSetId);

		if (skillSetToDelete != null) {

			if (skillSetToDelete.getId() != 0) {

				LOGGER.debug("deleting skillSet : ");

				skillSetDao.deleteSkillSet(skillSetToDelete);

				isDeleted = Boolean.TRUE;

			} else {

				LOGGER.trace("couldn't find skillSet with Id: " + skillSetId);
			}
		}

		return isDeleted;
	}

	@Override
	public SkillSet updateSkillSet(SkillSet skillSet) {

		LOGGER.debug("Updating skillSet with skillSet: " + skillSet);

		// check whether skillSet exists, if then retrieve it and update it.

		SkillSet skillSetToUpdate = skillSetDao.retireveSkillSetById(skillSet.getId());

		if (skillSetToUpdate != null) {

			if (skillSetToUpdate.getId() != 0) {

				LOGGER.debug("updating skillSet : ");

				// skillSetToUpdate = JobPortalHelper.copySkillSet(skillSet,
				// skillSetToUpdate);

				skillSetDao.updateSkillSet(skillSetToUpdate);

			}
		}

		return null;
	}

	@Override
	public List<SkillSet> findAll() {

		LOGGER.debug("findAll skillSet");

		return skillSetDao.findAll();

	}

	@Override
	public List<SkillSet> findAllById(List<Integer> listId) {

		LOGGER.debug("findAllById skillSet");

		return skillSetDao.findAllBySkillList(listId);

	}

	@Override
	public Boolean changeStatusSkillSet(SkillSet skillSet) {

		Boolean isStatusChanged = Boolean.FALSE;

		SkillSet skillSetToUpdate = skillSetDao.retrieveSkillSetByName(skillSet.getSkillSetName());

		if (skillSetToUpdate != null) {

			LOGGER.debug("changing status skillSet: " + skillSetToUpdate.getSkillSetName());

			skillSetToUpdate.setIsActive(skillSet.getIsActive());

			skillSetDao.updateSkillSet(skillSetToUpdate);

			isStatusChanged = Boolean.TRUE;

		}

		return isStatusChanged;

	}

}

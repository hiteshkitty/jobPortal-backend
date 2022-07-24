package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troika.dao.SeekerSkillSetDao;
import com.troika.domain.model.SeekerSkillSet;
import com.troika.repo.SeekerSkillSetRepo;

@Component
public class SeekerSkillSetDaoImpl implements SeekerSkillSetDao {

	private static final Logger LOGGER = Logger.getLogger(SeekerSkillSetDaoImpl.class);

	@Autowired
	SeekerSkillSetRepo seekerSkillSetRepo;

	@Override
	public SeekerSkillSet retireveSeekerSkillSetById(Integer seekerSkillSetId) {

		LOGGER.debug("retrieving seekerSkillSet details using seekerSkillSetId: " + seekerSkillSetId);

		SeekerSkillSet seekerSkillSet = null;

		seekerSkillSet = seekerSkillSetRepo.findOne(seekerSkillSetId);

		LOGGER.debug(
				"retrieved SeekerSkillSetDetails for seekerSkillSetId: " + seekerSkillSetId + " is " + seekerSkillSet);

		return seekerSkillSet;

	}

	@Override
	public SeekerSkillSet retrieveSeekerSkillSetByName(String seekerSkillSetName) {

		LOGGER.debug("retrieving seekerSkillSet details using seekerSkillSetName: " + seekerSkillSetName);

		SeekerSkillSet seekerSkillSet = null;

		// seekerSkillSet =
		// seekerSkillSetRepo.findBySeekerSkillSetName(seekerSkillSetName);

		LOGGER.debug("retrieved SeekerSkillSetDetails for seekerSkillSetName: " + seekerSkillSetName + " is "
				+ seekerSkillSet);

		return seekerSkillSet;

	}

	@Override
	public SeekerSkillSet createSeekerSkillSet(SeekerSkillSet seekerSkillSet) {

		LOGGER.debug("creating seekerSkillSet details: " + seekerSkillSet);

		SeekerSkillSet seekerSkillSetCreated = seekerSkillSetRepo.save(seekerSkillSet);

		LOGGER.debug("created SeekerSkillSetDetails: " + seekerSkillSetCreated);

		return seekerSkillSetCreated;

	}

	@Override
	public void deleteSeekerSkillSet(SeekerSkillSet seekerSkillSetToDelete) {

		LOGGER.debug("deleting seekerSkillSet details: " + seekerSkillSetToDelete);

		seekerSkillSetRepo.delete(seekerSkillSetToDelete);

		LOGGER.debug("created SeekerSkillSetDetails: " + seekerSkillSetToDelete);

	}

	@Override
	public SeekerSkillSet updateSeekerSkillSet(SeekerSkillSet seekerSkillSetToUpdate) {

		LOGGER.debug("updating seekerSkillSet details: " + seekerSkillSetToUpdate);

		SeekerSkillSet seekerSkillSetUpdated = seekerSkillSetRepo.save(seekerSkillSetToUpdate);

		LOGGER.debug("seekerSkillSetUpdated SeekerSkillSetDetails: " + seekerSkillSetUpdated);

		return seekerSkillSetUpdated;
	}

	@Override
	public List<SeekerSkillSet> findAll() {

		List<SeekerSkillSet> seekerSkills = null;

		LOGGER.debug("fetching all SeekerSkillSet");

		seekerSkills = (List) seekerSkillSetRepo.findAll();

		LOGGER.debug("fetch all SeekerSkillSet: ");

		return seekerSkills;
	}

	@Override
	public List<SeekerSkillSet> findUsersBySkillList(List<Integer> skillIdList) {
		List<SeekerSkillSet> usersBySkillList = null;

		LOGGER.debug("fetching SeekerSkillSet: " + skillIdList);

		usersBySkillList = seekerSkillSetRepo.findUsersBySkillList(skillIdList);

		if (usersBySkillList != null) {

			LOGGER.debug("fetch all usersBySkillList: " + usersBySkillList.size());

		}

		return usersBySkillList;
	}

}

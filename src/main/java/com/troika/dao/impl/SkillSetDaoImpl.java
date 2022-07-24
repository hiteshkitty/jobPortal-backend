package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troika.dao.SkillSetDao;
import com.troika.domain.model.SkillSet;
import com.troika.repo.SkillSetRepo;

@Component
public class SkillSetDaoImpl implements SkillSetDao {

	private static final Logger LOGGER = Logger.getLogger(SkillSetDaoImpl.class);

	@Autowired
	private SkillSetRepo skillSetRepo;

	@Override
	public SkillSet retireveSkillSetById(Integer skillSetId) {

		LOGGER.trace("retrieving skillSet details using skillSetId: " + skillSetId);

		SkillSet skillSet = null;

		skillSet = skillSetRepo.findOne(skillSetId);

		LOGGER.trace("retrieved SkillSetDetails for skillSetId: " + skillSetId + " is " + skillSet);

		return skillSet;

	}

	@Override
	public SkillSet retrieveSkillSetByName(String skillSetName) {

		LOGGER.trace("retrieving skillSet details using skillSetName: " + skillSetName);

		SkillSet skillSet = null;

		skillSet = skillSetRepo.findBySkillSetName(skillSetName);

		LOGGER.trace("retrieved SkillSetDetails for skillSetName: " + skillSetName + " is " + skillSet);

		return skillSet;

	}

	@Override
	public SkillSet createSkillSet(SkillSet skillSet) {

		LOGGER.trace("creating skillSet details: " + skillSet);

		SkillSet skillSetCreated = skillSetRepo.save(skillSet);

		LOGGER.trace("created SkillSetDetails: " + skillSetCreated);

		return skillSetCreated;

	}

	@Override
	public void deleteSkillSet(SkillSet skillSetToDelete) {

		LOGGER.trace("deleting skillSet details: " + skillSetToDelete);

		skillSetRepo.delete(skillSetToDelete);

		LOGGER.trace("created SkillSetDetails: " + skillSetToDelete);

	}

	@Override
	public SkillSet updateSkillSet(SkillSet skillSetToUpdate) {

		LOGGER.trace("updating skillSet details: " + skillSetToUpdate);

		SkillSet skillSetUpdated = skillSetRepo.save(skillSetToUpdate);

		LOGGER.trace("skillSetUpdated SkillSetDetails: " + skillSetUpdated);

		return skillSetUpdated;
	}

	@Override
	public List<SkillSet> findAll() {

		List<SkillSet> skills = null;

		LOGGER.trace("fetching all SkillSet");

		skills = (List) skillSetRepo.findByIsActiveTrueOrderBySkillSetName();

		LOGGER.trace("fetch all SkillSet: ");

		return skills;
	}

	@Override
	public List<SkillSet> findAllBySkillList(List<Integer> listId) {

		List<SkillSet> skills = null;

		LOGGER.trace("fetching findAllById");

		skills = skillSetRepo.findBySkillList(listId);

		LOGGER.trace("fetch all SkillSet: ");

		return skills;
	}

}

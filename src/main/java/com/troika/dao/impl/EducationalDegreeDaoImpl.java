package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.troika.dao.EducationalDegreeDao;
import com.troika.domain.model.EducationalDegree;
import com.troika.repo.EducationalDegreeRepo;

@Component
public class EducationalDegreeDaoImpl implements EducationalDegreeDao {

	private static final Logger LOGGER = Logger.getLogger(EducationalDegreeDaoImpl.class);

	@Autowired
	private EducationalDegreeRepo educationalDegreeRepo;

	@Override
	@Cacheable("educationalDegree")
	public EducationalDegree retireveEducationalDegreeById(Integer educationalDegreeId) {

		EducationalDegree degree = educationalDegreeRepo.findOne(educationalDegreeId);

		LOGGER.trace("EducationalDegree: " + degree);

		return degree;

	}

	@Override
	public EducationalDegree createEducationalDegree(EducationalDegree educationalDegree) {

		LOGGER.trace("creating educationalDegree details: " + educationalDegree);

		EducationalDegree educationalDegreeCreated = educationalDegreeRepo.save(educationalDegree);

		LOGGER.trace("created EducationalDegreeDetails: " + educationalDegreeCreated);

		return educationalDegreeCreated;

	}

	@Override
	public void deleteEducationalDegree(EducationalDegree educationalDegreeToDelete) {

		LOGGER.trace("deleting educationalDegree details: " + educationalDegreeToDelete);

		educationalDegreeRepo.delete(educationalDegreeToDelete);

		LOGGER.trace("created EducationalDegreeDetails: " + educationalDegreeToDelete);

	}

	@Override
	public EducationalDegree updateEducationalDegree(EducationalDegree educationalDegreeToUpdate) {

		LOGGER.trace("updating educationalDegree details: " + educationalDegreeToUpdate);

		EducationalDegree educationalDegreeUpdated = educationalDegreeRepo.save(educationalDegreeToUpdate);

		LOGGER.trace("educationalDegreeUpdated EducationalDegreeDetails: " + educationalDegreeUpdated);

		return educationalDegreeUpdated;
	}

	@Override
	public List<EducationalDegree> findAll() {

		List<EducationalDegree> educationalDegree = null;

		LOGGER.trace("fetching all EducationalDegree");

		educationalDegree = (List) educationalDegreeRepo.findByIsActiveTrueOrderByDegreeName();

		LOGGER.trace("fetch all EducationalDegree: ");

		return educationalDegree;
	}

	@Override
	public EducationalDegree retireveEducationalDegreeByName(String degreeName) {

		EducationalDegree degree = educationalDegreeRepo.findByDegreeName(degreeName);

		LOGGER.trace("EducationalDegree: " + degree);

		return degree;

	}

}

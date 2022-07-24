package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.troika.dao.EducationalDegreeDao;
import com.troika.domain.model.EducationalDegree;
import com.troika.services.EducationalDegreeService;

@Service
public class EducationalDegreeServiceImpl implements EducationalDegreeService {

	private static final Logger LOGGER = Logger.getLogger(EducationalDegreeServiceImpl.class);

	@Autowired
	private EducationalDegreeDao educationalDegreeDao;

	@Override
	public EducationalDegree retrieveEducationalDegreeById(Integer educationalDegreeId) {
		EducationalDegree educationalDegree = educationalDegreeDao.retireveEducationalDegreeById(educationalDegreeId);

		return educationalDegree;
	}

	@Override
	public EducationalDegree postEducationalDegree(EducationalDegree educationalDegree) {

		EducationalDegree createdEducationalDegree = educationalDegreeDao.createEducationalDegree(educationalDegree);

		return createdEducationalDegree;

	}

	@Override
	public Boolean deleteEducationalDegreeById(Integer educationalDegreeId) {

		Boolean isDeleted = Boolean.FALSE;

		EducationalDegree educationalDegreeToDelete = educationalDegreeDao
				.retireveEducationalDegreeById(educationalDegreeId);

		if (educationalDegreeToDelete != null) {

			LOGGER.debug("deleting educationalDegree: " + educationalDegreeToDelete.getDegreeName());

			educationalDegreeDao.deleteEducationalDegree(educationalDegreeToDelete);

			isDeleted = Boolean.TRUE;

		}

		return isDeleted;

	}

	@Override
	public EducationalDegree updateEducationalDegree(EducationalDegree educationalDegree) {

		return null;
	}

	@Override
	@Cacheable("degrees")
	public List<EducationalDegree> findAll() {

		LOGGER.trace("fetching all degrees");

		List<EducationalDegree> degrees = educationalDegreeDao.findAll();

		LOGGER.trace("fetched degrees: " + degrees);

		return degrees;

	}

	@Override
	public Boolean deleteEducationalDegreeByName(String educationalDegreeName) {

		Boolean isDeleted = Boolean.FALSE;

		EducationalDegree educationalDegreeToDelete = educationalDegreeDao
				.retireveEducationalDegreeByName(educationalDegreeName);

		if (educationalDegreeToDelete != null) {

			LOGGER.debug("deleting educationalDegree: " + educationalDegreeToDelete.getDegreeName());

			educationalDegreeDao.deleteEducationalDegree(educationalDegreeToDelete);

			isDeleted = Boolean.TRUE;

		}

		return isDeleted;

	}

	@Override
	public Boolean changeStatusEducationalDegree(EducationalDegree degree) {

		Boolean isStatusChanged = Boolean.FALSE;

		EducationalDegree educationalDegreeToUpdate = educationalDegreeDao
				.retireveEducationalDegreeByName(degree.getDegreeName());

		if (educationalDegreeToUpdate != null) {

			LOGGER.debug("changing status educationalDegree: " + educationalDegreeToUpdate.getDegreeName());

			educationalDegreeToUpdate.setIsActive(degree.getIsActive());

			educationalDegreeDao.updateEducationalDegree(educationalDegreeToUpdate);

			isStatusChanged = Boolean.TRUE;

		}

		return isStatusChanged;

	}

}

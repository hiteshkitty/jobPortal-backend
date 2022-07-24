package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troika.dao.SeekerProfileDao;
import com.troika.domain.model.SeekerProfile;
import com.troika.repo.SeekerProfileRepo;

@Component
public class SeekerProfileDaoImpl implements SeekerProfileDao {

	private static final Logger LOGGER = Logger.getLogger(SeekerProfileDaoImpl.class);

	@Autowired
	SeekerProfileRepo seekerProfileRepo;

	@Override
	public SeekerProfile retireveSeekerProfileById(Integer seekerProfileId) {

		LOGGER.debug("retrieving seekerProfile details using seekerProfileId: " + seekerProfileId);

		SeekerProfile seekerProfile = null;

		seekerProfile = seekerProfileRepo.findOne(seekerProfileId);

		LOGGER.debug("retrieved SeekerProfileDetails for seekerProfileId: " + seekerProfileId + " is " + seekerProfile);

		return seekerProfile;

	}

	@Override
	public SeekerProfile retrieveSeekerProfileByName(String seekerProfileName) {

		LOGGER.debug("retrieving seekerProfile details using seekerProfileName: " + seekerProfileName);

		SeekerProfile seekerProfile = null;

		// seekerProfile =
		// seekerProfileRepo.findBySeekerProfileName(seekerProfileName);

		LOGGER.debug(
				"retrieved SeekerProfileDetails for seekerProfileName: " + seekerProfileName + " is " + seekerProfile);

		return seekerProfile;

	}

	@Override
	public SeekerProfile createSeekerProfile(SeekerProfile seekerProfile) {

		LOGGER.debug("creating seekerProfile details: " + seekerProfile);
		SeekerProfile seekerProfileCreated = null;
		try {
			seekerProfileCreated = seekerProfileRepo.save(seekerProfile);
		} catch (Exception ex) {

			throw ex;
		}
		LOGGER.debug("created SeekerProfileDetails: " + seekerProfileCreated);

		return seekerProfileCreated;

	}

	@Override
	public void deleteSeekerProfile(SeekerProfile seekerProfileToDelete) {

		LOGGER.debug("deleting seekerProfile details: " + seekerProfileToDelete);

		seekerProfileRepo.delete(seekerProfileToDelete);

		LOGGER.debug("created SeekerProfileDetails: " + seekerProfileToDelete);

	}

	@Override
	public SeekerProfile updateSeekerProfile(SeekerProfile seekerProfileToUpdate) {

		LOGGER.debug("updating seekerProfile details: " + seekerProfileToUpdate);

		SeekerProfile seekerProfileUpdated = seekerProfileRepo.save(seekerProfileToUpdate);

		LOGGER.debug("seekerProfileUpdated SeekerProfileDetails: " + seekerProfileUpdated);

		return seekerProfileUpdated;
	}

	@Override
	public List<SeekerProfile> findAll() {

		List<SeekerProfile> seekerProfile = null;

		LOGGER.debug("fetching all seekerProfile");

		seekerProfile = (List) seekerProfileRepo.findAll();

		LOGGER.debug("fetch all seekerProfile: ");

		return seekerProfile;
	}

}

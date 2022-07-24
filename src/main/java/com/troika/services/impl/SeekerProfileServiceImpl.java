package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.dao.SeekerProfileDao;
import com.troika.domain.model.SeekerProfile;
import com.troika.services.SeekerProfileService;

@Service
public class SeekerProfileServiceImpl implements SeekerProfileService {

	private static final Logger LOGGER = Logger.getLogger(SeekerProfileServiceImpl.class);

	@Autowired
	private SeekerProfileDao seekerProfileDao;

	@Override
	public SeekerProfile retrieveSeekerProfileById(Integer seekerProfileId) {

		LOGGER.debug("finding seekerProfile details using seekerProfileId: " + seekerProfileId);

		SeekerProfile seekerProfile = seekerProfileDao.retireveSeekerProfileById(seekerProfileId);

		return seekerProfile;
	}

	@Override
	public SeekerProfile createSeekerProfile(SeekerProfile seekerProfile) {

		LOGGER.debug("creating seekerProfile with details: " + seekerProfile);

		SeekerProfile createdSeekerProfile = seekerProfileDao.createSeekerProfile(seekerProfile);

		return createdSeekerProfile;
	}

	@Override
	public SeekerProfile deleteSeekerProfileById(Integer compId) {

		LOGGER.debug("deleting seekerProfile with compId: " + compId);

		// check whether seekerProfile exists, if then retrieve it and delete
		// it.

		SeekerProfile seekerProfileToDelete = seekerProfileDao.retireveSeekerProfileById(compId);

		// if (seekerProfileToDelete != null) {
		//
		// if (seekerProfileToDelete.getUserAccountId() != 0) {
		//
		// LOGGER.debug("deleting seekerProfile : ");
		//
		// seekerProfileDao.deleteSeekerProfile(seekerProfileToDelete);
		//
		// }
		// }

		return null;
	}

	@Override
	public SeekerProfile updateSeekerProfile(SeekerProfile seekerProfile) {

		LOGGER.debug("Updating seekerProfile with seekerProfile: " + seekerProfile);

		// check whether seekerProfile exists, if then retrieve it and update
		// it.
		//
		// SeekerProfile seekerProfileToUpdate = seekerProfileDao
		// .retireveSeekerProfileById(seekerProfile.getUserAccountId());
		//
		// if (seekerProfileToUpdate != null) {
		//
		// if (seekerProfileToUpdate.getUserAccountId() != 0) {
		//
		// LOGGER.debug("updating seekerProfile : ");
		//
		// // seekerProfileToUpdate =
		// // JobPortalHelper.copySeekerProfile(seekerProfile,
		// // seekerProfileToUpdate);
		//
		// seekerProfileDao.updateSeekerProfile(seekerProfileToUpdate);
		//
		// }
		// }
		//
		return null;
	}

	@Override
	public List<SeekerProfile> findAll() {
		return seekerProfileDao.findAll();
	}

}

package com.troika.dao;

import java.util.List;

import com.troika.domain.model.SeekerProfile;

public interface SeekerProfileDao {

	SeekerProfile retireveSeekerProfileById(Integer seekerProfileId);

	SeekerProfile retrieveSeekerProfileByName(String seekerProfileName);

	SeekerProfile createSeekerProfile(SeekerProfile seekerProfile);

	void deleteSeekerProfile(SeekerProfile seekerProfileToDelete);

	SeekerProfile updateSeekerProfile(SeekerProfile seekerProfileToUpdate);

	List<SeekerProfile> findAll();
}

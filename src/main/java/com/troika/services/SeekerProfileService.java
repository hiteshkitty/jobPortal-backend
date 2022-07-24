package com.troika.services;

import java.util.List;

import com.troika.domain.model.SeekerProfile;

public interface SeekerProfileService {

	SeekerProfile retrieveSeekerProfileById(final Integer seekerProfileId);

	SeekerProfile createSeekerProfile(SeekerProfile seekerProfile);

	SeekerProfile deleteSeekerProfileById(Integer seekerProfileId);

	SeekerProfile updateSeekerProfile(SeekerProfile seekerProfile);

	List<SeekerProfile> findAll();
}

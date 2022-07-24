package com.troika.services;

import java.util.List;

import com.troika.domain.model.SeekerSkillSet;

public interface SeekerSkillSetService {

	SeekerSkillSet retrieveSeekerSkillSetById(final Integer seekerSkillSetId);

	SeekerSkillSet createSeekerSkillSet(SeekerSkillSet seekerSkillSet);

	SeekerSkillSet deleteSeekerSkillSetById(Integer compId);

	SeekerSkillSet updateSeekerSkillSet(SeekerSkillSet seekerSkillSet);

	List<SeekerSkillSet> findAll();

}

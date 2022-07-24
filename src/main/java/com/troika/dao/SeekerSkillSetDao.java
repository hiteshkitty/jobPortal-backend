package com.troika.dao;

import java.util.List;

import com.troika.domain.model.SeekerSkillSet;

public interface SeekerSkillSetDao {

	SeekerSkillSet retireveSeekerSkillSetById(Integer seekerSkillSetId);

	SeekerSkillSet retrieveSeekerSkillSetByName(String seekerSkillSetName);

	SeekerSkillSet createSeekerSkillSet(SeekerSkillSet seekerSkillSet);

	void deleteSeekerSkillSet(SeekerSkillSet seekerSkillSetToDelete);

	SeekerSkillSet updateSeekerSkillSet(SeekerSkillSet seekerSkillSetToUpdate);

	List<SeekerSkillSet> findAll();

	List<SeekerSkillSet> findUsersBySkillList(List<Integer> skillIdList);
}

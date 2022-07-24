package com.troika.dao;

import java.util.List;

import com.troika.domain.model.SkillSet;

public interface SkillSetDao {

	SkillSet retireveSkillSetById(Integer skillSetId);

	SkillSet retrieveSkillSetByName(String skillSetName);

	SkillSet createSkillSet(SkillSet skillSet);

	void deleteSkillSet(SkillSet skillSetToDelete);

	SkillSet updateSkillSet(SkillSet skillSetToUpdate);

	List<SkillSet> findAll();

	List<SkillSet> findAllBySkillList(List<Integer> listId);
}

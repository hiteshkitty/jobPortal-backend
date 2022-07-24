package com.troika.services;

import java.util.List;

import com.troika.domain.model.SkillSet;

public interface SkillSetService {

	SkillSet retrieveSkillSetById(final Integer skillSetId);

	SkillSet createSkillSet(SkillSet skillSet);

	Boolean deleteSkillSetById(Integer skillSetId);

	Boolean changeStatusSkillSet(SkillSet skillSet);

	SkillSet updateSkillSet(SkillSet skillSet);

	List<SkillSet> findAll();

	List<SkillSet> findAllById(List<Integer> listId);

}

package com.troika.repo;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.troika.domain.model.City;
import com.troika.domain.model.SkillSet;

@Transactional
@Repository
public interface SkillSetRepo extends CrudRepository<SkillSet, Serializable> {

	SkillSet findBySkillSetName(String skillSetName);

	@Query("select o from SkillSet o where id in :listId")
	List<SkillSet> findBySkillList(@Param("listId") List<Integer> listId);
	
	public Iterable<SkillSet> findByIsActiveTrueOrderBySkillSetName();
}

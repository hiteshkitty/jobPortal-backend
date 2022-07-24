package com.troika.repo;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.troika.domain.model.SeekerSkillSet;

@Transactional
@Repository
public interface SeekerSkillSetRepo extends CrudRepository<SeekerSkillSet, Serializable> {

	@Query("select distinct o from SeekerSkillSet o where SEEKER_SKILL_ID in :listId")
	List<SeekerSkillSet> findUsersBySkillList(@Param("listId") List<Integer> listId);

}

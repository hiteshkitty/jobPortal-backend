package com.troika.repo;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.troika.domain.model.JobSkillSet;

@Transactional
@Repository
public interface JobSkillSetRepo extends CrudRepository<JobSkillSet, Serializable> {

	@Query("select distinct o from JobSkillSet o where skill_set_id in :listId")
	List<JobSkillSet> findJobsBySkillList(@Param("listId") List<Integer> listId);
}

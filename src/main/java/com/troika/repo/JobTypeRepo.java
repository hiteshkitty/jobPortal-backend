package com.troika.repo;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.troika.domain.model.JobType;

@Transactional
@Repository
public interface JobTypeRepo extends CrudRepository<JobType, Serializable> {

	JobType findByJobType(String jobType);

	@Query("select o from JobType o where id in :listId")
	List<JobType> findByJobId(@Param("listId") List<Integer> listId);

}

package com.troika.repo;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.troika.domain.model.City;
import com.troika.domain.model.EducationalDegree;

@Transactional
@Repository
@Cacheable
public interface EducationalDegreeRepo extends CrudRepository<EducationalDegree, Serializable> {

	public EducationalDegree findByDegreeName(String degreeName);
	
	public Iterable<EducationalDegree> findByIsActiveTrueOrderByDegreeName();

}

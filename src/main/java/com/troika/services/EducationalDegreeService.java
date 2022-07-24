package com.troika.services;

import java.util.List;

import com.troika.domain.model.EducationalDegree;

public interface EducationalDegreeService {

	EducationalDegree retrieveEducationalDegreeById(final Integer educationalDegreeId);

	EducationalDegree postEducationalDegree(EducationalDegree educationalDegree);

	Boolean deleteEducationalDegreeById(Integer degreeId);

	Boolean deleteEducationalDegreeByName(String degreeName);

	EducationalDegree updateEducationalDegree(EducationalDegree educationalDegree);

	List<EducationalDegree> findAll();

	Boolean changeStatusEducationalDegree(EducationalDegree educationalDegree);

}

package com.troika.dao;

import java.util.List;

import com.troika.domain.model.EducationalDegree;

public interface EducationalDegreeDao {

	EducationalDegree retireveEducationalDegreeById(Integer educationalDegreeId);

	EducationalDegree retireveEducationalDegreeByName(String degreeName);

	EducationalDegree createEducationalDegree(EducationalDegree educationalDegree);

	void deleteEducationalDegree(EducationalDegree educationalDegreeToDelete);

	EducationalDegree updateEducationalDegree(EducationalDegree educationalDegreeToUpdate);

	List<EducationalDegree> findAll();
}

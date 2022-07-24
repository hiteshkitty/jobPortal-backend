package com.troika.services;

import java.util.List;

import com.troika.domain.model.BusinessStream;

public interface BusinessStreamService {

	BusinessStream retrieveBusinessStreamById(final Integer companyId);

	BusinessStream registerBusinessStream(BusinessStream company);

	BusinessStream deleteBusinessStreamById(Integer compId);

	BusinessStream updateBusinessStream(BusinessStream businessStream);

	List<BusinessStream> findAll();
}

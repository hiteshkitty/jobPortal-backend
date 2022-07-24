package com.troika.dao;

import java.util.List;

import com.troika.domain.model.BusinessStream;

public interface BusinessStreamDao {

	BusinessStream retireveBusinessStreamById(Integer businessStreamId);

	BusinessStream retrieveBusinessStreamByName(String businessStreamName);

	BusinessStream createBusinessStream(BusinessStream businessStream);

	void deleteBusinessStream(BusinessStream businessStreamToDelete);

	BusinessStream updateBusinessStream(BusinessStream businessStreamToUpdate);

	List<BusinessStream> findAll();
}

package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troika.dao.BusinessStreamDao;
import com.troika.domain.model.BusinessStream;
import com.troika.repo.BusinessStreamRepo;

@Component
public class BusinessStreamDaoImpl implements BusinessStreamDao {

	private static final Logger LOGGER = Logger.getLogger(BusinessStreamDaoImpl.class);

	@Autowired
	private BusinessStreamRepo businessStreamRepo;

	@Override
	public BusinessStream retireveBusinessStreamById(Integer businessStreamId) {

		BusinessStream businessStream = businessStreamRepo.findOne(businessStreamId);

		LOGGER.debug("BusinessStream: " + businessStream);

		LOGGER.debug("BusinessName: " + businessStream.getBusinessStreamName());

		return businessStream;

	}

	@Override
	public BusinessStream retrieveBusinessStreamByName(String businessStreamName) {

		LOGGER.debug("retrieving businessStream details using businessStreamName: " + businessStreamName);

		BusinessStream businessStream = null;

		businessStream = businessStreamRepo.findByBusinessStreamName(businessStreamName);

		LOGGER.debug("retrieved BusinessStreamDetails for businessStreamName: " + businessStreamName + " is "
				+ businessStream);

		return businessStream;

	}

	@Override
	public BusinessStream createBusinessStream(BusinessStream businessStream) {

		LOGGER.debug("creating businessStream details: " + businessStream);

		BusinessStream businessStreamCreated = businessStreamRepo.save(businessStream);

		LOGGER.debug("created BusinessStreamDetails: " + businessStreamCreated);

		return businessStreamCreated;

	}

	@Override
	public void deleteBusinessStream(BusinessStream businessStreamToDelete) {

		LOGGER.debug("deleting businessStream details: " + businessStreamToDelete);

		businessStreamRepo.delete(businessStreamToDelete);

		LOGGER.debug("created BusinessStreamDetails: " + businessStreamToDelete);

	}

	@Override
	public BusinessStream updateBusinessStream(BusinessStream businessStreamToUpdate) {

		LOGGER.debug("updating businessStream details: " + businessStreamToUpdate);

		BusinessStream businessStreamUpdated = businessStreamRepo.save(businessStreamToUpdate);

		LOGGER.debug("businessStreamUpdated BusinessStreamDetails: " + businessStreamUpdated);

		return businessStreamUpdated;
	}

	@Override
	public List<BusinessStream> findAll() {

		List<BusinessStream> businessStream = null;

		LOGGER.debug("fetching all BusinessStream");
		
		businessStream = (List)businessStreamRepo.findAll();
		
		LOGGER.debug("fetch all BusinessStream: ");

		return businessStream;
	}

}

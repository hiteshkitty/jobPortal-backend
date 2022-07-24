package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.dao.BusinessStreamDao;
import com.troika.domain.model.BusinessStream;
import com.troika.helper.JobPortalHelper;
import com.troika.services.BusinessStreamService;

@Service
public class BusinessStreamServiceImpl implements BusinessStreamService {

	private static final Logger LOGGER = Logger.getLogger(BusinessStreamServiceImpl.class);

	@Autowired
	private BusinessStreamDao businessStreamDao;

	@Override
	public BusinessStream retrieveBusinessStreamById(Integer businessStreamId) {

		LOGGER.debug("finding businessStream details using businessStreamId: " + businessStreamId);

		BusinessStream businessStream = businessStreamDao.retireveBusinessStreamById(businessStreamId);

		return businessStream;
	}

	@Override
	public BusinessStream registerBusinessStream(BusinessStream businessStream) {

		LOGGER.debug("creating businessStream with details: " + businessStream);

		BusinessStream registerBusinessStream = businessStreamDao.createBusinessStream(businessStream);

		LOGGER.debug("registerBusinessStream: " + registerBusinessStream);

		return registerBusinessStream;
	}

	@Override
	public BusinessStream deleteBusinessStreamById(Integer compId) {

		LOGGER.debug("deleting businessStream with compId: " + compId);

		// check whether businessStream exists, if then retrieve it and delete
		// it.

		BusinessStream businessStreamToDelete = businessStreamDao.retireveBusinessStreamById(compId);

		if (businessStreamToDelete != null) {

			if (businessStreamToDelete.getId() != 0) {

				LOGGER.debug("deleting businessStream : ");

				businessStreamDao.deleteBusinessStream(businessStreamToDelete);

			}
		}

		return null;
	}

	@Override
	public BusinessStream updateBusinessStream(BusinessStream businessStream) {

		LOGGER.debug("Updating businessStream with businessStream: " + businessStream);

		// check whether businessStream exists, if then retrieve it and update
		// it.

		BusinessStream businessStreamToUpdate = businessStreamDao.retireveBusinessStreamById(businessStream.getId());

		if (businessStreamToUpdate != null) {

			if (businessStreamToUpdate.getId() != 0) {

				LOGGER.debug("updating businessStream : ");

				businessStreamToUpdate = JobPortalHelper.copyBusinessStream(businessStream, businessStreamToUpdate);

				businessStreamDao.updateBusinessStream(businessStreamToUpdate);

			}
		}

		return null;
	}

	@Override
	public List<BusinessStream> findAll() {
		return businessStreamDao.findAll();
	}

}

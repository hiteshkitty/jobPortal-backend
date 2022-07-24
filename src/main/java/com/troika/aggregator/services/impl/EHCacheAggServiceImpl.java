package com.troika.aggregator.services.impl;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.troika.aggregator.services.EHCacheAggService;
import com.troika.domain.view.EHCacheRequest;
import com.troika.domain.view.EHCacheResponse;

/**
 * TODO
 */
public class EHCacheAggServiceImpl extends BaseCacheServiceImpl implements EHCacheAggService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EHCacheAggServiceImpl.class);

	/**
	 * @param request
	 * @return
	 */
	@Override
	@RolesAllowed("Admin")
	public EHCacheResponse removeCacheData(EHCacheRequest request) {

		LOGGER.debug("Remove Cache Data Request Key: " + request);

		String msisdnToken = request.getToken();
		EHCacheResponse ehCacheResponse = new EHCacheResponse();
		Boolean result = null;// ;getCachingService().removeFromCache(msisdnToken,
								// AccountOverviewResponse.class);
		// ehCacheResponse.setIsSucessful(result);

		// Delete the record from the database.

		return ehCacheResponse;
	}

}

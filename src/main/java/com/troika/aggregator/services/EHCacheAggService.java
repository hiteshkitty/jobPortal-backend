package com.troika.aggregator.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.troika.domain.view.EHCacheRequest;
import com.troika.domain.view.EHCacheResponse;

@Path("ehCacheService")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public interface EHCacheAggService {

	/**
	 * RESTful service endpoint for SimpleEHCacheService for HTTP POST method.
	 * 
	 * @param request reference of BarUnbarRequest
	 * @return the PlanSettingsAggregatorResponse or an Exception
	 */
	@POST
	@Path("/removeCache/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	EHCacheResponse removeCacheData(EHCacheRequest request);
}

package com.troika.aggregator.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.troika.domain.view.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/retrievemasterdata")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Api(value = "Master data for swagger", produces = "application/json")
public interface MasterDataAggService {

	@GET
	@ApiOperation(value = "Retrieves Master data", response = RestResponse.class)
	RestResponse retrieveMasterData();

}

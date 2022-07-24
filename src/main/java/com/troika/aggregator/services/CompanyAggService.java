package com.troika.aggregator.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.troika.domain.view.CompanyProfile;
import com.troika.domain.view.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/companyprofile")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Api(value = "Company", produces = "application/json")
public interface CompanyAggService {

	
	@CrossOrigin
	@GET
	@Path("/companyid/{companyId}")
//	@ApiOperation(value = "Retrieves Company by id.", response = RestResponse.class)
	RestResponse retrieveCompanyProfileById(@PathParam("companyId") String compnanyId);

	@CrossOrigin
	@POST
	@ApiOperation(value = "Registers Company.", response = RestResponse.class)
	RestResponse registerCompanyProfile(CompanyProfile companyProfile);

	@CrossOrigin
	@DELETE
	@Path("/companyid/{companyId}")
	@ApiOperation(value = "Deletes Company by id.", response = RestResponse.class)
	RestResponse deleteCompanyProfile(@PathParam("companyId") String compnanyId);

	@CrossOrigin
	@PUT
	@ApiOperation(value = "Updates Company.", response = RestResponse.class)
	RestResponse updateCompanyProfile(CompanyProfile companyProfile);
}

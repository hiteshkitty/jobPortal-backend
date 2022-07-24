package com.troika.aggregator.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.troika.domain.view.LoginRequest;
import com.troika.domain.view.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/auth/")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Api(value = "Login", produces = "application/json")
public interface LoginAggService {

	@CrossOrigin
	@POST
	@Path("/login")
	@ApiOperation(value = "Login UserLog.", response = RestResponse.class)
	RestResponse login(@Context HttpHeaders headers);

	@CrossOrigin
	@POST
	@Path("/loginrequest")
	@ApiOperation(value = "Login UserLog.", response = RestResponse.class)
	RestResponse loginRequest(LoginRequest loginRequest);
	
	@CrossOrigin
	@DELETE
	@Path("/logout")
	@ApiOperation(value = "Logout for user", response = RestResponse.class)
	RestResponse logout(@Context HttpHeaders headers);
}

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

import com.troika.domain.view.JobApplyRequest;
import com.troika.domain.view.JobRequest;
import com.troika.domain.view.RestResponse;
import com.troika.domain.view.SearchRequest;

import io.swagger.annotations.ApiOperation;

@Path("/job")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })

public interface JobAggService {

	@GET
	@Path("/searchjob/jobid/{jobid}")
	@ApiOperation(value = "Retrieves Job by id.", response = RestResponse.class)
	RestResponse searchJob(@PathParam("jobid") String jobId);

	@GET
	@Path("/searchprofiles/jobid/{jobid}")
	@ApiOperation(value = "Search profiles by job id.", response = RestResponse.class)
	RestResponse searchProfiles(@PathParam("jobid") String jobId);

	@GET
	@Path("/retrievejobs")
	@ApiOperation(value = "Retrieves 4 recent Job.", response = RestResponse.class)
	RestResponse retrieveRecentJobs();

	@GET
	@Path("/searchappliedjob/emailid/{emailid}")
	@ApiOperation(value = "Searches jobs applied by user by emailid.", response = RestResponse.class)
	RestResponse searchAppliedJobs(@PathParam("emailid") String emailId);

	@POST
	@Path("/searchjob")
	@ApiOperation(value = "Searches job using search request.", response = RestResponse.class)
	RestResponse searchJob(SearchRequest request);

	@POST
	@ApiOperation(value = "Creates Job.", response = RestResponse.class)
	RestResponse createJob(JobRequest request);

	@DELETE
	@Path("/jobid/{jobId}")
	@ApiOperation(value = "Deletes Job by id.", response = RestResponse.class)
	RestResponse deleteJob(@PathParam("jobId") Integer jobId);

	@PUT
	@ApiOperation(value = "Updates Job by id.", response = RestResponse.class)
	RestResponse updateJob(JobRequest request);

	@POST
	@Path("/applyjob")
	@ApiOperation(value = "Apply Job by id.", response = RestResponse.class)
	RestResponse applyJob(JobApplyRequest request) throws Exception;

}

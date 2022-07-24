package com.troika.aggregator.services;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.troika.domain.view.RestResponse;
import com.troika.domain.view.SearchRequest;
import com.troika.domain.view.UserProfile;
import com.troika.domain.view.UserRoleRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Path("/userprofile")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Api(value = "User", produces = "application/json")
public interface UserAggService {

	@GET
	@CrossOrigin
	@Path("/emailid/{emailid}")
	@ApiOperation(value = "Retrieves user profile by emailid.", response = RestResponse.class)
	RestResponse retrieveUserProfileByEmailId(@PathParam("emailid") String emailId);

	@POST
	@ApiOperation(value = "Registers User Profile.", response = RestResponse.class)
	RestResponse registerUserProfile(UserProfile userProfile);

	@POST
	@CrossOrigin
	@Path("/registerrecruiter")
	@ApiOperation(value = "Registers Recruiter Profile.", response = RestResponse.class)
	RestResponse registerRecruiterProfile(UserProfile userProfile);

	@DELETE
	@CrossOrigin
	@Path("/emailid/{emailid}")
	@ApiOperation(value = "Deletes User Profile", response = RestResponse.class)
	RestResponse deleteUserProfile(@PathParam("emailid") String emailId);

	@PUT
	@ApiOperation(value = "Update User Profile", response = RestResponse.class)
	RestResponse updateUserProfile(UserProfile userProfile);

	@GET
	@CrossOrigin
	@Path("/jobsposted/emailid/{emailid}")
	@ApiOperation(value = "Retrieves Jobs posted by user using emailid", response = RestResponse.class)
	RestResponse findAllJobsPosted(@PathParam("emailid") String emailId);

	@POST
	@CrossOrigin
	@Path("/upload/resume/")
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "Uploads Resume", response = RestResponse.class)
	public RestResponse uploadResume(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("userProfile") FormDataBodyPart userProfile);

	@GET
	@CrossOrigin
	@Path("/userrole/emailid/{emailid}")
	@ApiOperation(value = "Retrieves user profile by emailid.", response = RestResponse.class)
	RestResponse retrieveUserProfileRole(@PathParam("emailid") String emailId);

	@PUT
	@CrossOrigin
	@Path("/updaterole")
	@ApiOperation(value = "Update User Profile", response = RestResponse.class)
	RestResponse updateUserProfileRole(UserRoleRequest request);

	@POST
	@CrossOrigin
	@Path("/searchprofiles")
	@ApiOperation(value = "Searches profiles using search request.", response = RestResponse.class)
	RestResponse searchProfiles(SearchRequest request);

	@GET
	@CrossOrigin
	@Path("/downloadresume/emailid/{emailid}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadFile(@PathParam("emailid") String emailId) throws Exception;

}

package com.troika.aggregator.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.troika.aggregator.services.UserAggService;
import com.troika.domain.model.Job;
import com.troika.domain.model.RoleEnum;
import com.troika.domain.model.SeekerProfile;
import com.troika.domain.model.UserAccount;
import com.troika.domain.view.JobListResponse;
import com.troika.domain.view.JobResponse;
import com.troika.domain.view.RestResponse;
import com.troika.domain.view.SearchRequest;
import com.troika.domain.view.StatusEnum;
import com.troika.domain.view.UserInformation;
import com.troika.domain.view.UserProfile;
import com.troika.domain.view.UserProfileListResponse;
import com.troika.domain.view.UserProfileResponse;
import com.troika.domain.view.UserRoleRequest;
import com.troika.helper.JobPortalHelper;
import com.troika.helper.TroikaConstants;
import com.troika.helper.TroikaThreadLocal;
import com.troika.services.UserAccountService;

@Service
public class UserAggServiceImpl extends BaseCacheServiceImpl implements UserAggService {

	private static final Logger LOGGER = Logger.getLogger(UserAggServiceImpl.class);

	@Autowired

	private UserAccountService userAccountService;

	@Autowired
	private JobPortalHelper jobPortalHelper;

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.USER, TroikaConstants.RECRUITER })
	public RestResponse retrieveUserProfileByEmailId(String emailId) {

		LOGGER.debug("fetching userAccount using userId: " + emailId);

		if (emailId != null && emailId.equals("0000")) {

			emailId = jobPortalHelper.getEmailIdFromRequest(emailId);

		}

		LOGGER.debug("emailId to search details: " + emailId);

		UserAccount retrievedUserAccount = userAccountService.retrieveUserAccountByEmailId(emailId);

		UserProfile fetchedUserProfile = jobPortalHelper.convertToUserProfile(retrievedUserAccount);

		RestResponse response = JobPortalHelper.createRestResponse(fetchedUserProfile, StatusEnum.SUCCESS,
				"User Account fetched successfully.");

		JobPortalHelper.logPayload(response);

		return response;

	}

	@Override
	@PermitAll
	public RestResponse registerUserProfile(UserProfile userProfile) {

		LOGGER.debug("creating userProfile using: " + userProfile);

		UserProfile userAccountResponse = new UserProfile();

		UserAccount userAccount = jobPortalHelper.convertToUserAccount(userProfile);

		RestResponse response = null;

		try {

			UserAccount createdUserAccount = userAccountService.registerUserAccount(userAccount);

			userAccountResponse = jobPortalHelper.convertToUserProfile(createdUserAccount);

			response = JobPortalHelper.createRestResponse(userAccountResponse, StatusEnum.SUCCESS,
					"User Profile created successfully.");

		} catch (DataIntegrityViolationException ex) {

			ex.printStackTrace();

			response = JobPortalHelper.createRestResponse("User is already registered with same emailId.",
					StatusEnum.FAILURE, ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();

			response = JobPortalHelper.createRestResponse("Not able to create user.", StatusEnum.FAILURE,
					ex.getMessage());

		}

		JobPortalHelper.logPayload(response);

		return response;

	}

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.USER, TroikaConstants.RECRUITER })
	public RestResponse deleteUserProfile(String emailId) {

		LOGGER.debug("deleting user with id: " + emailId);

		if (emailId == null || emailId.length() == 0) {

			return JobPortalHelper.createRestResponse("Please provide valid emailId.", StatusEnum.SUCCESS,
					"Please provide valid emailId..");

		}

		UserInformation userInfo = (UserInformation) TroikaThreadLocal.get();

		RestResponse response = null;

		if (userInfo == null) {

			response = JobPortalHelper.createRestResponse("Error while deleting user.", StatusEnum.SUCCESS,
					"Error while deleting user.");

		} else {

			String requestorRole = userInfo.getRole().name();

			String requestorEmailId = userInfo.getEmailId();

			if (requestorRole != null && requestorEmailId != null) {

				if (requestorRole.equalsIgnoreCase(RoleEnum.ADMIN.name())
						|| (requestorEmailId.equalsIgnoreCase(emailId))) {

					LOGGER.debug("Deleting user");

					userAccountService.deleteUserAccountByEmailId(emailId);

					response = JobPortalHelper.createRestResponse("User deleted successfully.", StatusEnum.SUCCESS,
							"User deleted successfully.");

				} else {

					LOGGER.debug(
							"not allowing user with emailId: " + requestorEmailId + " is trying to delete: " + emailId);

					response = JobPortalHelper.createRestResponse("You are not allowed to delete this user.",
							StatusEnum.SUCCESS, "You are not allowed to delete this user.");

				}
			}

		}

		JobPortalHelper.logPayload(response);

		return response;
	}

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.USER, TroikaConstants.RECRUITER })
	public RestResponse updateUserProfile(UserProfile userProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.USER, TroikaConstants.RECRUITER })
	public RestResponse findAllJobsPosted(String emailId) {

		LOGGER.debug("fetching jobs posted by userId: " + emailId);

		// if (emailId != null && emailId.equals("0000")) {

		emailId = jobPortalHelper.getEmailIdFromRequest(emailId);

		LOGGER.debug("fetching jobs posted updated by userId: " + emailId);

		// }

		UserInformation userInfo = (UserInformation) TroikaThreadLocal.get();

		List<Job> jobsPosted = new ArrayList<>();

		if (userInfo != null) {

			String role = userInfo.getRole().name();

			if (role.equalsIgnoreCase(RoleEnum.ADMIN.name())) {

				jobsPosted = userAccountService.retrieveAllJobs();

			} else {

				LOGGER.debug("findAllJobsPosted emailId: " + emailId);

				jobsPosted = userAccountService.retrieveJobsPostedByEmailId(emailId);

			}

		}

		List<JobResponse> jobResponseList = jobPortalHelper.converJobList(jobsPosted);

		JobListResponse finalResponse = new JobListResponse();

		finalResponse.setJobList(jobResponseList);

		RestResponse response = JobPortalHelper.createRestResponse(jobResponseList, StatusEnum.SUCCESS,
				"All jobs posted by user fetched successfully.");

		JobPortalHelper.logPayload(response);

		return response;

	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse updateUserProfileRole(UserRoleRequest request) {

		LOGGER.debug("updating role for user: " + request);

		RestResponse response = null;

		if (request.getEmailId() == null || request.getRole() == null) {

			LOGGER.debug("emailId or role can't be null");

			LOGGER.error("User doesn't exist for user role: " + request);

			response = JobPortalHelper.createRestResponse("emailId or role can't be null", StatusEnum.FAILURE,
					"emailId or role can't be null");

		} else {

			try {

				RoleEnum.valueOf(request.getRole());

			} catch (IllegalArgumentException ex) {

				LOGGER.error("Invalid user role: " + request);

				response = JobPortalHelper.createRestResponse("Invalid user role: " + request.getRole(),
						StatusEnum.FAILURE, "Invalid user role: " + request.getRole());
			}
		}

		if (response == null) {

			UserAccount userAccount = userAccountService.updateUserRole(request);

			if (userAccount == null) {

				LOGGER.error("User doesn't exist for user role: " + request.getEmailId());

				response = JobPortalHelper.createRestResponse(
						"User doesn't exist for user role: " + request.getEmailId(), StatusEnum.FAILURE,
						"User doesn't exist for user role: " + request.getEmailId());

			} else {
				
//				final String recruiterEmailId = userAccount.getEmailId();
//
//				final String recruiterFirstName = userAccount.getFirstName();
//
//				final String recruiterLastName = userAccount.getLastName();
//				
//				ExecutorService execService = Executors.newFixedThreadPool(1);
//
//				execService.execute(new Runnable() {
//
//					public void run() {
//
//						LOGGER.info("Sending email to Recruiter for role updation.");
//
//						jobPortalHelper.sendRecruiterRoleUpdateEmail(recruiterEmailId, recruiterFirstName, recruiterLastName);
//
//					}
//
//				});
//				execService.shutdown();
				response = JobPortalHelper.createRestResponse("Role updated successfully.", StatusEnum.SUCCESS,
						"Role updated successfully.");

			}
		}

		JobPortalHelper.logPayload(response);

		return response;

	}

	@Override
	@PermitAll
	public RestResponse retrieveUserProfileRole(String emailId) {

		LOGGER.debug("retrieving role information for emailId: " + emailId);

		UserRoleRequest roleInformation = new UserRoleRequest();

		UserAccount userAccount = userAccountService.retrieveUserAccountBasicInfo(emailId);

		RestResponse response = null;

		if (userAccount != null) {

			LOGGER.debug("setting roleInformation");

			roleInformation.setEmailId(userAccount.getEmailId());

			if (userAccount.getRole() != null) {
				roleInformation.setRole(userAccount.getRole().name());

			}
			response = JobPortalHelper.createRestResponse(roleInformation, StatusEnum.SUCCESS,
					"Role information retrieved successfully.");

		} else {

			LOGGER.debug("couldn't retrieve user Information so couldn't set userInformation");

			response = JobPortalHelper.createRestResponse(roleInformation, StatusEnum.FAILURE,
					"Role information retrieved su.");

		}

		JobPortalHelper.logPayload(response);

		return response;

	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			out.flush();

			File file = new File("C:\\uploaded\\Address.java");

			byte[] bFile = new byte[(int) file.length()];

			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				// convert file into array of bytes
				fileInputStream.read(bFile);
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public byte[] convertToBytes(InputStream uploadedInputStream) throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int read = 0;
		while ((read = uploadedInputStream.read(buffer, 0, buffer.length)) != -1) {
			baos.write(buffer, 0, read);
		}
		baos.flush();
		byte[] bFile = baos.toByteArray();

		return bFile;
	}

	@Override
	@PermitAll
	public RestResponse uploadResume(InputStream uploadedInputStream, FormDataContentDisposition fileDetail,
			FormDataBodyPart profile) {

		LOGGER.info("inside upload Resume");

		if (profile != null) {

			LOGGER.debug(profile.getEntity());

			// LOGGER.debug(profile.getHeaders());

		}
		profile.setMediaType(MediaType.APPLICATION_JSON_TYPE);

		UserProfile userProfile = profile.getValueAs(UserProfile.class);

		LOGGER.debug(userProfile);

		// writeToFile(uploadedInputStream, uploadedFileLocation)
		byte[] file = null;

		try {
			file = convertToBytes(uploadedInputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LOGGER.debug(userProfile);

		LOGGER.debug("creating userProfile using: " + userProfile);

		UserProfile userAccountResponse = new UserProfile();

		UserAccount userAccount = jobPortalHelper.convertToUserAccount(userProfile, file);

		RestResponse response = null;

		try {

			UserAccount createdUserAccount = userAccountService.registerUserAccount(userAccount);

			userAccountResponse = jobPortalHelper.convertToUserProfile(createdUserAccount);

//			final String recipientEmailId = userAccountResponse.getEmail();
//
//			final String recipientFirstName = userAccountResponse.getFirstName();
//
//			final String recipientLastName = userAccountResponse.getLastName();
//
//			ExecutorService execService = Executors.newFixedThreadPool(1);
//
//			execService.execute(new Runnable() {
//
//				public void run() {
//
//					LOGGER.info("sending welcome email to: " + recipientEmailId);
//
//					jobPortalHelper.sendWelcomeEmail(recipientEmailId, recipientFirstName, recipientLastName);
//
//				}
//
//			});
//
//			execService.shutdown();

			response = JobPortalHelper.createRestResponse("User profile created successfully", StatusEnum.SUCCESS,
					"User Profile created successfully.");

		} catch (DataIntegrityViolationException ex) {

			ex.printStackTrace();

			response = JobPortalHelper.createRestResponse("User is already registered with same emailId.",
					StatusEnum.FAILURE, "User is already registered with same emailId.");

		} catch (Exception ex) {

			ex.printStackTrace();

			response = JobPortalHelper.createRestResponse("Not able to create user.", StatusEnum.FAILURE,
					ex.getMessage());

		}

		JobPortalHelper.logPayload(response);

		return response;

	}

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.RECRUITER })
	public RestResponse searchProfiles(SearchRequest request) {

		LOGGER.debug("searchProfiles for request: " + request);

		List<SeekerProfile> seekerProfileList = userAccountService.retrieveProfiles(request);

		LOGGER.debug("size of seekerProfile list: " + seekerProfileList.size());

		List<UserProfileResponse> userResponseList = jobPortalHelper.convertSeekerProfileList(seekerProfileList);

		UserProfileListResponse finalResponse = new UserProfileListResponse();

		finalResponse.setUserProfileList(userResponseList);

		RestResponse response = JobPortalHelper.createRestResponse(finalResponse, StatusEnum.SUCCESS,
				"Search profiles completed successfully.");

		JobPortalHelper.logPayload(response);

		return response;

	}

	@Override
	@PermitAll
	public RestResponse registerRecruiterProfile(UserProfile recruiterProfile) {

		LOGGER.debug("creating recruiterProfile using: " + recruiterProfile);

		UserProfile userAccountResponse = new UserProfile();

		RestResponse response = null;

		try {

			UserAccount userAccountExist = userAccountService.retrieveUserAccountByEmailId(recruiterProfile.getEmail());

			if (userAccountExist != null) {

				throw new DataIntegrityViolationException("Recruiter Already registered in the system.");

			}

			UserAccount userAccount = jobPortalHelper.convertToRecruiterAccount(recruiterProfile);

			UserAccount createdUserAccount = userAccountService.registerUserAccount(userAccount);

			userAccountResponse = jobPortalHelper.convertToUserProfile(createdUserAccount);

//			final String recruiterEmailId = userAccountResponse.getEmail();
//
//			final String recruiterFirstName = userAccountResponse.getFirstName();
//
//			final String recruiterLastName = userAccountResponse.getLastName();
//
//			ExecutorService execService = Executors.newFixedThreadPool(1);
//
//			execService.execute(new Runnable() {
//
//				public void run() {
//
//					LOGGER.info("Sending email to Admin and new recruiter");
//
//					jobPortalHelper.sendRecruiterWelcomeEmail(recruiterEmailId, recruiterFirstName, recruiterLastName);
//
//					jobPortalHelper.sendRecruiterCreationToAdmin(recruiterEmailId, recruiterFirstName,
//							recruiterLastName);
//
//				}
//
//			});
//
//			execService.shutdown();

			response = JobPortalHelper.createRestResponse(userAccountResponse, StatusEnum.SUCCESS,
					"Recruiter Profile created successfully.");

		} catch (DataIntegrityViolationException ex) {

			ex.printStackTrace();

			response = JobPortalHelper.createRestResponse("Recruiter is already registered with same emailId.",
					StatusEnum.FAILURE, ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();

			response = JobPortalHelper.createRestResponse("Not able to create recruiter.", StatusEnum.FAILURE,
					ex.getMessage());

		}

		JobPortalHelper.logPayload(response);

		return response;

	}

	@Override
	@PermitAll
	public Response downloadFile(String emailId) throws Exception {

		byte[] resume = null;

		String fileName = "resume.doc";

		ResponseBuilder response = null;

		try {

			if (emailId != null && !emailId.isEmpty()) {

				UserAccount userAccount = userAccountService.retrieveUserAccountByEmailId(emailId);

				if (userAccount != null && userAccount.getSeekerProfile() != null
						&& userAccount.getSeekerProfile().getResume() != null) {

					LOGGER.debug("got resume");

					resume = userAccount.getSeekerProfile().getResume();

					fileName = userAccount.getFirstName() + "_" + userAccount.getLastName() + ".docx";

					response = Response.ok(resume);

					response.header("Content-Disposition", "attachment; filename='" + fileName + "'");

				} else {

					if (userAccount == null) {

						throw new Exception(
								"Couldn't not download resume, as user doesn't exist with this emailId: " + emailId);
						// response = Response.noContent().entity(
						// "Couldn't not download resume, as user doesn't exist
						// with this emailId: " + emailId);

					} else {

						throw new Exception("Couldn't not download resume, may be it's corrupt or doesn't exist");

						// response = Response.noContent()
						// .entity("Couldn't not download resume, may be it's
						// corrupt or doesn't exist");
					}
				}
			} else {

				throw new Exception("Please provide the valid emailId to download resume");
				// response = Response.noContent().entity("Please provide the
				// valid emailId to download resume");
			}
		} catch (Exception ex) {

			// throw new Exception("Please provide the valid emailId to download
			// resume");

			response = Response.noContent();

		}

		return response.build();

	}

}

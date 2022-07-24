package com.troika.aggregator.services.impl;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.troika.aggregator.services.JobAggService;
import com.troika.domain.model.Job;
import com.troika.domain.view.JobApplyRequest;
import com.troika.domain.view.JobListResponse;
import com.troika.domain.view.JobRequest;
import com.troika.domain.view.JobResponse;
import com.troika.domain.view.RestResponse;
import com.troika.domain.view.SearchRequest;
import com.troika.domain.view.SeekerListResponse;
import com.troika.domain.view.StatusEnum;
import com.troika.domain.view.UserInformation;
import com.troika.helper.JobPortalHelper;
import com.troika.helper.TroikaConstants;
import com.troika.helper.TroikaThreadLocal;
import com.troika.services.JobService;

@Service
public class JobAggServiceImpl extends BaseCacheServiceImpl implements JobAggService {

	private static final Logger LOGGER = Logger.getLogger(JobAggServiceImpl.class);

	@Autowired
	private JobService jobService;

	@Autowired
	private JobPortalHelper jobPortalHelper;

	@Override
	@PermitAll
	public RestResponse searchJob(SearchRequest request) {

		LOGGER.debug("searchJobs for request: " + request);

		int experience = request.getExperience();

		String location = request.getLocation();

		List<Integer> skills = request.getSkillSetList();

		List<Job> jobs = jobService.retrieveJobs(request);

		List<JobResponse> jobResponseList = jobPortalHelper.converJobList(jobs);

		JobListResponse finalResponse = new JobListResponse();

		finalResponse.setJobList(jobResponseList);

		RestResponse response = JobPortalHelper.createRestResponse(finalResponse, StatusEnum.SUCCESS,
				"Job search completed successfully.");

		JobPortalHelper.logPayload(response);

		return response;

	}

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.RECRUITER })
	public RestResponse createJob(JobRequest jobRequest) {

		LOGGER.debug("creating Job  using: " + jobRequest);

		Job job = jobPortalHelper.convertJobRequest(jobRequest);

		Job createdJob = jobService.postJob(job);

		JobResponse jobResponse = jobPortalHelper.convertJobToJobResponse(createdJob);

		RestResponse response = JobPortalHelper.createRestResponse(jobResponse, StatusEnum.SUCCESS,
				"Job created successfully.");

		JobPortalHelper.logPayload(response);

		return response;
	}

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.RECRUITER })
	public RestResponse deleteJob(Integer jobId) {

		LOGGER.debug("deleting job using jobId: " + jobId);

		String response = jobService.deleteJobById(jobId);

		RestResponse restResponse = JobPortalHelper.createRestResponse(response, StatusEnum.SUCCESS,
				response);

		return restResponse;

	}

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.RECRUITER })
	public RestResponse updateJob(JobRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.USER, TroikaConstants.RECRUITER })
	public RestResponse searchJob(String id) {

		LOGGER.debug("finding job using jobId: " + id);

		Integer jobId = Integer.valueOf(id);

		Job jobResponse = jobService.retrieveJobById(jobId);

		JobResponse response = jobPortalHelper.convertJobToJobResponse(jobResponse);

		RestResponse restResponse = JobPortalHelper.createRestResponse(response, StatusEnum.SUCCESS,
				"found job details successfully.");

		return restResponse;

	}

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.USER })
	public RestResponse applyJob(JobApplyRequest request) throws Exception {

		LOGGER.debug("Applying job with request: " + request);

		String jobAppliedByEmailId = null;

		UserInformation userInfo = (UserInformation) TroikaThreadLocal.get();

		if (userInfo != null) {
			String email = userInfo.getEmailId();

			if (email != null) {

				LOGGER.debug("fetched emailId from thread local: " + email);

				jobAppliedByEmailId = email;
			}

			if (jobAppliedByEmailId != null) {

				request.setEmailId(jobAppliedByEmailId);

			}
		}

		RestResponse response = null;

		try {

			jobService.applyJob(request);

			response = JobPortalHelper.createRestResponse("You have applied for job successfully.", StatusEnum.SUCCESS,
					"You have applied for job successfully.");

		} catch (DataIntegrityViolationException ex) {

			response = JobPortalHelper.createRestResponse("You have already applied for this job.", StatusEnum.FAILURE,
					"You have already applied for this job.");

		}

		JobPortalHelper.logPayload(response);

		return response;
	}

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.RECRUITER })
	public RestResponse searchProfiles(String jobId) {

		LOGGER.debug("search profiles for jobId: " + jobId);

		Integer jobPostId = null;

		SeekerListResponse seekerResponse = new SeekerListResponse();

		if (jobId != null && !StringUtils.isEmpty(jobId)) {

			jobPostId = Integer.valueOf(jobId);

			seekerResponse = jobPortalHelper.findProfilesByJobId(jobPostId);

		}

		RestResponse response = JobPortalHelper.createRestResponse(seekerResponse, StatusEnum.SUCCESS,
				"Seeker list retrieved successfully.");

		JobPortalHelper.logPayload(response);

		return response;

	}

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.USER, TroikaConstants.RECRUITER })
	public RestResponse searchAppliedJobs(String emailId) {

		LOGGER.debug("search applied jobs for userId: " + emailId);

		JobListResponse jobList = new JobListResponse();

		if (emailId != null && !StringUtils.isEmpty(emailId)) {

			// if (emailId.equals("0000")) {

			emailId = jobPortalHelper.getEmailIdFromRequest(emailId);

			LOGGER.debug("search applied jobs updated emailId for userId: " + emailId);
			// }

			LOGGER.debug("searchAppliedJobs emailId: " + emailId);

			jobList = jobPortalHelper.findAppliedJobByEmailId(emailId);

		}

		RestResponse response = JobPortalHelper.createRestResponse(jobList, StatusEnum.SUCCESS,
				"Jobs applied by user list successfully retrieved.");

		JobPortalHelper.logPayload(response);

		return response;

	}

	@Override
	public RestResponse retrieveRecentJobs() {

		LOGGER.debug("Retrieve 4 recent jobs");

		List<Job> jobs = jobService.retrieveRecentJobs();

		List<JobResponse> jobResponseList = jobPortalHelper.converJobList(jobs);

		JobListResponse finalResponse = new JobListResponse();

		finalResponse.setJobList(jobResponseList);

		RestResponse response = JobPortalHelper.createRestResponse(finalResponse, StatusEnum.SUCCESS,
				"Recent jobs retrieved successfully.");

		JobPortalHelper.logPayload(response);

		return response;
	}

}

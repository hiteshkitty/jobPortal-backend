package com.troika.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.dao.JobDao;
import com.troika.dao.JobSkillSetDao;
import com.troika.domain.model.Job;
import com.troika.domain.model.JobPostActivity;
import com.troika.domain.model.JobSkillSet;
import com.troika.domain.model.UserAccount;
import com.troika.domain.view.JobApplyRequest;
import com.troika.domain.view.SearchRequest;
import com.troika.domain.view.UserInformation;
import com.troika.helper.TroikaThreadLocal;
import com.troika.services.JobPostActivityService;
import com.troika.services.JobService;
import com.troika.services.UserAccountService;

@Service
public class JobServiceImpl implements JobService {

	private static final Logger LOGGER = Logger.getLogger(JobServiceImpl.class);

	@Autowired
	private JobDao jobDao;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private JobPostActivityService jobPostActivityService;

	@Autowired
	private JobSkillSetDao jobSkillSetDao;

	@Override
	public Job retrieveJobById(Integer jobId) {

		Job job = jobDao.retireveJobById(jobId);

		return job;
	}

	@Override
	public Job postJob(Job job) {

		LOGGER.debug("creating job with details: " + job);

		Job createdJob = jobDao.createJob(job);

		return createdJob;

	}

	@Override
	public String deleteJobById(Integer jobId) {

		LOGGER.debug("deleting job with id: " + jobId);

		String response = "Job deleted successfully";

		Job jobToDelete = jobDao.retireveJobById(jobId);

		if (jobToDelete != null) {

			UserInformation userInfo = (UserInformation) TroikaThreadLocal.get();

			String jobCreatedBy = null;

			if (userInfo != null) {

				String recruiter = userInfo.getEmailId();

				if (jobToDelete.getUserAccount() != null) {

					jobCreatedBy = jobToDelete.getUserAccount().getEmailId();

					if (jobCreatedBy != null && (jobCreatedBy.equalsIgnoreCase(recruiter))) {

						jobDao.deleteJob(jobToDelete);

						response = "Job deleted successfully";

					} else {

						LOGGER.debug("Job to be deleted is created by " + jobCreatedBy + " but user who is deleting is "
								+ recruiter);

						response = "As you haven't created the job, so you can't delete it";

					}

				}
			} else {

				response = "Couldn't delete job.";
			}

		} else {

			response = "couldn't find job to delete for id: " + jobId;

		}

		LOGGER.debug("Delete job response: " + response);

		return response;

	}

	@Override
	public Job updateJob(Job job) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> retrieveJobs(SearchRequest jobRequest) {

		LOGGER.debug("jobRequest in job service" + jobRequest);

		List<Job> jobs = new ArrayList<>();

		List<Job> jobsToReturn = new ArrayList<>();

		List<Integer> jobRequestSkillSetList = jobRequest.getSkillSetList();

		List<JobSkillSet> jobList = null;

		LOGGER.debug("retrieving jobs from jobskillSet");

		if (jobRequestSkillSetList != null) {

			jobList = jobSkillSetDao.findJobsBySkillList(jobRequestSkillSetList);

		}

		LOGGER.info("called skillSet to fetch jobs");

		if (jobList != null && jobList.size() > 0) {

			LOGGER.debug("number of jobs for " + jobRequestSkillSetList + " are " + jobList.size());

			for (JobSkillSet jobSkillSet : jobList) {

				if (jobSkillSet.getJob() != null) {

					LOGGER.debug("adding job to list with Id: " + jobSkillSet.getJob().getJobId());

					jobs.add(jobSkillSet.getJob());

				}
			}

		}

		if (jobs != null && jobs.size() > 0) {

			LOGGER.debug("look out for experience");

			int experience = 0;

			for (Job job : jobs) {

				experience = job.getExperience();

				if (experience >= jobRequest.getExperience()) {

					jobsToReturn.add(job);

				}
			}

		}

		return jobsToReturn;

	}

	@Override
	public Job applyJob(JobApplyRequest applyRequest) {

		LOGGER.debug("apply request: " + applyRequest);

		Integer jobId = applyRequest.getJobId();

		String emailId = applyRequest.getEmailId();

		Job appliedJob = retrieveJobById(jobId);

		UserAccount userAccount = userAccountService.retrieveUserAccountByEmailId(emailId);

		JobPostActivity jobPostActivity = new JobPostActivity();

		jobPostActivity.setApplyDate(new Date());

		jobPostActivity.setJob(appliedJob);

		jobPostActivity.setUserAccount(userAccount);

		JobPostActivity jobPostResponse = jobPostActivityService.createJobPostActivity(jobPostActivity);

		LOGGER.debug("jobPostResponse: " + jobPostResponse);

		return null;
	}

	@Override
	public List<Job> retrieveRecentJobs() {

		LOGGER.debug("retrieving recent jobs");

		List<Job> jobList = new ArrayList<>();

		jobList = jobDao.retrieveRecentJobs();

		if (jobList != null) {

			LOGGER.debug("retrieved recent jobs size: " + jobList.size());

		}
		return jobList;
	}

}

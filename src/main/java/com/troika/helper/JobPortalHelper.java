package com.troika.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.troika.cache.CachingService;
import com.troika.domain.model.AuditReportData;
import com.troika.domain.model.BusinessStream;
import com.troika.domain.model.Company;
import com.troika.domain.model.Job;
import com.troika.domain.model.JobPostActivity;
import com.troika.domain.model.JobSkillSet;
import com.troika.domain.model.JobType;
import com.troika.domain.model.RoleEnum;
import com.troika.domain.model.SeekerProfile;
import com.troika.domain.model.SeekerSkillSet;
import com.troika.domain.model.SkillSet;
import com.troika.domain.model.UserAccount;
import com.troika.domain.model.UserType;
import com.troika.domain.view.EmailNotificationRequest;
import com.troika.domain.view.JobListResponse;
import com.troika.domain.view.JobRequest;
import com.troika.domain.view.JobResponse;
import com.troika.domain.view.NotificationTypeEnum;
import com.troika.domain.view.RestEntity;
import com.troika.domain.view.RestResponse;
import com.troika.domain.view.Seeker;
import com.troika.domain.view.SeekerListResponse;
import com.troika.domain.view.StatusEnum;
import com.troika.domain.view.UserInformation;
import com.troika.domain.view.UserProfile;
import com.troika.domain.view.UserProfileResponse;
import com.troika.services.AuditReportDataService;
import com.troika.services.CompanyService;
import com.troika.services.JobPostActivityService;
import com.troika.services.JobService;
import com.troika.services.JobTypeService;
import com.troika.services.SkillSetService;
import com.troika.services.UserAccountService;
import com.troika.services.UserTypeService;

@Component
public class JobPortalHelper {

	private static final Logger LOGGER = Logger.getLogger(JobPortalHelper.class);

	private static final String UPLOAD_FOLDER = "./files/";

	@Autowired
	private SkillSetService skillSetService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private JobTypeService jobTypeService;

	@Autowired
	private JobService jobService;

	@Autowired
	private UserTypeService userTypeService;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private JobPostActivityService jobPostActivityService;

	@Autowired
	private AuditReportDataService auditService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private CachingService cachingService;

	@Value("${linkServiceUrl}")
	private String linkServiceUrl;

	/**
	 * 
	 * @param t
	 * @param status
	 * @param description
	 * @return
	 */
	static public <T> RestResponse createRestResponse(T t, StatusEnum status, String description) {

		LOGGER.debug("creating restresponse");

		RestResponse response = new RestResponse();

		RestEntity<T> entity = new RestEntity<>();

		entity.setEntity(t);

		response.setResponseEntity(entity);

		response.getResultStatus().setStatus(status);

		response.getResultStatus().setDescription(description);

		return response;
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	static public Company copyCompany(Company from, Company to) {

		LOGGER.debug("copying company");

		to.setCompanyName(from.getCompanyName());

		to.setCompanyWebsiteUrl(from.getCompanyWebsiteUrl());

		to.setIsActive(from.getIsActive());

		return to;

	}

	/**
	 * 
	 * @param response
	 */
	public static void logPayload(final RestResponse response) {

		if (response != null && response.getResultStatus() != null) {

			LOGGER.debug("response status is: " + response.getResultStatus().getStatus());

			if (response.getResponseEntity() != null && response.getResponseEntity().getEntity() != null) {

				LOGGER.debug("response is : " + response.getResponseEntity().getEntity());
			}
		}

	}

	/**
	 * 
	 * @param businessStream
	 * @param businessStreamToUpdate
	 * @return
	 */
	public static BusinessStream copyBusinessStream(BusinessStream businessStream,
			BusinessStream businessStreamToUpdate) {

		return null;
	}

	/**
	 * 
	 * @param skillList
	 * @return
	 */
	public Set<SkillSet> fetchSkillSetsById(List<Integer> skillList) {

		Set<SkillSet> skills = new HashSet<>();

		LOGGER.debug("skills fetched: " + skills);

		return skills;

	}

	/**
	 * 
	 * @param companyId
	 * @return
	 */
	public Company fetchCompanyById(Integer companyId) {

		LOGGER.debug("fetching company details using companyId: " + companyId);

		Company company = companyService.retrieveCompanyById(companyId);

		LOGGER.debug("company fetched: " + company);

		return company;

	}

	/**
	 * 
	 * @param jobTypeId
	 * @return
	 */
	public JobType fetchJobTypeById(Integer jobTypeId) {

		LOGGER.debug("fetching jobType details using jobTypeId: " + jobTypeId);

		JobType jobType = jobTypeService.retrieveJobTypeById(jobTypeId);

		LOGGER.debug("jobType fetched: " + jobType);

		return jobType;

	}

	/**
	 * 
	 * @param jobRequest
	 * @param job
	 * @return
	 */
	public List<JobSkillSet> retrieveJobSkills(JobRequest jobRequest, Job job) {

		List<JobSkillSet> jobSkills = new ArrayList<>();

		List<Integer> skillIds = new ArrayList<>();

		skillIds.addAll(jobRequest.getKeySkills());

		List<SkillSet> skillSetList = skillSetService.findAllById(skillIds);

		for (SkillSet skillSet : skillSetList) {

			JobSkillSet jobSkillSet = new JobSkillSet();

			jobSkillSet.setSkillSet(skillSet);

			jobSkillSet.setJob(job);

			jobSkills.add(jobSkillSet);
		}

		return jobSkills;
	}

	/**
	 * 
	 * @param jobRequest
	 * @return
	 */
	public Job convertJobRequest(JobRequest jobRequest) {

		LOGGER.debug("Converting JobRequest to Job");

		Job job = new Job();

		job.setJobId(jobRequest.getJobId());

		if (jobRequest.getMemberType() != null) {

			job.setJobPostedByType(jobRequest.getMemberType().name());

		}

		job.setCity(jobRequest.getCity());

		job.setCreatedDate(new Date());

		job.setCompanyName(jobRequest.getCompanyName());

		job.setExperience(jobRequest.getExperience());

		job.setJobDescription(jobRequest.getJobDescription());

		job.setJobIndustry(jobRequest.getJobIndustry());

		job.setJobTitle(jobRequest.getJobTitle());

		job.setQualification(jobRequest.getEducation());

		List<JobSkillSet> jobSkills = retrieveJobSkills(jobRequest, job);

		job.setJobSkillSets(jobSkills);

		Integer companyId = 1;

		Company company = retrieveCompanyDetails(companyId);

		job.setCompany(company);

		String jobType = jobRequest.getJobType();

		JobType jobTypeDetails = retrieveJobTypeDetails(jobType);

		job.setJobType(jobTypeDetails);

		// String emailId = jobRequest.getEmailId();

		String emailId = getEmailIdFromRequest("0000");

		UserAccount userAccount = userAccountService.retrieveUserAccountByEmailId(emailId);

		job.setUserAccount(userAccount);

		return job;

	}

	public JobType retrieveJobTypeDetails(String jobType) {

		LOGGER.debug("fetching jobType details for jobTypeId: " + jobType);

		Integer jobTypeId = 0;

		if (jobTypeId == null || StringUtils.isEmpty(jobTypeId)) {

			LOGGER.debug("Job Type is null");

			return new JobType();

		} else {

			jobTypeId = Integer.valueOf(jobType);

		}

		JobType jobTypeDetails = fetchJobTypeById(jobTypeId);

		LOGGER.debug("fetched jobType details: " + jobTypeDetails);

		return jobTypeDetails;
	}

	public Company retrieveCompanyDetails(Integer companyId) {

		LOGGER.debug("fetching company details for companyId: " + companyId);

		Company company = fetchCompanyById(companyId);

		LOGGER.debug("fetched company details: " + companyId);

		return company;
	}

	/**
	 * 
	 * @param job
	 * @return
	 */
	public JobResponse convertJobToJobResponse(final Job job) {

		// LOGGER.debug("converting job to jobresponse: " + job);

		JobResponse response = new JobResponse();

		// LOGGER.debug("Converting JobRequest to Job");

		response.setJobId(job.getJobId());

		response.setCity(job.getCity());

		response.setCreatedDate(new Date());

		response.setExperience(job.getExperience());

		response.setJobDescription(job.getJobDescription());

		response.setJobIndustry(job.getJobIndustry());

		response.setJobTitle(job.getJobTitle());

		response.setQualification(job.getQualification());

		// Iterate job skills and set in response
		List<JobSkillSet> jobSkills = job.getJobSkillSets();

		if (jobSkills != null && jobSkills.size() > 0) {

			for (JobSkillSet jobSkillSet : jobSkills) {

				if (jobSkillSet.getSkillSet() != null) {

					Integer jobSkillSetId = jobSkillSet.getSkillSet().getId();

					String jobSkillSetName = jobSkillSet.getSkillSet().getSkillSetName();

					response.getJobSkillSets().put(jobSkillSetId, jobSkillSetName);

				}
			}
		}

		response.setCompanyName(job.getCompanyName());

		if (job.getJobType() != null) {

			// LOGGER.debug("Setting job type in jobresponse ");

			response.getJobType().put(job.getJobType().getId(), job.getJobType().getJobType());
		}

		// LOGGER.debug("converted Job to JobResponse: " + job + " jobResponse:
		// " + response);

		return response;

	}

	/**
	 * 
	 * @param jobs
	 * @return
	 */
	public List<JobResponse> converJobList(List<Job> jobs) {

		List<JobResponse> jobResponseList = new ArrayList<>();

		LOGGER.debug("Converting jobs to JobResponse list size: " + jobs.size());

		if (jobs != null && jobs.size() > 0) {

			JobResponse convertedResponse = null;

			for (Job job : jobs) {

				convertedResponse = convertJobToJobResponse(job);

				jobResponseList.add(convertedResponse);

			}
		}

		LOGGER.debug("Converted jobs to JobResponse list size: " + jobResponseList.size());

		return jobResponseList;
	}

	/**
	 * 
	 * @param userProfile
	 * @return
	 */
	public UserAccount convertToRecruiterAccount(UserProfile userProfile) {

		LOGGER.debug("converting to userAccount: " + userProfile);

		UserAccount userAccount = new UserAccount();

		userAccount.setContactNumber(userProfile.getContactNumber());

		userAccount.setDateOfBirth(new Date());

		userAccount.setFirstName(userProfile.getFirstName());

		userAccount.setLastName(userProfile.getLastName());

		userAccount.setEmailId(userProfile.getEmail());

		userAccount.setRole(RoleEnum.RECRUITER);

		userAccount.setIsAuthorizedToPostJob(Boolean.FALSE);

		userAccount.setPassword(userProfile.getPassword());

		userAccount.setRegistrationDate(new Date());

		UserType userType = fetchUserType(userProfile.getUserType());

		if (userType != null) {

			userAccount.setUserType(userType);

		}

		LOGGER.debug("converted recruiterAccount from userProfile: " + userProfile + " to userAccount: " + userAccount);

		return userAccount;
	}

	/**
	 * 
	 * @param userProfile
	 * @return
	 */
	public UserAccount convertToUserAccount(UserProfile userProfile) {

		LOGGER.debug("converting to userAccount: " + userProfile);

		UserAccount userAccount = new UserAccount();

		userAccount.setContactNumber(userProfile.getContactNumber());

		userAccount.setDateOfBirth(new Date());

		userAccount.setFirstName(userProfile.getFirstName());

		userAccount.setLastName(userProfile.getLastName());

		userAccount.setEmailId(userProfile.getEmail());

		userAccount.setRole(RoleEnum.USER);

		userAccount.setPassword(userProfile.getPassword());

		userAccount.setRegistrationDate(new Date());

		UserType userType = fetchUserType(userProfile.getUserType());

		if (userType != null) {

			userAccount.setUserType(userType);

		}

		if (userProfile.getSeekerProfileRequest() != null) {

			Seeker request = userProfile.getSeekerProfileRequest();

			SeekerProfile seekerProfile = new SeekerProfile();

			seekerProfile.setCurrentLocation(request.getCurrentLocation());

			seekerProfile.setExpInMonths(request.getExpInMonths());

			seekerProfile.setHighestQualification(request.getHighestQualification());

			seekerProfile.setIsActive(request.getIsActive());

			seekerProfile.setProfileSummary(request.getProfileSummary());

			if (request.getSeekerSkillSets() != null) {

				List<SeekerSkillSet> skills = getSeekerSkillSet(request.getSeekerSkillSets(), seekerProfile);

				seekerProfile.setSeekerSkillSets(skills);

			}
			seekerProfile.setUserAccount(userAccount);
			userAccount.setSeekerProfile(seekerProfile);

		}

		LOGGER.debug("converted userAccount from userProfile: " + userProfile + " to userAccount: " + userAccount);

		return userAccount;
	}

	public List<SeekerSkillSet> getSeekerSkillSet(List<Integer> seekerSkillSets, SeekerProfile seekerProfile) {

		List<SeekerSkillSet> skills = new ArrayList<>();

		List<SkillSet> skillSetList = skillSetService.findAllById(seekerSkillSets);

		for (SkillSet skillSet : skillSetList) {

			SeekerSkillSet userSkillSet = new SeekerSkillSet();

			userSkillSet.setSeekerProfile(seekerProfile);

			userSkillSet.setSkillSet(skillSet);

			skills.add(userSkillSet);

		}

		return skills;
	}

	/**
	 * 
	 * @param userType
	 * @return
	 */
	public UserType fetchUserType(Integer userTypeId) {

		LOGGER.debug("fetching userType details using userTypeId: " + userTypeId);

		UserType userTypeDetail = userTypeService.retrieveUserTypeById(userTypeId);

		LOGGER.debug("userType fetched: " + userTypeDetail);

		return userTypeDetail;

	}

	/**
	 * 
	 * @param createdUserAccount
	 * @return
	 */
	public UserProfile convertToUserProfile(UserAccount userAccount) {

		LOGGER.debug("converting userAccount to userProfile: " + userAccount);

		UserProfile userProfile = new UserProfile();

		userProfile.setIsResumeAvailable(Boolean.FALSE);

		userProfile.setContactNumber(userAccount.getContactNumber());

		if (userAccount.getDateOfBirth() != null) {

			userProfile.setDateOfBirth(userAccount.getDateOfBirth().toString());

		}

		userProfile.setEmail(userAccount.getEmailId());

		userProfile.setFirstName(userAccount.getFirstName());

		userProfile.setLastName(userAccount.getLastName());

		if (userAccount.getRegistrationDate() != null) {

			userProfile.setRegistrationDate(userAccount.getRegistrationDate().toString());

		}
		if (userAccount.getRole() != null) {

			userProfile.setRole(userAccount.getRole());

		}

		if (userAccount.getSeekerProfile() != null) {

			Seeker seeker = new Seeker();

			SeekerProfile seekerProfile = userAccount.getSeekerProfile();

			if (seekerProfile.getResume() != null) {

				userProfile.setIsResumeAvailable(Boolean.TRUE);

				seeker.setIsResumeAvailable(Boolean.TRUE);

			}

			seeker.setSeekerId(userAccount.getEmailId());

			seeker.setCurrentLocation(seekerProfile.getCurrentLocation());

			seeker.setExpInMonths(seekerProfile.getExpInMonths());

			seeker.setHighestQualification(seekerProfile.getHighestQualification());

			seeker.setIsActive(seekerProfile.getIsActive());

			seeker.setProfileSummary(seekerProfile.getProfileSummary());

			if (seekerProfile.getSeekerSkillSets() != null && seekerProfile.getSeekerSkillSets().size() > 0) {

				List<Integer> keySkills = new ArrayList<>();

				for (SeekerSkillSet seekerSkillSet : seekerProfile.getSeekerSkillSets()) {

					if (seekerSkillSet.getSkillSet() != null) {

						keySkills.add(seekerSkillSet.getSkillSet().getId());

					}

				}

				seeker.getSeekerSkillSets().addAll(keySkills);

			}

			userProfile.setSeekerProfileRequest(seeker);

		}

		if (userAccount.getUserType() != null) {

			userProfile.setUserType(userAccount.getUserType().getUserTypeId());

		}

		LOGGER.debug("converted userAccount: " + userAccount + " to userProfile: " + userProfile);

		return userProfile;

	}

	public SeekerListResponse findProfilesByJobId(Integer jobId) {

		LOGGER.debug("findProfilesByJobId: " + jobId);

		SeekerListResponse response = new SeekerListResponse();

		Job job = jobService.retrieveJobById(jobId);

		List<JobPostActivity> jobsList = jobPostActivityService.findByJob(job);

		LOGGER.debug("Number of seekers applied for jobId: " + jobId + " are " + jobsList.size());

		List<UserAccount> userAccountList = new ArrayList<>();

		if (jobsList != null && jobsList.size() > 0) {

			for (JobPostActivity jobPostActivity : jobsList) {

				userAccountList.add(jobPostActivity.getUserAccount());

			}

		}

		List<Seeker> seekerResponseList = convertUserAccountListToSeekerList(userAccountList);

		response.setSeekerList(seekerResponseList);

		if (response != null && response.getSeekerList() != null) {

			LOGGER.debug("total job applied by jobId: " + jobId + " are " + response.getSeekerList().size());

		}

		return response;

	}

	private List<Seeker> convertUserAccountListToSeekerList(List<UserAccount> userAccountList) {
		// TODO Auto-generated method stub

		List<Seeker> seekerList = new ArrayList<>();

		if (userAccountList != null) {

			for (UserAccount userAccount : userAccountList) {

				Seeker seeker = convertUserAccountToSeeker(userAccount);

				if (seeker != null) {

					seekerList.add(seeker);

				}
			}
		}
		return seekerList;
	}

	private Seeker convertUserAccountToSeeker(UserAccount userAccount) {

		LOGGER.debug("Converting userAccount to Seeker");

		Seeker seeker = new Seeker();

		if (userAccount.getSeekerProfile() != null) {

			seeker.setExpInMonths(userAccount.getSeekerProfile().getExpInMonths());

			seeker.setCurrentLocation(userAccount.getSeekerProfile().getCurrentLocation());

			seeker.setHighestQualification(userAccount.getSeekerProfile().getHighestQualification());

			seeker.setProfileSummary(userAccount.getSeekerProfile().getProfileSummary());

			if (userAccount.getSeekerProfile().getResume() != null) {

				seeker.setIsResumeAvailable(true);
			}

			if (userAccount.getSeekerProfile().getSeekerSkillSets() != null
					&& userAccount.getSeekerProfile().getSeekerSkillSets().size() > 0) {

				List<Integer> keySkills = new ArrayList<>();

				for (SeekerSkillSet seekerSkillSet : userAccount.getSeekerProfile().getSeekerSkillSets()) {

					if (seekerSkillSet.getSkillSet() != null) {

						keySkills.add(seekerSkillSet.getSkillSet().getId());

					}

				}

				seeker.getSeekerSkillSets().addAll(keySkills);

			}

		}

		seeker.setSeekerId(userAccount.getEmailId());

		seeker.setFirsName(userAccount.getFirstName());

		seeker.setLastName(userAccount.getLastName());

		seeker.setContactNumber(userAccount.getContactNumber());

		LOGGER.debug("Converted seeker : " + seeker);

		return seeker;
	}

	public JobListResponse findAppliedJobByEmailId(String emailId) {

		LOGGER.debug("findAppliedJobByEmailid ");

		JobListResponse response = new JobListResponse();

		UserAccount userAccount = userAccountService.retrieveUserAccountByEmailId(emailId);

		List<JobPostActivity> jobsList = jobPostActivityService.findByUserAccount(userAccount);

		List<Job> jobsApplied = new ArrayList<>();

		if (jobsList != null && jobsList.size() > 0) {

			for (JobPostActivity jobPostActivity : jobsList) {

				jobsApplied.add(jobPostActivity.getJob());

			}

		}

		List<JobResponse> jobResponseList = converJobList(jobsApplied);

		response.setJobList(jobResponseList);

		if (response != null && response.getJobList() != null) {

			LOGGER.debug("total job applied by userId: " + emailId + " are " + response.getJobList().size());

		}

		return response;

	}

	/**
	 * 
	 * @param authCredentials
	 * @return
	 */
	public String fetchUserNameAndPassword(final String authCredentials) {
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");

		String usernameAndPassword = null;

		try {

			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);

			usernameAndPassword = new String(decodedBytes, "UTF-8");

		} catch (IOException e) {

			e.printStackTrace();

		}
		return usernameAndPassword;
	}

	/**
	 * 
	 * @param emailId
	 * @return
	 */
	public String getEmailIdFromRequest(String emailId) {

		if (emailId != null && emailId.equals("0000")) {

			LOGGER.debug("fetching email Id from thread local");

			UserInformation userInfo = (UserInformation) TroikaThreadLocal.get();

			if (userInfo != null) {

				String email = userInfo.getEmailId();

				if (email != null) {

					LOGGER.debug("fetched emailId from thread local: " + email);

					emailId = email;
				}
			}
		}

		return emailId;
	}

	/**
	 * 
	 * @param seekerProfileList
	 * @return
	 */
	public List<UserProfileResponse> convertSeekerProfileList(List<SeekerProfile> seekerProfileList) {

		List<UserProfileResponse> userProfileList = new ArrayList<>();

		for (SeekerProfile seekerProfile : seekerProfileList) {

			UserProfileResponse userProfile = new UserProfileResponse();

			userProfile.setHighestQualification(seekerProfile.getHighestQualification());

			userProfile.setEmailId(seekerProfile.getUserAccount().getEmailId());

			if (seekerProfile.getUserAccount() != null) {

				UserAccount userAccount = seekerProfile.getUserAccount();

				userProfile.setFirstName(userAccount.getFirstName());

				userProfile.setLastName(userAccount.getLastName());

			}

			if (seekerProfile.getResume() != null) {

				userProfile.setIsResumeAvailable(Boolean.TRUE);

			}

			userProfile.setExpInMonths(seekerProfile.getExpInMonths());

			if (seekerProfile.getSeekerSkillSets() != null && seekerProfile.getSeekerSkillSets().size() > 0) {

				List<Integer> keySkills = new ArrayList<>();

				for (SeekerSkillSet seekerSkillSet : seekerProfile.getSeekerSkillSets()) {

					if (seekerSkillSet.getSkillSet() != null) {

						keySkills.add(seekerSkillSet.getSkillSet().getId());

					}

				}

				userProfile.getSkillSets().addAll(keySkills);

			}

			userProfileList.add(userProfile);

		}

		return userProfileList;
	}

	/**
	 * 
	 * @param userProfile
	 * @param file
	 * @return
	 */
	public UserAccount convertToUserAccount(UserProfile userProfile, byte[] file) {

		UserAccount convertedUserProfile = convertToUserAccount(userProfile);

		if (file != null) {

			if (convertedUserProfile.getSeekerProfile() != null) {

				convertedUserProfile.getSeekerProfile().setResume(file);

			}
		}

		return convertedUserProfile;
	}

	/**
	 * 
	 * @param resume
	 */
	public static File convertByteToDoc(byte[] resume, String firstName, String lastName) {

		FileInputStream fileInputStream = null;

		try {

			File file = new File(firstName + "_" + lastName + ".docx");
			// byte[] bFile = new byte[(int) file.length()];

			// read file into bytes[]
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(resume);

			// save bytes[] into a file
			writeBytesToFileNio(resume, UPLOAD_FOLDER + "test3.doc");

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return null;

	}

	private static void writeBytesToFileNio(byte[] bFile, String fileDest) {

		try {
			Path path = Paths.get(fileDest);
			Files.write(path, bFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Boolean SendEmail(EmailNotificationRequest emailRequest) {

		LOGGER.debug("sending email: " + emailRequest);

		Boolean isEmailSent = Boolean.FALSE;

		LOGGER.debug("isEmailSent: " + isEmailSent);

		return isEmailSent;

	}

	/**
	 * 
	 * @param toEmail
	 * @param ccEmail
	 * @param bccEmail
	 * @param subject
	 * @param content
	 * @return
	 */
	public EmailNotificationRequest createEmailRequest(String[] toEmail, String[] ccEmail, String[] bccEmail,
			String subject, String content) {

		EmailNotificationRequest request = new EmailNotificationRequest();

		if (((toEmail == null || toEmail.length == 0) && ((ccEmail == null || ccEmail.length == 0))
				&& ((bccEmail == null || bccEmail.length == 0))) && ((null != subject) || (null != content))) {

			LOGGER.debug("insufficent data to create emailRequest");

			return null;

		}

		request.setToEmail(toEmail);

		request.setCcEmail(bccEmail);

		request.setBccEmail(bccEmail);

		request.setEmailSubject(subject);

		request.setEmailContent(content);

		LOGGER.debug("email request created: " + request);

		return request;
	}

	/**
	 * 
	 * @param data
	 */
	public void insertAuditReportData(AuditReportData data) {

		LOGGER.trace("inserting data: " + data);

		auditService.postAuditReportData(data);

		LOGGER.trace("inserted data successfully");

	}

	/**
	 * 
	 * @return
	 */
	public static String generateProcessingCode() {
		try {

			LOGGER.info("Generating processing code");

			MessageDigest instance = MessageDigest.getInstance("MD5");

			byte[] messageDigest = instance.digest(String.valueOf(System.nanoTime()).getBytes());

			StringBuilder hexString = new StringBuilder();

			for (int i = 0; i < messageDigest.length; i++) {

				String hex = Integer.toHexString(0xFF & messageDigest[i]);

				if (hex.length() == 1) {

					hexString.append('0');

				}

				hexString.append(hex);
			}

			LOGGER.info("processing code generated");

			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {

			LOGGER.info("processing code can't be generated");

			return "";
		}
	}

	/**
	 * 
	 * @param processingCode
	 * @param emailId
	 * @param operation
	 * @return
	 */
	public AuditReportData createAuditReportData(String processingCode, String emailId, String operation) {

		LOGGER.trace("inserting auditReportData");

		AuditReportData data = new AuditReportData();

		data.setDateCreated(new Date());

		data.setProcessingCode(processingCode);

		data.setRequesterEmail(emailId);

		data.setOperation(operation);

		LOGGER.trace("created auditrpeportData: " + data);

		return data;

	}

	/**
	 * 
	 * @param processingCode
	 * @return
	 */
	public String getEmailVerificaitonContent(String processingCode) {

		StringBuilder content = new StringBuilder();

		content.append("<i>Dear User,");
		content.append("<br />");
		content.append("<br />");
		content.append(
				"Welcome, and thanks for signing up, Please click the below button to activate your account <br />");
		content.append("please <a href=" + linkServiceUrl.trim() + processingCode + ">Confirm here</a>");
		content.append("<br />");
		content.append("Once it is successfully updated, you will be able to login in the application");
		content.append("<br />");
		content.append("<br />");
		content.append("Please DO NOT REPLY to this email.");
		content.append("<br />");
		content.append("<br />");
		content.append("Thank you for your support.");
		content.append("<br />");
		content.append("<br />");
		content.append("Best regards,");
		content.append("<br />");
		content.append("Troika Systems</i>");

		content.append(linkServiceUrl.trim() + processingCode);

		return content.toString();

	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public String getWelcomeEmailContent(String firstName, String lastName) {

		StringBuilder content = new StringBuilder();

		content.append("<i>Dear " + firstName + " " + lastName);
		content.append("<br />");
		content.append("<br />");
		content.append("Welcome you account has been register, you will be able to login in the application now.");
		content.append("<br />");
		content.append("<br />");
		content.append("Please DO NOT REPLY to this email.");
		content.append("<br />");
		content.append("<br />");
		content.append("Thank you for your support.");
		content.append("<br />");
		content.append("<br />");
		content.append("Best regards,");
		content.append("<br />");
		content.append("Troika Systems</i>");

		return content.toString();

	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public String getWelcomeRecruiterEmailContent(String firstName, String lastName) {

		StringBuilder content = new StringBuilder();

		content.append("<i>Dear " + firstName + " " + lastName);
		content.append("<br />");
		content.append("<br />");
		content.append(
				"Welcome you account has been register, once admin approves your request, then you will be able to perform operations.");
		content.append("<br />");
		content.append("<br />");
		content.append("Please DO NOT REPLY to this email.");
		content.append("<br />");
		content.append("<br />");
		content.append("Thank you for your support.");
		content.append("<br />");
		content.append("<br />");
		content.append("Best regards,");
		content.append("<br />");
		content.append("Troika Systems</i>");

		return content.toString();

	}

	/**
	 * 
	 * @param processingCode
	 * @return
	 */
	public String getRoleUpdateEmailConent(String firstName, String lastName, String emailId, String role) {

		StringBuilder content = new StringBuilder();

		content.append("<i>Dear " + firstName + " " + lastName + ",");
		content.append("<br />");
		content.append("<br />");
		content.append("Your role " + role
				+ " has been granted full permissions, you can now able to search profiles and post jobs.");
		content.append("<br />");
		content.append("<br />");
		content.append("Please DO NOT REPLY to this email.");
		content.append("<br />");
		content.append("<br />");
		content.append("Thank you for your support.");
		content.append("<br />");
		content.append("<br />");
		content.append("Best regards,");
		content.append("<br />");
		content.append("Troika Systems</i>");

		return content.toString();

	}

	// private String getApprovalEmailContent(final String requestor, final
	// String target, final int emailType) {
	//
	// String content = "";
	//
	// switch (emailType) {
	//
	// case 1:
	//
	// content = "We have received a request from " + requestor + " to link your
	// Digi number " + target
	// + " to his/her MyDigi account, and is now sending you this email to seek
	// your approval."
	// + "Please note that this is not a request to transfer ownership – you
	// will still be the rightful owner of "
	// + target + ". " + "<br/>It's just that, moving forward, " + requestor
	// + " will be able to access your usage info from his/her MyDigi account.";
	//
	// break;
	//
	// case 2:
	//
	// if (requestor == null || (requestor != null
	// && (requestor.equalsIgnoreCase(DUMMY_EMAIL) ||
	// requestor.equalsIgnoreCase("a@a.a")))) {
	//
	// LOGGER.debug("as email is dummy@digi.com.my so sending message for target
	// email only " + target);
	//
	// content = "We have received a request to update your MyDigi Email ID to "
	// + target + ".";
	//
	// } else {
	//
	// LOGGER.debug("as email is not dummy@digi.com.my so sending message for
	// target email only " + requestor);
	//
	// content = "We have received a request from you to update your MyDigi
	// Email ID from " + requestor
	// + " to " + target + ".";
	//
	// }
	//
	// break;
	//
	// }
	//
	// return content;
	// }

	public void sendWelcomeEmail(String emailId, String firstName, String lastName) {

		String[] toEmail = { emailId };

		EmailNotificationRequest emailRequest = createEmailRequest(toEmail, null, null,
				getSubject(NotificationTypeEnum.WELCOME_EMAIL), getWelcomeEmailContent(firstName, lastName));

		emailService.sendEmail(emailRequest.getEmailSubject(), emailRequest.getEmailContent(), toEmail, null, null);

		LOGGER.debug("Email Sent...: ");

	}

	private Boolean sendEmail(EmailNotificationRequest emailRequest) {

		LOGGER.debug("sending email : " + emailRequest);

		return null;

	}

	private String getSubject(NotificationTypeEnum notificationType) {

		String request = notificationType.name();

		String subject = "";

		switch (request) {

		case "EMAIL_VERIFICATION":

			subject = "Verification email for account created";

			break;

		case "WELCOME_EMAIL":

			subject = "Welcome to Troika System job portal";

			break;

		case "RECRUITER_ADMIN_NOTIFICATION":

			subject = "Recruiter request for access";

			break;
			
		case "RECRUITER_ROLE_UPDATE_NOTIFICATION":

			subject = "Admin updated role access request";

			break;
		}

		return subject;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getAllAdminEmailIds() {

		List<String> emailIds = userAccountService.getAllAdminEmailIds();

		LOGGER.debug("admin emailIds: " + emailIds);

		return emailIds;
	}

	/**
	 * Returns the subject depending upon the emailType and Operation.
	 *
	 * @param operation
	 * @param emailType
	 * @return
	 */
	// private String getSubject(final String operation, final String emailType)
	// {
	//
	// String content = "";
	//
	// if (operation.equalsIgnoreCase(OCSServicesConstants.LINK_A_MSISDN)) {
	//
	// if (emailType != null &&
	// emailType.equalsIgnoreCase(OCSServicesConstants.CONFIRMATION_EMAIL)) {
	//
	// content = "Your request to link account approved";
	//
	// } else if (emailType != null &&
	// emailType.equalsIgnoreCase(OCSServicesConstants.NOTIFICATION_EMAIL)) {
	//
	// content = "Your request to link account completed";
	//
	// }
	//
	// } else if
	// (operation.equalsIgnoreCase(OCSServicesConstants.ADD_TELENOR_EMAIL)) {
	//
	// if (emailType != null &&
	// emailType.equalsIgnoreCase(OCSServicesConstants.CONFIRMATION_EMAIL)) {
	//
	// content = "Your request to update login address completed";
	//
	// } else if (emailType != null &&
	// emailType.equalsIgnoreCase(OCSServicesConstants.NOTIFICATION_EMAIL)) {
	//
	// content = "Your request to update login address completed";
	//
	// }
	// } else if
	// (operation.equalsIgnoreCase(OCSServicesConstants.ADD_TELENOR_CRM_EMAIL))
	// {
	//
	// if (emailType != null &&
	// emailType.equalsIgnoreCase(OCSServicesConstants.CONFIRMATION_EMAIL)) {
	//
	// content = "Your login and billing address updated";
	//
	// } else if (emailType != null &&
	// emailType.equalsIgnoreCase(OCSServicesConstants.NOTIFICATION_EMAIL)) {
	//
	// content = "Your approval request completed";
	//
	// }
	//
	// } else if (operation.equalsIgnoreCase(OCSServicesConstants.IR_SERVICE)) {
	//
	// content += "International Roaming request approved";
	//
	// }
	//
	// LOGGER.debug("subject: " + content);
	//
	// return content;
	// }

	public void sendRecruiterCreationToAdmin(String recruiterEmailId, String recruiterFirstName,
			String recruiterLastName) {

		List<String> adminEmailIds = getAllAdminEmailIds();

		String[] toEmail = adminEmailIds.toArray(new String[adminEmailIds.size()]);

		EmailNotificationRequest emailRequest = createEmailRequest(toEmail, null, null,
				getSubject(NotificationTypeEnum.RECRUITER_ADMIN_NOTIFICATION),
				getRecruiterAdminEmailContent(recruiterEmailId, recruiterFirstName, recruiterLastName));

		emailService.sendEmail(emailRequest.getEmailSubject(), emailRequest.getEmailContent(), toEmail, null, null);

		LOGGER.debug("Email Sent...: ");

	}

	private String getRecruiterAdminEmailContent(String recruiterEmailId, String recruiterFirstName,
			String recruiterLastName) {

		StringBuilder content = new StringBuilder();

		content.append("<i>Dear Admin");
		content.append("<br />");
		content.append("<br />");
		content.append("A new recruiter user has been created in the system with name " + recruiterFirstName + " "
				+ recruiterLastName + " using emailId " + recruiterEmailId + ".");
		content.append("Please go to update roles page and update his role to appropirate.");
		content.append("<br />");
		content.append("<br />");
		content.append("Please DO NOT REPLY to this email.");
		content.append("<br />");
		content.append("<br />");
		content.append("Thank you for your support.");
		content.append("<br />");
		content.append("<br />");
		content.append("Best regards,");
		content.append("<br />");
		content.append("Troika Systems</i>");

		return content.toString();
	}

	/**
	 * 
	 * @param recruiterEmailId
	 * @param recruiterFirstName
	 * @param recruiterLastName
	 */
	public void sendRecruiterWelcomeEmail(String recruiterEmailId, String recruiterFirstName,
			String recruiterLastName) {

		String[] toEmail = { recruiterEmailId };

		EmailNotificationRequest emailRequest = createEmailRequest(toEmail, null, null,
				getSubject(NotificationTypeEnum.WELCOME_EMAIL),
				getWelcomeRecruiterEmailContent(recruiterFirstName, recruiterLastName));

		emailService.sendEmail(emailRequest.getEmailSubject(), emailRequest.getEmailContent(), toEmail, null, null);

		LOGGER.debug("Email Sent...: ");

	}

	/**
	 * 
	 * @param recruiterEmailId
	 * @param recruiterFirstName
	 * @param recruiterLastName
	 */
	public void sendRecruiterRoleUpdateEmail(String recruiterEmailId, String recruiterFirstName,
			String recruiterLastName) {
		String[] toEmail = { recruiterEmailId };

		EmailNotificationRequest emailRequest = createEmailRequest(toEmail, null, null,
				getSubject(NotificationTypeEnum.RECRUITER_ROLE_UPDATE_NOTIFICATION),
				getRecruiterRoleUpdateNotification(recruiterFirstName, recruiterLastName));

		emailService.sendEmail(emailRequest.getEmailSubject(), emailRequest.getEmailContent(), toEmail, null, null);

		LOGGER.debug("Email Sent...: ");
	}

	private String getRecruiterRoleUpdateNotification(String recruiterFirstName, String recruiterLastName) {

		StringBuilder content = new StringBuilder();

		content.append("<i>Dear " + recruiterFirstName + " " + recruiterLastName);
		content.append("<br />");
		content.append("<br />");
		content.append("Admin has updated your role, please logout if already login.");
		content.append("<br />");
		content.append("<br />");
		content.append("Please DO NOT REPLY to this email.");
		content.append("<br />");
		content.append("<br />");
		content.append("Thank you for your support.");
		content.append("<br />");
		content.append("<br />");
		content.append("Best regards,");
		content.append("<br />");
		content.append("Troika Systems</i>");

		return content.toString();

	}

}

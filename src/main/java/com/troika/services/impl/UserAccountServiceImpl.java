package com.troika.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.dao.JobDao;
import com.troika.dao.SeekerProfileDao;
import com.troika.dao.SeekerSkillSetDao;
import com.troika.dao.UserAccountDao;
import com.troika.domain.model.Job;
import com.troika.domain.model.RoleEnum;
import com.troika.domain.model.SeekerProfile;
import com.troika.domain.model.SeekerSkillSet;
import com.troika.domain.model.UserAccount;
import com.troika.domain.view.SearchRequest;
import com.troika.domain.view.UserRoleRequest;
import com.troika.services.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	private static final Logger LOGGER = Logger.getLogger(UserAccountServiceImpl.class);

	@Autowired
	private UserAccountDao userAccountDao;

	@Autowired
	private SeekerProfileDao seekerDao;

	@Autowired
	private JobDao jobDao;

	@Autowired
	private SeekerSkillSetDao seekerSkillSetDao;

	@Override
	public UserAccount retrieveUserAccountByEmailId(String emailId) {

		LOGGER.debug("finding userAccount details using emailId: " + emailId);

		UserAccount userAccount = userAccountDao.retireveUserAccountByEmailId(emailId);

		return userAccount;
	}

	@Override
	public UserAccount registerUserAccount(UserAccount userAccount) {

		LOGGER.debug("creating userAccount with details: " + userAccount);

		UserAccount registerUserAccount = null;

		SeekerProfile seeker = null;

		// byte[] deo = writeToFile();

		if (userAccount.getSeekerProfile() == null) {

			registerUserAccount = userAccountDao.createUserAccount(userAccount);

		} else {

			// userAccount.getSeekerProfile().setResume(deo);

			seeker = seekerDao.createSeekerProfile(userAccount.getSeekerProfile());

			registerUserAccount = seeker.getUserAccount();
		}

		LOGGER.debug("registerUserAccount: " + registerUserAccount);

		return registerUserAccount;
	}

	byte[] writeToFile() {

		File file = new File("C:\\uploaded\\rc061-010d-java_concurrency_1.pdf");
		byte[] bFile = new byte[(int) file.length()];
		try {

			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				// convert file into array of bytes
				fileInputStream.read(bFile);
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return bFile;

	}

	@Override
	public UserAccount deleteUserAccountByEmailId(String emailId) {

		LOGGER.debug("deleting userAccount with compId: " + emailId);

		// check whether userAccount exists, if then retrieve it and delete it.

		UserAccount userAccountToDelete = userAccountDao.retireveUserAccountByEmailId(emailId);

		if (userAccountToDelete != null) {

			if (userAccountToDelete.getEmailId() != null) {

				LOGGER.debug("deleting userAccount : ");

				userAccountDao.deleteUserAccount(userAccountToDelete);

			}
		}

		return userAccountToDelete;
	}

	@Override
	public UserAccount updateUserAccount(UserAccount userAccount) {

		LOGGER.debug("Updating userAccount with userAccount: " + userAccount);

		// check whether userAccount exists, if then retrieve it and update it.

		// UserAccount userAccountToUpdate =
		// userAccountDao.retireveUserAccountById(userAccount.getUserAccountId());
		//
		// if (userAccountToUpdate != null) {
		//
		// if (userAccountToUpdate.getUserAccountId() != 0) {
		//
		// LOGGER.debug("updating userAccount : ");
		//
		// userAccountToUpdate = JobPortalHelper.copyUserAccount(userAccount,
		// userAccountToUpdate);
		//
		// userAccountDao.updateUserAccount(userAccountToUpdate);
		//
		// }
		// }

		return null;
	}

	@Override
	public List<UserAccount> findAll() {
		return userAccountDao.findAll();
	}

	@Override
	public List<Job> retrieveJobsPostedByEmailId(String emailId) {

		LOGGER.debug("finding jobs posted by emailId: " + emailId);

		UserAccount userAccount = retrieveUserAccountByEmailId(emailId);

		if (userAccount == null) {

			LOGGER.debug("not able to fetch userAccount for emailId: " + emailId);

			return null;

		}

		List<Job> jobsList = jobDao.retrieveJobsPostedById(userAccount);

		LOGGER.debug("no. of jobs posted byuserAccountId: " + emailId + " are " + jobsList.size());

		return jobsList;

	}

	@Override
	public UserAccount updateUserRole(UserRoleRequest request) {

		LOGGER.debug("update request for role");

		UserAccount userAccountUpdated = null;

		UserAccount userAccountToUpdate = userAccountDao.retireveUserAccountByEmailId(request.getEmailId());

		if (userAccountToUpdate != null) {

			RoleEnum role = RoleEnum.valueOf(request.getRole());

			userAccountToUpdate.setRole(role);

			userAccountToUpdate.setIsAuthorizedToPostJob(Boolean.TRUE);

			userAccountUpdated = userAccountDao.updateUserAccount(userAccountToUpdate);

		}

		return userAccountUpdated;

	}

	@Override
	public UserAccount retrieveUserAccountBasicInfo(String emailId) {

		LOGGER.debug("finding retrieveUserAccountBasicInfo details using emailId: " + emailId);

		UserAccount userAccount = userAccountDao.retireveUserAccountBasicInfo(emailId);

		return userAccount;

	}

	@Override
	public List<SeekerProfile> retrieveProfiles(SearchRequest request) {

		LOGGER.debug("SearchRequest in retrieveProfiles: " + request);

		List<SeekerProfile> seekerProfileList = new ArrayList<>();

		List<SeekerProfile> seekerProfileListToReturn = new ArrayList<>();

		List<Integer> skillSetList = request.getSkillSetList();

		List<SeekerSkillSet> skillList = null;

		LOGGER.debug("retrieving skills from seekerSkillSet");

		if (skillSetList != null) {

			skillList = seekerSkillSetDao.findUsersBySkillList(skillSetList);

		}

		LOGGER.info("called skillSet to fetch userprofiles");

		if (skillList != null && skillList.size() > 0) {

			LOGGER.debug("number of profiles for " + skillSetList + " are " + skillList.size());

			for (SeekerSkillSet seekerSkillSet : skillList) {

				if (seekerSkillSet.getSeekerProfile() != null) {

					seekerProfileList.add(seekerSkillSet.getSeekerProfile());

				}
			}

		}

		if (seekerProfileList != null && seekerProfileList.size() > 0) {

			LOGGER.debug("look out for experience");

			String location = null;

			for (SeekerSkillSet seekerSkillSet : skillList) {

				location = seekerSkillSet.getSeekerProfile().getCurrentLocation();

				if ((location != null && request.getLocation() != null)
						&& location.equalsIgnoreCase(request.getLocation())) {

					if (seekerSkillSet.getSeekerProfile().getExpInMonths() >= request.getExperience()) {

						seekerProfileListToReturn.add(seekerSkillSet.getSeekerProfile());

					}
				}
			}

		}
		return seekerProfileListToReturn;

	}

	@Override
	public List<Job> retrieveAllJobs() {

		LOGGER.debug("Retrieve all jobs posted");

		List<Job> jobsList = jobDao.findAll();

		LOGGER.debug("no. of jobs retrieveAllJobs  are " + jobsList.size());

		return jobsList;

	}

	@Override
	public List<String> getAllAdminEmailIds() {

		List<String> emailIds = userAccountDao.getAllAdminEmailIds();

		LOGGER.debug("email Ids of admins: " + emailIds);

		return emailIds;
	}

}

package com.troika.services;

import java.util.List;

import com.troika.domain.model.Job;
import com.troika.domain.model.SeekerProfile;
import com.troika.domain.model.UserAccount;
import com.troika.domain.view.SearchRequest;
import com.troika.domain.view.UserRoleRequest;

public interface UserAccountService {

	// UserAccount retrieveUserAccountById(final Integer userAccountId);

	UserAccount retrieveUserAccountByEmailId(final String emailId);

	UserAccount registerUserAccount(UserAccount userAccount);

	UserAccount retrieveUserAccountBasicInfo(final String emailId);

	// UserAccount deleteUserAccountById(Integer compId);

	UserAccount deleteUserAccountByEmailId(final String emailId);

	UserAccount updateUserAccount(UserAccount userAccount);

	List<UserAccount> findAll();

	// List<Job> retrieveJobsPostedById(Integer userAccountId);

	List<Job> retrieveJobsPostedByEmailId(String emailId);

	UserAccount updateUserRole(UserRoleRequest request);

	List<SeekerProfile> retrieveProfiles(SearchRequest request);

	List<Job> retrieveAllJobs();

	List<String> getAllAdminEmailIds();

}

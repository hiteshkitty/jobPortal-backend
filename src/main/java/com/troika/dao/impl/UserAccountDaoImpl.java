package com.troika.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troika.dao.UserAccountDao;
import com.troika.domain.model.RoleEnum;
import com.troika.domain.model.UserAccount;
import com.troika.repo.UserAccountRepo;

@Component
public class UserAccountDaoImpl implements UserAccountDao {

	private static final Logger LOGGER = Logger.getLogger(UserAccountDaoImpl.class);

	@Autowired
	private UserAccountRepo userAccountRepo;

	// @Override
	// public UserAccount retireveUserAccountById(Integer userAccountId) {
	//
	// UserAccount userAccount = userAccountRepo.findOne(userAccountId);
	//
	// LOGGER.debug("UserAccount: " + userAccount);
	//
	// return userAccount;
	//
	// }

	@Override
	public UserAccount retireveUserAccountByEmailId(String emailId) {

		UserAccount userAccount = userAccountRepo.findByEmailId(emailId);

		LOGGER.debug("UserAccount: " + userAccount);

		return userAccount;

	}

	@Override
	public UserAccount createUserAccount(UserAccount userAccount) {

		LOGGER.debug("creating userAccount details: " + userAccount);

		UserAccount userAccountCreated = userAccountRepo.save(userAccount);

		LOGGER.debug("created UserAccountDetails: " + userAccountCreated);

		return userAccountCreated;

	}

	@Override
	public void deleteUserAccount(UserAccount userAccountToDelete) {

		LOGGER.debug("deleting userAccount details: " + userAccountToDelete);

		userAccountRepo.delete(userAccountToDelete);

		LOGGER.debug("created UserAccountDetails: " + userAccountToDelete);

	}

	@Override
	public UserAccount updateUserAccount(UserAccount userAccountToUpdate) {

		LOGGER.debug("updating userAccount details: " + userAccountToUpdate);

		UserAccount userAccountUpdated = userAccountRepo.save(userAccountToUpdate);

		LOGGER.debug("userAccountUpdated UserAccountDetails: " + userAccountUpdated);

		return userAccountUpdated;
	}

	@Override
	public List<UserAccount> findAll() {

		List<UserAccount> userAccount = null;

		LOGGER.debug("fetching all UserAccount");

		userAccount = (List) userAccountRepo.findAll();

		LOGGER.debug("fetch all UserAccount: ");

		return userAccount;
	}

	@Override
	public UserAccount retireveUserAccountBasicInfo(String emailId) {

		UserAccount userAccount = userAccountRepo.findByEmailId(emailId);

		if (userAccount != null) {
			userAccount.setJobPostActivities(null);

			userAccount.setJobPosts(null);

			// userAccount.setSeekerProfile(null);
		}
		LOGGER.debug("UserAccount for emailId: " + emailId + " : " + userAccount);

		return userAccount;

	}

	@Override
	public List<String> getAllAdminEmailIds() {

		List<UserAccount> userAccounts = userAccountRepo.findByRoleAndIsActiveTrue(RoleEnum.ADMIN);

		List<String> emailIds = new ArrayList<String>();

		if (userAccounts != null) {

			for (UserAccount userAccount : userAccounts) {

				emailIds.add(userAccount.getEmailId());
			}

		}

		return emailIds;
	}

}

package com.troika.services.impl;

import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.troika.domain.model.UserAccount;
import com.troika.helper.GenericException;
import com.troika.helper.JobPortalHelper;
import com.troika.services.AuthenticationService;
import com.troika.services.UserAccountService;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger LOGGER = Logger.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private JobPortalHelper helper;

	@Override
	public UserAccount authenticate(final String authCredentials, Set<String> rolesSet) throws GenericException {

		if (null == authCredentials) {

			return null;

		}

		String usernameAndPassword = helper.fetchUserNameAndPassword(authCredentials);

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");

		final String userName = tokenizer.nextToken();

		final String password = tokenizer.nextToken();

		LOGGER.debug("retrieving information of user for emailId: " + userName);

		UserAccount userAccount = userAccountService.retrieveUserAccountBasicInfo(userName);

		boolean authenticationStatus = false;

		if (userAccount != null) {

			LOGGER.debug(
					"User information found in database for " + userName + " having role " + userAccount.getRole());

		}

		if (userAccount != null && userAccount.getPassword() != null) {

			if (!userName.equalsIgnoreCase(userAccount.getEmailId()) || !password.equals(userAccount.getPassword())) {

				LOGGER.debug("user is not a valid user: " + userName);

			} else {

				LOGGER.debug("password matched for " + userName);

				authenticationStatus = true;

			}
		} else {

			LOGGER.debug("User information not found in database for " + userName);

			throw new GenericException("",
					"Invalid user is accessing the application, please check the username/password",
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (!authenticationStatus) {

			LOGGER.debug("user: " + userName + "");

			userAccount = null;

		}

		return userAccount;
	}

}

package com.troika.aggregator.services.impl;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.ws.rs.core.HttpHeaders;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.aggregator.services.LoginAggService;
import com.troika.cache.CachingService;
import com.troika.domain.model.UserAccount;
import com.troika.domain.view.LoginRequest;
import com.troika.domain.view.RestResponse;
import com.troika.domain.view.StatusEnum;
import com.troika.domain.view.UserInformation;
import com.troika.domain.view.UserProfile;
import com.troika.helper.GenericException;
import com.troika.helper.JobPortalHelper;
import com.troika.services.AuthenticationService;

@Service
public class LoginAggServiceImpl extends BaseCacheServiceImpl implements LoginAggService {

	private static final Logger LOGGER = Logger.getLogger(LoginAggServiceImpl.class);

	public static final String AUTHENTICATION_HEADER = "Authorization";

	public static final String SESSION_ID = "SessionId";

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private JobPortalHelper helper;

	@Autowired
	private CachingService cachingService;

	@Override
	public RestResponse login(HttpHeaders headers) {

		LOGGER.debug("Login invoked");

		String credentials = headers.getRequestHeader(AUTHENTICATION_HEADER) != null
				? headers.getRequestHeader(AUTHENTICATION_HEADER).get(0).trim() : null;

		String usernameAndPassword = helper.fetchUserNameAndPassword(credentials);

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");

		final String userName = tokenizer.nextToken();

		LOGGER.debug("retrieving information of user for emailId: " + userName);

		UserAccount userAccount = null;

		RestResponse response = null;

		try {

			userAccount = authenticationService.authenticate(credentials, null);

			if (userAccount != null) {

				UserProfile loggedInUser = helper.convertToUserProfile(userAccount);

				response = JobPortalHelper.createRestResponse(loggedInUser, StatusEnum.SUCCESS,
						"User logged in successfully.");

				UserInformation userInfo = new UserInformation();

				userInfo.setCredentials(credentials);

				userInfo.setEmailId(userAccount.getEmailId());
				
				userInfo.setIsRecruiter(userAccount.getIsAuthorizedToPostJob());

				userInfo.setRole(userAccount.getRole());

				cachingService.putInCache(credentials, userInfo, "USR_LOG");

			} else {
				response = JobPortalHelper.createRestResponse("User couldn't logged in, please check your credentials.", StatusEnum.FAILURE,
						"User couldn't logged in, please check your credentials.");
			}

		} catch (GenericException e) {

			e.printStackTrace();

			response = JobPortalHelper.createRestResponse("User couldn't logged in, please check your credentials.", StatusEnum.FAILURE,
					"User couldn't logged in.");
		}

		LOGGER.debug("userAccount: " + userAccount);

		JobPortalHelper.logPayload(response);

		return response;
	}

	@Override
	public RestResponse logout(HttpHeaders headers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override

	public RestResponse loginRequest(LoginRequest loginRequest) {

		LOGGER.debug("retrieving information of user for emailId: " + loginRequest.getUserName());

		UserAccount userAccount = null;

		RestResponse response = null;

		String userName = loginRequest.getUserName();

		String password = loginRequest.getPassword();

		try {

			String userCredenitals = userName + ":" + password;

			String credentials = null;

			try {

				credentials = Base64.getEncoder().encodeToString(userCredenitals.getBytes("utf-8"));

			} catch (UnsupportedEncodingException e) {

				LOGGER.error("couldn't encode username password");

				e.printStackTrace();

				response = JobPortalHelper.createRestResponse("User couldn't logged in, please check your credentials.", StatusEnum.FAILURE,
						"User couldn't logged in.");

				return response;

			}

			userAccount = authenticationService.authenticate(credentials, null);

			if (userAccount != null) {

				UserProfile loggedInUser = helper.convertToUserProfile(userAccount);

				response = JobPortalHelper.createRestResponse(loggedInUser, StatusEnum.SUCCESS,
						"User logged in successfully.");

				UserInformation userInfo = new UserInformation();

				userInfo.setCredentials(credentials);

				userInfo.setEmailId(userAccount.getEmailId());
				
				userInfo.setIsRecruiter(userAccount.getIsAuthorizedToPostJob());

				userInfo.setRole(userAccount.getRole());

				cachingService.putInCache(credentials, userInfo, "USR_LOG");

			} else {
				response = JobPortalHelper.createRestResponse("User couldn't logged in, please check your credentials.", StatusEnum.FAILURE,
						"User couldn't logged in.");
			}

		} catch (GenericException e) {

			e.printStackTrace();

			response = JobPortalHelper.createRestResponse("User couldn't logged in, please check your credentials.", StatusEnum.FAILURE,
					"User couldn't logged in.");
		}

		LOGGER.debug("userAccount: " + userAccount);

		JobPortalHelper.logPayload(response);

		return response;

	}

}

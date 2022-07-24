package com.troika.helper.filter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.troika.aggregator.services.MasterDataAggService;
import com.troika.cache.CachingService;
import com.troika.domain.model.EducationalDegree;
import com.troika.domain.model.RoleEnum;
import com.troika.domain.model.State;
import com.troika.domain.model.UserAccount;
import com.troika.domain.model.UserType;
import com.troika.domain.view.MasterData;
import com.troika.domain.view.RestEntity;
import com.troika.domain.view.RestResponse;
import com.troika.domain.view.StatusEnum;
import com.troika.domain.view.UserInformation;
import com.troika.helper.GenericException;
import com.troika.helper.JobPortalHelper;
import com.troika.helper.TroikaConstants;
import com.troika.helper.TroikaThreadLocal;
import com.troika.services.AuthenticationService;

/**
 * This filter verify the access permissions for a user based on username and
 * passowrd provided in request
 */
@Provider
public class TroikaFilterAuthentication implements javax.ws.rs.container.ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	public static final String AUTHENTICATION_HEADER = "Authorization";

	private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
			.entity("Access blocked for all users !!").build();

	private static final Logger LOGGER = Logger.getLogger(TroikaFilterAuthentication.class);

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private MasterDataAggService masterDataAggService;

	@Autowired
	private CachingService cachingService;

	@Autowired
	private JobPortalHelper helper;

	@Override
	public void filter(ContainerRequestContext requestContext) {

		TroikaThreadLocal.setTimerThreadLocal(System.currentTimeMillis());

		String path = requestContext.getUriInfo().getAbsolutePath().getPath();

		String httpMethod = requestContext.getMethod();
		
		LOGGER.debug("URL requested: " + path + " HEADERS: " + requestContext.getHeaders());

		if (path.contains("/jport/auth/")) {

			LOGGER.debug("Skip the authenication here");

			return;
		}

		Method method = resourceInfo.getResourceMethod();

		UserInformation userInfo = new UserInformation();

		String authCredentials = requestContext.getHeaderString(AUTHENTICATION_HEADER);

		UserAccount userAccount = null;

		Set<String> rolesSet = new HashSet<>();

		// Access allowed for all

		if (!method.isAnnotationPresent(PermitAll.class)) {
			// Access denied for all
			if (method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(ACCESS_FORBIDDEN);
				return;
			}

			// Verify user access
			if (method.isAnnotationPresent(RolesAllowed.class)) {

				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);

				rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

				LOGGER.debug("method: " + method.getName() + " has permissions for " + rolesSet);

				// Is user valid?

				userInfo = (UserInformation) cachingService.getFromCache(authCredentials, "USR_LOG");

				if (userInfo == null) {

					try {

						userAccount = authenticationService.authenticate(authCredentials, rolesSet);

						if (userAccount != null) {

							UserInformation userInfoToCache = new UserInformation();

							userInfoToCache.setCredentials(authCredentials);

							userInfoToCache.setEmailId(userAccount.getEmailId());

							userInfoToCache.setRole(userAccount.getRole());
							
							userInfoToCache.setIsRecruiter(userAccount.getIsAuthorizedToPostJob());

							cachingService.putInCache(authCredentials, userInfoToCache, "USR_LOG");

							TroikaThreadLocal.set(userInfoToCache);

							userInfo = new UserInformation();

							userInfo.setEmailId(userAccount.getEmailId());

							userInfo.setRole(userAccount.getRole());
							
							userInfo.setIsRecruiter(userAccount.getIsAuthorizedToPostJob());

						}
					} catch (GenericException e) {

						e.printStackTrace();

						LOGGER.debug("msg" + e.getMessage());

						LOGGER.debug("errormsg" + e.getErrorMessage());

						RestResponse response = JobPortalHelper.createRestResponse(e.getErrorMessage(),
								StatusEnum.FAILURE, e.getMessage());

						requestContext
								.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(response).build());

						return;

					}

					LOGGER.debug("userAccount: " + userAccount);

					if (userAccount == null) {

						// requestContext.abortWith(ACCESS_DENIED);
						// requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Invalid
						// user is trying to access the applicaiton.").build());

						RestResponse response = JobPortalHelper.createRestResponse(
								"Invalid user is trying to access the applicaiton.", StatusEnum.FAILURE,
								"Invalid user is trying to access the applicaiton.");

						requestContext
								.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(response).build());

						return;

					}

				}

				if (userInfo.getRole() != null) {

					String role = userInfo.getRole().name();

					if (rolesSet.contains(role)) {

						LOGGER.debug("role matched for " + authCredentials);

						LOGGER.debug("user role: " + role + " roleset: " + rolesSet);
						
						if (role.equalsIgnoreCase(RoleEnum.RECRUITER.name()) ) {
							
							if ( ! userInfo.getIsRecruiter()) {
								
								LOGGER.debug("user not allowed to perform action.");
								
								requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
										.entity("You as a recruiter yet not allowed to perform this operation.").build());

								RestResponse response = JobPortalHelper.createRestResponse(
										"You as a recruiter yet not allowed to perform this operation.", StatusEnum.FAILURE,
										"You as a recruiter yet not allowed to perform this operation.");

								requestContext
										.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(response).build());

								return;

							} else {
								LOGGER.debug("Recruiter is allowed to post job");
							}
						}
						
						LOGGER.debug("user is all set to access method: " + method);
						
						TroikaThreadLocal.set(userInfo);

					} else {

						LOGGER.debug("role doesn't matched for " + authCredentials);

						// requestContext.abortWith(ACCESS_DENIED);
						requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
								.entity("User is not allowed to access the resource.").build());

						RestResponse response = JobPortalHelper.createRestResponse(
								"User is not authorized to perform this action.", StatusEnum.FAILURE,
								"User is not authorized to perform this action.");

						requestContext
								.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(response).build());

						return;
					}
				}

				if (userInfo != null && userInfo.getRole() != null) {
					LOGGER.debug("Requested page: " + path + " httpMethod: " + httpMethod + " method: "
							+ method.getName() + " which can be access by " + rolesSet + " from user: "
							+ userInfo.getEmailId() + " having role " + userInfo.getRole().name());
				} else {
					LOGGER.debug(
							"Requested page: " + path + " httpMethod: " + httpMethod + " method: " + method.getName()
									+ " which can be access by " + rolesSet + " from user: " + userInfo.getEmailId());

				}
			}

		} else {

			LOGGER.debug("Requested page: " + path + " httpMethod: " + httpMethod + " method: " + method.getName()
					+ " is permitted for all users");

		}

		if (cachingService.getFromCache(TroikaConstants.MASTER_DATA_CACHE_KEY,
				TroikaConstants.MASTER_DATA_CACHE_VALUE) == null) {

			RestResponse response = masterDataAggService.retrieveMasterData();

			if (response != null && response.getResultStatus() != null && response.getResultStatus().getStatus() != null
					&& response.getResultStatus().getStatus().equals(StatusEnum.SUCCESS)) {

				if (response.getResponseEntity() != null && response.getResponseEntity().getEntity() != null) {

					MasterData masterData = (MasterData) response.getResponseEntity().getEntity();

					List<EducationalDegree> degreeList = masterData.getDegreeList();

					List<State> stateList = masterData.getStateList();

					List<UserType> userTypeList = masterData.getUserTypeList();

					LOGGER.debug("userTypeList: " + userTypeList);

					LOGGER.debug("stateList: " + stateList);

					LOGGER.debug("degreeList: " + degreeList);

					cachingService.putInCache(TroikaConstants.MASTER_DATA_CACHE_KEY, masterData,
							TroikaConstants.MASTER_DATA_CACHE_VALUE);

					LOGGER.debug("cache updated");
				}
			}
		}

	}

	private String fetchEmailId(String authCredentials) {

		String usernameAndPassword = helper.fetchUserNameAndPassword(authCredentials);

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");

		final String userName = tokenizer.nextToken();

		final String password = tokenizer.nextToken();

		LOGGER.debug("retrieving information of user for emailId: " + userName);

		return userName;

	}

	/**
	 * 
	 * @param exception
	 * @return
	 */
	public Response createResponse(Throwable exception) {

		RestResponse restResponse = new RestResponse();

		RestEntity<String> entity = new RestEntity<>();

		if (exception.getCause() != null) {

			entity.setEntity(exception.getCause().getMessage());

		}
		restResponse.setResponseEntity(entity);

		Response response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(restResponse).build();

		return response;

	}

}

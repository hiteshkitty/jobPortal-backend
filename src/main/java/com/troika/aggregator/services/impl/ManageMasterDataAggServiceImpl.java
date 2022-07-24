package com.troika.aggregator.services.impl;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.troika.aggregator.services.ManageMasterDataAggService;
import com.troika.cache.CachingService;
import com.troika.domain.model.City;
import com.troika.domain.model.EducationalDegree;
import com.troika.domain.model.SkillSet;
import com.troika.domain.view.RestResponse;
import com.troika.domain.view.StatusEnum;
import com.troika.helper.JobPortalHelper;
import com.troika.helper.TroikaConstants;
import com.troika.services.CityService;
import com.troika.services.EducationalDegreeService;
import com.troika.services.SkillSetService;

/**
 * TODO
 */
public class ManageMasterDataAggServiceImpl extends BaseCacheServiceImpl implements ManageMasterDataAggService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManageMasterDataAggServiceImpl.class);

	@Autowired
	private EducationalDegreeService degreeService;

	@Autowired
	private CityService cityService;

	@Autowired
	private SkillSetService skillSetService;
	
	@Autowired
	private CachingService cachingService;

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse addSkillSet(SkillSet skillSet) {

		LOGGER.debug("adding new skillSet: ");

		RestResponse response = null;

		if (skillSet.getSkillSetName() == null || skillSet.getSkillSetName().trim().length() == 0) {

			response = JobPortalHelper.createRestResponse("Please provide valid skillSet name", StatusEnum.SUCCESS,
					"Please provide valid skillSet name");

		} else {

			skillSetService.createSkillSet(skillSet);

			response = JobPortalHelper.createRestResponse("Added skillSet successufully.", StatusEnum.SUCCESS,
					"Added skillSet successufully.");
		}

		JobPortalHelper.logPayload(response);

		return response;
	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse addEducationalDegree(EducationalDegree educationalDegree) {

		LOGGER.debug("adding new educationalDegree: ");

		RestResponse response = null;

		if (educationalDegree.getDegreeName() == null || educationalDegree.getDegreeName().trim().length() == 0) {

			response = JobPortalHelper.createRestResponse("Please provide valid educationalDegree name",
					StatusEnum.SUCCESS, "Please provide valid educationalDegree name");

		} else {

			degreeService.postEducationalDegree(educationalDegree);

			response = JobPortalHelper.createRestResponse("Added educationalDegree successufully.", StatusEnum.SUCCESS,
					"Added educationalDegree successufully.");
		}

		JobPortalHelper.logPayload(response);

		return response;
	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse addCity(City city) {

		LOGGER.debug("adding new city: ");

		RestResponse response = null;

		if (city.getCityName() == null || city.getCityName().trim().length() == 0) {

			response = JobPortalHelper.createRestResponse("Please provide valid city name", StatusEnum.SUCCESS,
					"Please provide valid city name");

		} else {

			cityService.postCity(city);

			response = JobPortalHelper.createRestResponse("Added city successufully.", StatusEnum.SUCCESS,
					"Added city successufully.");
		}

		JobPortalHelper.logPayload(response);

		return response;
	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse deleteSkillSet(SkillSet skillSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse deleteEducationalDegree(EducationalDegree educationalDegree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse deleteCity(City city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse changeStatusSkillSet(SkillSet skillSet) {

		LOGGER.trace("Changing status of  skillSet: " + skillSet);

		RestResponse response = null;

		if ((skillSet.getSkillSetName() == null || skillSet.getSkillSetName().trim().length() == 0)
				&& (null == skillSet.getIsActive())) {

			response = JobPortalHelper.createRestResponse("Please provide valid skillset name/status.",
					StatusEnum.FAILURE, "Please provide valid skillset name/status.");
		} else {

			Boolean isStatusChanged = skillSetService.changeStatusSkillSet(skillSet);

			if (isStatusChanged) {

				cachingService.removeFromCache(TroikaConstants.MASTER_DATA_CACHE_KEY,
						TroikaConstants.MASTER_DATA_CACHE_VALUE);
				
				response = JobPortalHelper.createRestResponse("Changed the status of skillset successufully.",
						StatusEnum.SUCCESS, "Changed the status of skillset successufully.");

			} else {

				response = JobPortalHelper.createRestResponse(
						"Couldn't change the status, please verify the input again.", StatusEnum.FAILURE,
						"Couldn't change the status, please verify the input again.");

			}
		}

		JobPortalHelper.logPayload(response);

		return response;
	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse changeStatusEducationalDegree(EducationalDegree educationalDegree) {

		LOGGER.trace("Changing status of  educationalDegree: " + educationalDegree);

		RestResponse response = null;

		if ((educationalDegree.getDegreeName() == null || educationalDegree.getDegreeName().trim().length() == 0)
				&& (null == educationalDegree.getIsActive())) {

			response = JobPortalHelper.createRestResponse("Please provide valid educationalDegree name/status.",
					StatusEnum.FAILURE, "Please provide valid educationalDegree name/status.");
		} else {

			Boolean isStatusChanged = degreeService.changeStatusEducationalDegree(educationalDegree);

			if (isStatusChanged) {

				cachingService.removeFromCache(TroikaConstants.MASTER_DATA_CACHE_KEY,
						TroikaConstants.MASTER_DATA_CACHE_VALUE);
				
				response = JobPortalHelper.createRestResponse("Changed the status of educationalDegree successufully.",
						StatusEnum.SUCCESS, "Changed the status of educationalDegree successufully.");

			} else {

				response = JobPortalHelper.createRestResponse(
						"Couldn't change the status, please verify the input again.", StatusEnum.FAILURE,
						"Couldn't change the status, please verify the input again.");

			}

		}

		JobPortalHelper.logPayload(response);

		return response;
	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse changeStatusCity(City city) {

		LOGGER.trace("Changing status of  city: " + city);

		RestResponse response = null;

		if ((city.getCityName() == null || city.getCityName().trim().length() == 0) && (null == city.getIsActive())) {

			response = JobPortalHelper.createRestResponse("Please provide valid city name/status.", StatusEnum.FAILURE,
					"Please provide valid city name/status.");
		} else {

			Boolean isStatusChanged = cityService.changeStatusCity(city);

			if (isStatusChanged) {

				cachingService.removeFromCache(TroikaConstants.MASTER_DATA_CACHE_KEY,
						TroikaConstants.MASTER_DATA_CACHE_VALUE);
				
				response = JobPortalHelper.createRestResponse("Changed the status of city successufully.",
						StatusEnum.SUCCESS, "Changed the status of city successufully.");

			} else {

				response = JobPortalHelper.createRestResponse(
						"Couldn't change the status, please verify the input again.", StatusEnum.FAILURE,
						"Couldn't change the status, please verify the input again.");

			}

		}

		JobPortalHelper.logPayload(response);

		return response;
	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse updateSkillSet(SkillSet skillSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse updateEducationalDegree(EducationalDegree educationalDegree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse updateCity(City city) {
		// TODO Auto-generated method stub
		return null;
	}

}

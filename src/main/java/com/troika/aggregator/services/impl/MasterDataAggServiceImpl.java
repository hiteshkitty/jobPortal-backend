package com.troika.aggregator.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.aggregator.services.MasterDataAggService;
import com.troika.domain.model.City;
import com.troika.domain.model.Country;
import com.troika.domain.model.EducationalDegree;
import com.troika.domain.model.JobType;
import com.troika.domain.model.Role;
import com.troika.domain.model.SkillSet;
import com.troika.domain.model.State;
import com.troika.domain.model.UserType;
import com.troika.domain.view.JobIndustryTypeData;
import com.troika.domain.view.MasterData;
import com.troika.domain.view.RestResponse;
import com.troika.domain.view.SkillSetData;
import com.troika.domain.view.StatusEnum;
import com.troika.helper.JobPortalHelper;
import com.troika.services.CityService;
import com.troika.services.CountryService;
import com.troika.services.EducationalDegreeService;
import com.troika.services.JobTypeService;
import com.troika.services.RoleService;
import com.troika.services.SkillSetService;
import com.troika.services.StateService;
import com.troika.services.UserTypeService;

@Service
public class MasterDataAggServiceImpl extends BaseCacheServiceImpl implements MasterDataAggService {

	private static final Logger LOGGER = Logger.getLogger(MasterDataAggServiceImpl.class);

	@Autowired
	private StateService stateService;

	@Autowired
	private EducationalDegreeService degreeService;

	@Autowired
	private UserTypeService userTypeService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CityService cityService;

	@Autowired
	private SkillSetService skillSetService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private JobTypeService jobTypeService;

	@Override
	public RestResponse retrieveMasterData() {

		MasterData masterData = new MasterData();

//		LOGGER.trace("fetching states");
//
//		List<State> states = stateService.findAll();
//
//		LOGGER.trace("fetching countries");
//
//		List<Country> countries = countryService.findAll();
//
//		LOGGER.trace("fetching cities");

		List<City> cities = cityService.findAll();

		LOGGER.trace("fetching roles");

		List<Role> roles = roleService.findAll();

		LOGGER.trace("fetching degrees");

		List<EducationalDegree> degrees = degreeService.findAll();

		LOGGER.trace("fetching userType");

		List<UserType> userTypeList = userTypeService.findAll();

		LOGGER.trace("fetching skillSet");

		List<SkillSet> skillList = skillSetService.findAll();

		List<SkillSetData> skillDataList = convertSkillSetToSkillData(skillList);

		LOGGER.trace("fetching jobtype");

		List<JobType> jobList = jobTypeService.findAll();

		List<JobIndustryTypeData> jobTypeDataList = convertJobTypeToJobData(jobList);

		masterData.setJobIndustryTypeList(jobTypeDataList);

		masterData.setSkillSetList(skillDataList);

		masterData.setDegreeList(degrees);

//		masterData.setStateList(states);

		masterData.setCityList(cities);

//		masterData.setCountryList(countries);

		masterData.setRoleList(roles);

		if (userTypeList != null) {

			for (UserType userType : userTypeList) {

				userType.setUserAccounts(null);

			}
			masterData.setUserTypeList(userTypeList);
		}

		RestResponse response = JobPortalHelper.createRestResponse(masterData, StatusEnum.SUCCESS,
				"Master data fetched successfully.");

		JobPortalHelper.logPayload(response);

		return response;

	}

	private List<JobIndustryTypeData> convertJobTypeToJobData(List<JobType> jobList) {

		LOGGER.trace("converting JobType to JobTypeData");

		List<JobIndustryTypeData> jobData = new ArrayList<>();

		if (jobList != null && !jobList.isEmpty()) {

			LOGGER.trace("copying jobList to jobtype data");

			for (JobType jobType : jobList) {

				JobIndustryTypeData data = new JobIndustryTypeData();

				data.setId(jobType.getId());

				data.setJobType(jobType.getJobType());

				jobData.add(data);

			}
		}

		LOGGER.trace("JobType data list: " + jobData);

		return jobData;
	}

	private List<SkillSetData> convertSkillSetToSkillData(List<SkillSet> skillList) {

		LOGGER.trace("converting skillSet to SkillData");

		List<SkillSetData> skillData = new ArrayList<>();

		if (skillList != null && !skillList.isEmpty()) {

			LOGGER.trace("copying skillset to skill data");

			for (SkillSet skill : skillList) {

				SkillSetData data = new SkillSetData();

				data.setSkillSetId(skill.getId());

				data.setSkillName(skill.getSkillSetName());

				skillData.add(data);

			}
		}

		LOGGER.trace("Skill data list: " + skillData);

		return skillData;
	}

}

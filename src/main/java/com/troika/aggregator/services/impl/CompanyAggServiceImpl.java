package com.troika.aggregator.services.impl;

import javax.annotation.security.RolesAllowed;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.troika.aggregator.services.CompanyAggService;
import com.troika.domain.model.BusinessStream;
import com.troika.domain.model.Company;
import com.troika.domain.view.CompanyProfile;
import com.troika.domain.view.RestResponse;
import com.troika.domain.view.StatusEnum;
import com.troika.helper.JobPortalHelper;
import com.troika.helper.TroikaConstants;
import com.troika.services.BusinessStreamService;
import com.troika.services.CompanyService;

@Service
public class CompanyAggServiceImpl extends BaseCacheServiceImpl implements CompanyAggService {

	private static final Logger LOGGER = Logger.getLogger(CompanyAggServiceImpl.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private BusinessStreamService businessStreamService;

	@Override
	@RolesAllowed({ TroikaConstants.ADMIN, TroikaConstants.USER, TroikaConstants.RECRUITER })
	public RestResponse retrieveCompanyProfileById(String companyId) {

		LOGGER.debug("finding company using companyId: " + companyId);

		Integer compId = Integer.valueOf(companyId);

		Company company = companyService.retrieveCompanyById(compId);

		CompanyProfile rp = convertToCompanyProfile(company);

		RestResponse response = JobPortalHelper.createRestResponse(rp, StatusEnum.SUCCESS,
				"found company details successfully.");

		JobPortalHelper.logPayload(response);

		return response;

	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse registerCompanyProfile(CompanyProfile recruiterProfile) {

		LOGGER.debug("creating registerCompanyProfile using: " + recruiterProfile);

		RestResponse response = null;

		try {
			Company companyToRegister = convertToCompany(recruiterProfile);

			Company createdCompany = companyService.registerCompany(companyToRegister);

			CompanyProfile rp = convertToCompanyProfile(createdCompany);

			response = JobPortalHelper.createRestResponse(rp, StatusEnum.SUCCESS, "Created Company successfully");

		} catch (DataIntegrityViolationException ex) {

			ex.printStackTrace();

			response = JobPortalHelper.createRestResponse("Company is already registered with same information.",
					StatusEnum.FAILURE, ex.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();

			response = JobPortalHelper.createRestResponse("Not able to register company.", StatusEnum.FAILURE,
					ex.getMessage());

		}
		JobPortalHelper.logPayload(response);

		return response;
	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse deleteCompanyProfile(String companyId) {

		LOGGER.debug("finding company using companyId: " + companyId);

		Integer compId = Integer.valueOf(companyId);

		Company company = companyService.deleteCompanyById(compId);

		CompanyProfile rp = convertToCompanyProfile(company);

		RestResponse response = JobPortalHelper.createRestResponse(company, StatusEnum.SUCCESS,
				"found compay details successfully.");

		return response;

	}

	@Override
	@RolesAllowed(TroikaConstants.ADMIN)
	public RestResponse updateCompanyProfile(CompanyProfile recruiterProfile) {

		LOGGER.debug("updating recruiterProfile using: " + recruiterProfile);

		Company companyToUpdate = convertToCompany(recruiterProfile);

		Company updatedCompany = companyService.updateCompany(companyToUpdate);

		CompanyProfile rp = convertToCompanyProfile(updatedCompany);

		RestResponse response = JobPortalHelper.createRestResponse(rp, StatusEnum.SUCCESS,
				"Company created successfully.");

		return response;
	}

	private CompanyProfile convertToCompanyProfile(final Company company) {

		LOGGER.debug("request to Convert company: " + company);

		CompanyProfile cp = new CompanyProfile();

		cp.setCompanyId(company.getCompanyId());

		cp.setCompanyName(company.getCompanyName());

		cp.setCompanyWebsite(company.getCompanyWebsiteUrl());

		if (company.getBusinessStream() != null) {

			cp.setCompanyType(company.getBusinessStream().getId());

		}

		cp.setEmailId(company.getEmailId());

		cp.setEstablishmentDate(company.getEstablishmentDate());

		cp.setCity(company.getCity());

		if (company.getPrimaryContactNumber() != null) {

			cp.setPrimaryMobileNumber(company.getPrimaryContactNumber().toString());

		}

		cp.setCompanyProfile(company.getProfileDescription());

		cp.setStreetAddress(company.getCompanyAddress());

		cp.setIsActive(company.getIsActive());

		LOGGER.debug("converted CompanyProfile: " + cp);

		return cp;

	}

	private Company convertToCompany(CompanyProfile cp) {

		LOGGER.debug("request to Convert CompanyProfile: " + cp);

		Company company = new Company();

		if (cp.getCompanyId() != null) {

			company.setCompanyId(cp.getCompanyId());

		}
		company.setCompanyName(cp.getCompanyName());

		company.setCompanyWebsiteUrl(cp.getCompanyWebsite());

		BusinessStream businessStream = getBusinessStream(cp.getCompanyType());

		company.setBusinessStream(businessStream);

		company.setEmailId(cp.getEmailId());

		company.setEstablishmentDate(cp.getEstablishmentDate());

		company.setCity(cp.getCity());

		if (cp.getPrimaryMobileNumber() != null) {

			company.setPrimaryContactNumber(cp.getPrimaryMobileNumber());

		}

		company.setCompanyAddress(cp.getStreetAddress());

		company.setIsActive(cp.getIsActive());

		company.setProfileDescription(cp.getCompanyProfile());

		LOGGER.debug("converted Company: " + company);

		return company;

	}

	private BusinessStream getBusinessStream(Integer type) {

		BusinessStream businessStream = businessStreamService.retrieveBusinessStreamById(type);

		if (businessStream != null) {

			LOGGER.debug("businessStream: " + businessStream);

		}

		return businessStream;

	}
}

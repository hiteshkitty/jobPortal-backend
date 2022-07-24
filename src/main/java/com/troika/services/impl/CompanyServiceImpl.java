package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.dao.CompanyDao;
import com.troika.domain.model.Company;
import com.troika.helper.JobPortalHelper;
import com.troika.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static final Logger LOGGER = Logger.getLogger(CompanyServiceImpl.class);

	@Autowired
	private CompanyDao companyDao;

	@Override
	public Company retrieveCompanyById(Integer companyId) {

		LOGGER.debug("finding company details using companyId: " + companyId);

		Company company = companyDao.retireveCompanyById(companyId);

		return company;
	}

	@Override
	public Company registerCompany(Company company) {

		LOGGER.debug("creating company with details: " + company);

		Company registerCompany = companyDao.createCompany(company);

		LOGGER.debug("registerCompany: " + registerCompany);

		return registerCompany;
	}

	@Override
	public Company deleteCompanyById(Integer compId) {

		LOGGER.debug("deleting company with compId: " + compId);

		// check whether company exists, if then retrieve it and delete it.

		Company companyToDelete = companyDao.retireveCompanyById(compId);

		if (companyToDelete != null) {

			if (companyToDelete.getCompanyId() != 0) {

				LOGGER.debug("deleting company : ");

				companyDao.deleteCompany(companyToDelete);

			}
		}

		return null;
	}

	@Override
	public Company updateCompany(Company company) {

		LOGGER.debug("Updating company with company: " + company);

		// check whether company exists, if then retrieve it and update it.

		Company companyToUpdate = companyDao.retireveCompanyById(company.getCompanyId());

		if (companyToUpdate != null) {

			if (companyToUpdate.getCompanyId() != 0) {

				LOGGER.debug("updating company : ");

				companyToUpdate = JobPortalHelper.copyCompany(company, companyToUpdate);

				companyDao.updateCompany(companyToUpdate);

			}
		}

		return companyToUpdate;
	}

	@Override
	public List<Company> findAll() {
		return companyDao.findAll();
	}

}

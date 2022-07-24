package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.troika.dao.CompanyDao;
import com.troika.domain.model.Company;
import com.troika.repo.CompanyRepo;

@Component
public class CompanyDaoImpl implements CompanyDao {

	private static final Logger LOGGER = Logger.getLogger(CompanyDaoImpl.class);

	@Autowired
	private CompanyRepo companyRepo;

	@Override
	@Cacheable("company")
	public Company retireveCompanyById(Integer companyId) {

		Company comp = companyRepo.findOne(companyId);

		LOGGER.debug("Company: " + comp);

		LOGGER.debug("BusinessUnit: " + comp.getBusinessStream());

		return comp;

	}

	@Override
	public Company retrieveCompanyByName(String companyName) {

		LOGGER.debug("retrieving company details using companyName: " + companyName);

		Company company = null;

		company = companyRepo.findByCompanyName(companyName);

		LOGGER.debug("retrieved CompanyDetails for companyName: " + companyName + " is " + company);

		return company;

	}

	@Override
	public Company createCompany(Company company) {

		LOGGER.debug("creating company details: " + company);

		Company companyCreated = companyRepo.save(company);

		LOGGER.debug("created CompanyDetails: " + companyCreated);

		return companyCreated;

	}

	@Override
	public void deleteCompany(Company companyToDelete) {

		LOGGER.debug("deleting company details: " + companyToDelete);

		companyRepo.delete(companyToDelete);

		LOGGER.debug("created CompanyDetails: " + companyToDelete);

	}

	@Override
	public Company updateCompany(Company companyToUpdate) {

		LOGGER.debug("updating company details: " + companyToUpdate);

		Company companyUpdated = companyRepo.save(companyToUpdate);

		LOGGER.debug("companyUpdated CompanyDetails: " + companyUpdated);

		return companyUpdated;
	}

	@Override
	public List<Company> findAll() {

		List<Company> company = null;

		LOGGER.debug("fetching all Company");

		company = (List) companyRepo.findAll();

		LOGGER.debug("fetch all Company: ");

		return company;
	}

}

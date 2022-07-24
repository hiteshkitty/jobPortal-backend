package com.troika.services;

import java.util.List;

import com.troika.domain.model.Company;

public interface CompanyService {

	Company retrieveCompanyById(final Integer companyId);

	Company registerCompany(Company company);

	Company deleteCompanyById(Integer compId);

	Company updateCompany(Company company);

	List<Company> findAll();

}

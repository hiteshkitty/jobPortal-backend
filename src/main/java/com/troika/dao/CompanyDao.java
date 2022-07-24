package com.troika.dao;

import java.util.List;

import com.troika.domain.model.Company;

public interface CompanyDao {

	Company retireveCompanyById(Integer companyId);

	Company retrieveCompanyByName(String companyName);

	Company createCompany(Company company);

	void deleteCompany(Company companyToDelete);

	Company updateCompany(Company companyToUpdate);

	List<Company> findAll();
}

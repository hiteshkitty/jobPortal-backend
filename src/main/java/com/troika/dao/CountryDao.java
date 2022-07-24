package com.troika.dao;

import java.util.List;

import com.troika.domain.model.Country;

public interface CountryDao {

	Country retireveCountryById(Integer countryId);

	Country createCountry(Country country);

	void deleteCountry(Country countryToDelete);

	Country updateCountry(Country countryToUpdate);

	List<Country> findAll();
}

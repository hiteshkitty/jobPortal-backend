package com.troika.services;

import java.util.List;

import com.troika.domain.model.Country;

public interface CountryService {

	Country retrieveCountryById(final Integer countryId);

	Country postCountry(Country country);

	Country deleteCountryById(Integer compId);

	Country updateCountry(Country country);

	List<Country> findAll();

}

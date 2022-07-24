package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.troika.dao.CountryDao;
import com.troika.domain.model.Country;
import com.troika.repo.CountryRepo;

@Component
public class CountryDaoImpl implements CountryDao {

	private static final Logger LOGGER = Logger.getLogger(CountryDaoImpl.class);

	@Autowired
	private CountryRepo countryRepo;

	@Override
	@Cacheable("country")
	public Country retireveCountryById(Integer countryId) {

		Country comp = countryRepo.findOne(countryId);

		LOGGER.trace("Country: " + comp);

		return comp;

	}

	@Override
	public Country createCountry(Country country) {

		LOGGER.trace("creating country details: " + country);

		Country countryCreated = countryRepo.save(country);

		LOGGER.trace("created CountryDetails: " + countryCreated);

		return countryCreated;

	}

	@Override
	public void deleteCountry(Country countryToDelete) {

		LOGGER.trace("deleting country details: " + countryToDelete);

		countryRepo.delete(countryToDelete);

		LOGGER.trace("created CountryDetails: " + countryToDelete);

	}

	@Override
	public Country updateCountry(Country countryToUpdate) {

		LOGGER.trace("updating country details: " + countryToUpdate);

		Country countryUpdated = countryRepo.save(countryToUpdate);

		LOGGER.trace("countryUpdated CountryDetails: " + countryUpdated);

		return countryUpdated;
	}

	@Override
	public List<Country> findAll() {

		List<Country> country = null;

		LOGGER.trace("fetching all Country");

		country = (List) countryRepo.findAll();

		LOGGER.trace("fetch all Country: ");

		return country;
	}

}

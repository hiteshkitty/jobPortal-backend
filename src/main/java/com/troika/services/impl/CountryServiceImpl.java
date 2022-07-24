package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.troika.dao.CountryDao;
import com.troika.domain.model.Country;
import com.troika.services.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	private static final Logger LOGGER = Logger.getLogger(CountryServiceImpl.class);

	@Autowired
	private CountryDao countryDao;

	@Override
	public Country retrieveCountryById(Integer countryId) {
		Country country = countryDao.retireveCountryById(countryId);

		return country;
	}

	@Override
	public Country postCountry(Country country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country deleteCountryById(Integer countryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country updateCountry(Country country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Cacheable("countries")
	public List<Country> findAll() {

		LOGGER.trace("fetching all countries");

		List<Country> countrys = countryDao.findAll();

		LOGGER.trace("fetched countrys: " + countrys);

		return countrys;

	}

}

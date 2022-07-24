package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.troika.dao.CityDao;
import com.troika.domain.model.City;
import com.troika.services.CityService;

@Service
public class CityServiceImpl implements CityService {

	private static final Logger LOGGER = Logger.getLogger(CityServiceImpl.class);

	@Autowired
	private CityDao cityDao;

	@Override
	public City retrieveCityById(Integer cityId) {
		City city = cityDao.retireveCityById(cityId);

		return city;
	}

	@Override
	public City postCity(City city) {

		City createdCity = cityDao.createCity(city);

		return createdCity;

	}

	@Override
	public Boolean deleteCityById(Integer cityId) {

		Boolean isDeleted = Boolean.FALSE;

		City cityToDelete = cityDao.retireveCityById(cityId);

		if (cityToDelete != null) {

			LOGGER.debug("deleting city: " + cityToDelete.getCityName());

			cityDao.deleteCity(cityToDelete);

			isDeleted = Boolean.TRUE;

		}

		return isDeleted;

	}

	@Override
	public City updateCity(City city) {

		return null;
	}

	@Override
	@Cacheable("cities")
	public List<City> findAll() {

		LOGGER.trace("fetching all cities");

		List<City> cities = cityDao.findAll();

		LOGGER.trace("fetched cities: " + cities);

		return cities;

	}

	@Override
	public Boolean deleteCityByName(String cityName) {

		Boolean isDeleted = Boolean.FALSE;

		City cityToDelete = cityDao.retireveCityByName(cityName);

		if (cityToDelete != null) {

			LOGGER.debug("deleting city: " + cityToDelete.getCityName());

			cityDao.deleteCity(cityToDelete);

			isDeleted = Boolean.TRUE;

		}

		return isDeleted;

	}

	@Override
	public Boolean changeStatusCity(City city) {

		Boolean isStatusChanged = Boolean.FALSE;

		City cityToUpdate = cityDao.retireveCityByName(city.getCityName());

		if (cityToUpdate != null) {

			LOGGER.debug("changing status city: " + cityToUpdate.getCityName());

			cityToUpdate.setIsActive(city.getIsActive());

			cityDao.updateCity(cityToUpdate);

			isStatusChanged = Boolean.TRUE;

		}

		return isStatusChanged;

	}
}

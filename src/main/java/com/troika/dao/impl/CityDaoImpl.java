package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.troika.dao.CityDao;
import com.troika.domain.model.City;
import com.troika.repo.CityRepo;

@Component
public class CityDaoImpl implements CityDao {

	private static final Logger LOGGER = Logger.getLogger(CityDaoImpl.class);

	@Autowired
	private CityRepo cityRepo;

	@Override
	@Cacheable("city")
	public City retireveCityById(Integer cityId) {

		City comp = cityRepo.findOne(cityId);

		LOGGER.trace("City: " + comp);

		return comp;

	}

	@Override
	public City createCity(City city) {

		LOGGER.trace("creating city details: " + city);

		City cityCreated = cityRepo.save(city);

		LOGGER.trace("created CityDetails: " + cityCreated);

		return cityCreated;

	}

	@Override
	public void deleteCity(City cityToDelete) {

		LOGGER.trace("deleting city details: " + cityToDelete);

		cityRepo.delete(cityToDelete);

		LOGGER.trace("created CityDetails: " + cityToDelete);

	}

	@Override
	public City updateCity(City cityToUpdate) {

		LOGGER.trace("updating city details: " + cityToUpdate);

		City cityUpdated = cityRepo.save(cityToUpdate);

		LOGGER.trace("cityUpdated CityDetails: " + cityUpdated);

		return cityUpdated;
	}

	@Override
	public List<City> findAll() {

		List<City> city = null;

		LOGGER.trace("fetching all City");

		city = (List) cityRepo.findByIsActiveTrueOrderByCityName();

		LOGGER.trace("fetch all City: ");

		return city;
	}

	@Override
	public City retireveCityByName(String cityName) {
		
		LOGGER.trace("finding city by name: " + cityName);

		City city = cityRepo.findByCityName(cityName);

		return city;
	}

}

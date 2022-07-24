package com.troika.services;

import java.util.List;

import com.troika.domain.model.City;

public interface CityService {

	City retrieveCityById(final Integer cityId);

	City postCity(City city);

	Boolean deleteCityById(Integer cityId);

	Boolean deleteCityByName(String cityName);
	
	Boolean changeStatusCity(City city);

	City updateCity(City city);

	List<City> findAll();

}

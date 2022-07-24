package com.troika.dao;

import java.util.List;

import com.troika.domain.model.City;

public interface CityDao {

	City retireveCityById(Integer cityId);

	City retireveCityByName(String cityName);

	City createCity(City city);

	void deleteCity(City cityToDelete);

	City updateCity(City cityToUpdate);

	List<City> findAll();
}

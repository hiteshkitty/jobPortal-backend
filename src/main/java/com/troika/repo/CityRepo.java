package com.troika.repo;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.troika.domain.model.City;

@Transactional
@Repository
@Cacheable
public interface CityRepo extends CrudRepository<City, Serializable> {
	
	public City findByCityName(String cityName);
	
	public Iterable<City> findByIsActiveTrueOrderByCityName();

}

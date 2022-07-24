package com.troika.repo;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.troika.domain.model.BusinessStream;

@Transactional
@Repository
public interface BusinessStreamRepo extends CrudRepository<BusinessStream, Serializable> {

	BusinessStream findByBusinessStreamName(String name);
}

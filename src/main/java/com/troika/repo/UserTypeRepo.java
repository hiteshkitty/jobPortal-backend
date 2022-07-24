package com.troika.repo;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.troika.domain.model.UserType;

@Transactional
@Repository
public interface UserTypeRepo extends CrudRepository<UserType, Serializable> {

}

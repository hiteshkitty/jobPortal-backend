package com.troika.services;

import java.util.List;

import com.troika.domain.model.UserType;

public interface UserTypeService {

	UserType retrieveUserTypeById(final Integer userTypeId);

	UserType postUserType(UserType userType);

	UserType deleteUserTypeById(Integer userTypeId);

	UserType updateUserType(UserType userType);

	List<UserType> findAll();

}

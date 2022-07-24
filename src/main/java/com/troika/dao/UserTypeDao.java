package com.troika.dao;

import java.util.List;

import com.troika.domain.model.UserType;

public interface UserTypeDao {

	UserType retireveUserTypeById(Integer userTypeId);

	UserType retrieveUserTypeByName(String userTypeName);

	UserType createUserType(UserType userType);

	void deleteUserType(UserType userTypeToDelete);

	UserType updateUserType(UserType userTypeToUpdate);

	List<UserType> findAll();
}

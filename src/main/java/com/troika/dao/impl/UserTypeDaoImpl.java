package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troika.dao.UserTypeDao;
import com.troika.domain.model.State;
import com.troika.domain.model.UserType;
import com.troika.repo.UserTypeRepo;

@Component
public class UserTypeDaoImpl implements UserTypeDao {

	private static final Logger LOGGER = Logger.getLogger(UserTypeDaoImpl.class);

	@Autowired
	private UserTypeRepo userTypeRepo;

	@Override
	public UserType retireveUserTypeById(Integer userTypeId) {

		UserType userType = userTypeRepo.findOne(userTypeId);

		LOGGER.trace("userType: " + userType);

		return userType;
	}

	@Override
	public UserType retrieveUserTypeByName(String userTypeName) {

		return null;
	}

	@Override
	public UserType createUserType(UserType userType) {

		UserType createdUserType = userTypeRepo.save(userType);
		return createdUserType;
	}

	@Override
	public void deleteUserType(UserType userTypeToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserType updateUserType(UserType userTypeToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserType> findAll() {
		List<UserType> userTypeList = null;

		LOGGER.trace("fetching all userType");

		userTypeList = (List) userTypeRepo.findAll();

		LOGGER.trace("fetch all UserType: ");

		return userTypeList;
	}

}

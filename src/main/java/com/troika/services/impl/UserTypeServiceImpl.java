package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.dao.UserTypeDao;
import com.troika.domain.model.UserType;
import com.troika.services.UserTypeService;

@Service
public class UserTypeServiceImpl implements UserTypeService {

	private static final Logger LOGGER = Logger.getLogger(UserTypeServiceImpl.class);

	@Autowired
	private UserTypeDao userTypeDao;

	@Override
	public UserType retrieveUserTypeById(Integer userTypeId) {
	
		UserType userType = userTypeDao.retireveUserTypeById(userTypeId);

		return userType;
	}

	@Override
	public UserType postUserType(UserType userType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserType deleteUserTypeById(Integer userTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserType updateUserType(UserType userType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserType> findAll() {

		LOGGER.trace("fetching all UserType");

		List<UserType> userTypeList = userTypeDao.findAll();

		LOGGER.trace("fetched states: " + userTypeList);

		return userTypeList;
	}

}

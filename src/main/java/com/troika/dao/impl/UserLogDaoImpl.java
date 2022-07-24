package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.troika.dao.UserLogDao;
import com.troika.domain.model.UserLog;
import com.troika.repo.UserLogRepo;

@Component
public class UserLogDaoImpl implements UserLogDao {

	private static final Logger LOGGER = Logger.getLogger(UserLogDaoImpl.class);

	@Autowired
	private UserLogRepo userLogRepo;

	@Override
	@Cacheable("userLog")
	public UserLog retireveUserLogById(String sessionId) {

		UserLog userLog = userLogRepo.findOne(sessionId);

		LOGGER.debug("UserLog: " + userLog);

		return userLog;

	}

	@Override
	public UserLog createUserLog(UserLog userLog) {

		LOGGER.debug("creating userLog details: " + userLog);

		UserLog userLogCreated = userLogRepo.save(userLog);

		LOGGER.debug("created UserLogDetails: " + userLogCreated);

		return userLogCreated;

	}

	@Override
	public void deleteUserLog(UserLog userLogToDelete) {

		LOGGER.debug("deleting userLog details: " + userLogToDelete);

		userLogRepo.delete(userLogToDelete);

		LOGGER.debug("created UserLogDetails: " + userLogToDelete);

	}

	@Override
	public UserLog updateUserLog(UserLog userLogToUpdate) {

		LOGGER.debug("updating userLog details: " + userLogToUpdate);

		UserLog userLogUpdated = userLogRepo.save(userLogToUpdate);

		LOGGER.debug("userLogUpdated UserLogDetails: " + userLogUpdated);

		return userLogUpdated;
	}

	@Override
	public List<UserLog> findAll() {

		List<UserLog> userLog = null;

		LOGGER.debug("fetching all UserLog");

		userLog = (List) userLogRepo.findAll();

		LOGGER.debug("fetch all UserLog: ");

		return userLog;
	}

}

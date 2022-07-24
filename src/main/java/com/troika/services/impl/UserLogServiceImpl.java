package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troika.dao.UserLogDao;
import com.troika.domain.model.UserLog;
import com.troika.services.UserLogService;

@Service
public class UserLogServiceImpl implements UserLogService {

	private static final Logger LOGGER = Logger.getLogger(UserLogServiceImpl.class);

	@Autowired
	private UserLogDao userLogDao;

	@Override
	public UserLog retrieveUserLogById(String sessionId) {

		LOGGER.debug("finding userLog details using userLogId: " + sessionId);

		UserLog userLog = userLogDao.retireveUserLogById(sessionId);

		return userLog;
	}

	@Override
	public UserLog createUserLog(UserLog userLog) {

		LOGGER.debug("creating userLog with details: " + userLog);

		UserLog registerUserLog = userLogDao.createUserLog(userLog);

		LOGGER.debug("registerUserLog: " + registerUserLog);

		return registerUserLog;
	}

	@Override
	public UserLog deleteUserLogById(String sessionId) {

		LOGGER.debug("deleting userLog with compId: " + sessionId);

		// check whether userLog exists, if then retrieve it and delete it.

		UserLog userLogToDelete = userLogDao.retireveUserLogById(sessionId);

		if (userLogToDelete != null) {

			if (userLogToDelete.getSessionId() != null) {

				LOGGER.debug("deleting userLog : ");

				userLogDao.deleteUserLog(userLogToDelete);

			}
		}

		return null;
	}

	@Override
	public List<UserLog> findAll() {
		return userLogDao.findAll();
	}

}

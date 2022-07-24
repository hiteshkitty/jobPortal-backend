package com.troika.services;

import java.util.List;

import com.troika.domain.model.UserLog;

public interface UserLogService {

	UserLog retrieveUserLogById(final String sessionId);

	UserLog createUserLog(UserLog userLog);

	UserLog deleteUserLogById(String compId);

	List<UserLog> findAll();

}

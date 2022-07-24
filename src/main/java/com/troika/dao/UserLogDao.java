package com.troika.dao;

import java.util.List;

import com.troika.domain.model.UserLog;

public interface UserLogDao {

	UserLog retireveUserLogById(String sessionId);

	UserLog createUserLog(UserLog userLog);

	void deleteUserLog(UserLog userLogToDelete);

	UserLog updateUserLog(UserLog userLogToUpdate);

	List<UserLog> findAll();
}

package com.troika.dao;

import java.util.List;

import com.troika.domain.model.UserAccount;

public interface UserAccountDao {

	// UserAccount retireveUserAccountById(Integer userAccountId);

	UserAccount retireveUserAccountByEmailId(String emailId);

	UserAccount createUserAccount(UserAccount userAccount);

	void deleteUserAccount(UserAccount userAccountToDelete);

	UserAccount updateUserAccount(UserAccount userAccountToUpdate);

	List<UserAccount> findAll();

	UserAccount retireveUserAccountBasicInfo(String emailId);

	List<String> getAllAdminEmailIds();

}

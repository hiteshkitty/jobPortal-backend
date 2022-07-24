package com.troika.repo;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.troika.domain.model.RoleEnum;
import com.troika.domain.model.UserAccount;

@Transactional
@Repository
public interface UserAccountRepo extends CrudRepository<UserAccount, Serializable> {

	public UserAccount findByEmailId(String emailId);

	List<UserAccount> findByRoleAndIsActiveTrue(RoleEnum role);
	
}

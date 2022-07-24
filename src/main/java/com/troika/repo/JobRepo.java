package com.troika.repo;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.troika.domain.model.Job;
import com.troika.domain.model.UserAccount;

@Transactional
@Repository
public interface JobRepo extends CrudRepository<Job, Serializable> {

	public List<Job> findByUserAccount(UserAccount userAccount);

	public List<Job> findFirst4ByOrderByCreatedDateDesc();
}

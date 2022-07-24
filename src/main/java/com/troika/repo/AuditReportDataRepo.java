package com.troika.repo;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.troika.domain.model.AuditReportData;

@Transactional
@Repository
@Cacheable
public interface AuditReportDataRepo extends CrudRepository<AuditReportData, Serializable> {

}

package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.troika.dao.AuditReportDataDao;
import com.troika.domain.model.AuditReportData;
import com.troika.repo.AuditReportDataRepo;

@Component
public class AuditReportDataDaoImpl implements AuditReportDataDao {

	private static final Logger LOGGER = Logger.getLogger(AuditReportDataDaoImpl.class);

	@Autowired
	private AuditReportDataRepo auditReportDataRepo;

	@Override
	@Cacheable("auditReportData")
	public AuditReportData retireveAuditReportDataById(Integer auditReportDataId) {

		AuditReportData comp = auditReportDataRepo.findOne(auditReportDataId);

		LOGGER.trace("AuditReportData: " + comp);

		return comp;

	}

	@Override
	public AuditReportData createAuditReportData(AuditReportData auditReportData) {

		LOGGER.trace("creating auditReportData details: " + auditReportData);

		AuditReportData auditReportDataCreated = auditReportDataRepo.save(auditReportData);

		LOGGER.trace("created AuditReportDataDetails: " + auditReportDataCreated);

		return auditReportDataCreated;

	}

	@Override
	public void deleteAuditReportData(AuditReportData auditReportDataToDelete) {

		LOGGER.trace("deleting auditReportData details: " + auditReportDataToDelete);

		auditReportDataRepo.delete(auditReportDataToDelete);

		LOGGER.trace("created AuditReportDataDetails: " + auditReportDataToDelete);

	}

	@Override
	public AuditReportData updateAuditReportData(AuditReportData auditReportDataToUpdate) {

		LOGGER.trace("updating auditReportData details: " + auditReportDataToUpdate);

		AuditReportData auditReportDataUpdated = auditReportDataRepo.save(auditReportDataToUpdate);

		LOGGER.trace("auditReportDataUpdated AuditReportDataDetails: " + auditReportDataUpdated);

		return auditReportDataUpdated;
	}

	@Override
	public List<AuditReportData> findAll() {

		List<AuditReportData> auditReportData = null;

		LOGGER.trace("fetching all AuditReportData");

		auditReportData = (List<AuditReportData>) auditReportDataRepo.findAll();

		LOGGER.trace("fetch all AuditReportData: ");

		return auditReportData;
	}

	@Override
	public AuditReportData retireveAuditReportDataByName(String auditReportDataName) {

		LOGGER.trace("finding auditReportData by name: " + auditReportDataName);

		AuditReportData auditReportData = null;// auditReportDataRepo.findByAuditReportDataName(auditReportDataName);

		return auditReportData;
	}

}

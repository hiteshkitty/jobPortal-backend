package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.troika.domain.model.AuditReportData;
import com.troika.services.AuditReportDataService;

@Service
public class AuditReportDataServiceImpl implements AuditReportDataService {

	private static final Logger LOGGER = Logger.getLogger(AuditReportDataServiceImpl.class);

	@Override
	public AuditReportData retrieveAuditReportDataById(Integer auditReportDataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuditReportData postAuditReportData(AuditReportData auditReportData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteAuditReportDataById(Integer auditReportDataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteAuditReportDataByName(String auditReportDataName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean changeStatusAuditReportData(AuditReportData auditReportData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuditReportData updateAuditReportData(AuditReportData auditReportData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuditReportData> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

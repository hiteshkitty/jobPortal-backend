package com.troika.services;

import java.util.List;

import com.troika.domain.model.AuditReportData;

public interface AuditReportDataService {

	AuditReportData retrieveAuditReportDataById(final Integer auditReportDataId);

	AuditReportData postAuditReportData(AuditReportData auditReportData);

	Boolean deleteAuditReportDataById(Integer auditReportDataId);

	Boolean deleteAuditReportDataByName(String auditReportDataName);
	
	Boolean changeStatusAuditReportData(AuditReportData auditReportData);

	AuditReportData updateAuditReportData(AuditReportData auditReportData);

	List<AuditReportData> findAll();

}

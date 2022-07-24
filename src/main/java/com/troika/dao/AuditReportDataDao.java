package com.troika.dao;

import java.util.List;

import com.troika.domain.model.AuditReportData;

public interface AuditReportDataDao {

	AuditReportData retireveAuditReportDataById(Integer auditReportDataId);

	AuditReportData retireveAuditReportDataByName(String auditReportDataName);

	AuditReportData createAuditReportData(AuditReportData auditReportData);

	void deleteAuditReportData(AuditReportData auditReportDataToDelete);

	AuditReportData updateAuditReportData(AuditReportData auditReportDataToUpdate);

	List<AuditReportData> findAll();
}

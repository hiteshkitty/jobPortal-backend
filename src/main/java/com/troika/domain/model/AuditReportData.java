package com.troika.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "AuditReportData")
@NamedQuery(name = "AuditReportData.findAll", query = "SELECT u FROM AuditReportData u")
public class AuditReportData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uidPK;

	private Date dateCreated;

	private String createdDate;

	private String createdTime;

	private Date dateUpdated;

	private String updatedDate;

	private String updatedTime;

	private String requesterEmail;

	private String operation;

	private String processingCode;

	public AuditReportData() {
		super();
	}

	public AuditReportData(final int uidPK, final Date dateCreated, final String createdDate, final String createdTime,
			final Date dateUpdated, final String updatedDate, final String updatedTime, final String requesterEmail,
			final String operation, final String processingCode) {
		super();
		this.uidPK = uidPK;
		this.dateCreated = dateCreated;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.dateUpdated = dateUpdated;
		this.updatedDate = updatedDate;
		this.updatedTime = updatedTime;
		this.requesterEmail = requesterEmail;
		this.operation = operation;
		this.processingCode = processingCode;
	}

	/**
	 * @return the uidPK
	 */
	public int getUidPK() {
		return uidPK;
	}

	/**
	 * @param uidPK
	 *            the uidPK to set
	 */
	public void setUidPK(int uidPK) {
		this.uidPK = uidPK;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdTime
	 */
	public String getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime
	 *            the createdTime to set
	 */
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the dateUpdated
	 */
	public Date getDateUpdated() {
		return dateUpdated;
	}

	/**
	 * @param dateUpdated
	 *            the dateUpdated to set
	 */
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	/**
	 * @return the updatedDate
	 */
	public String getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the updatedTime
	 */
	public String getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * @param updatedTime
	 *            the updatedTime to set
	 */
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	/**
	 * @return the requesterEmail
	 */
	public String getRequesterEmail() {
		return requesterEmail;
	}

	/**
	 * @param requesterEmail
	 *            the requesterEmail to set
	 */
	public void setRequesterEmail(String requesterEmail) {
		this.requesterEmail = requesterEmail;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return the processingCode
	 */
	public String getProcessingCode() {
		return processingCode;
	}

	/**
	 * @param processingCode
	 *            the processingCode to set
	 */
	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LinkAuditReportData [uidPK=" + uidPK + ", dateCreated=" + dateCreated + ", createdDate=" + createdDate
				+ ", createdTime=" + createdTime + ", dateUpdated=" + dateUpdated + ", updatedDate=" + updatedDate
				+ ", updatedTime=" + updatedTime + ", requesterEmail=" + requesterEmail + ", operation=" + operation
				+ ", processingCode=" + processingCode + "]";
	}

}

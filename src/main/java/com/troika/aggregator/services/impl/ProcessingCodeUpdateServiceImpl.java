package com.troika.aggregator.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.troika.aggregator.services.ProcessingCodeUpdateService;

public class ProcessingCodeUpdateServiceImpl implements ProcessingCodeUpdateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessingCodeUpdateServiceImpl.class);

	private Map<String, String> messages = new HashMap<String, String>();

	public String processingCodeUpdateNew(final String pCode) {

		String response = null; //processingCodeUpdate(pCode);

		return response;
	}

	// public String processingCodeUpdate(final String processingCode) {
	// LOGGER.debug("Processing Code..");
	//
	// final ResultStatus resultStatus = new ResultStatus();
	//
	// processingCodeUpdateResponse = new ProcessingCodeUpdateResponse();
	//
	// processingCodeUpdateResponse.setResultStatus(resultStatus);
	//
	// initializeMessages();
	//
	// LinkAuditReportData linkAuditReportData = null;
	// try {
	// validateRequest(processingCode);
	//
	// // get link information from table X_LINK_AUDIT_STR
	// LOGGER.info(" Retrieving Link Audit records from database table
	// X_LINK_AUDIT_STR ");
	// linkAuditReportData =
	// linkProcessingAuditService.readLinkAuditRecord(processingCode);
	// LOGGER.info("Link Audit record return from database: " +
	// linkAuditReportData);
	//
	// if (linkAuditReportData != null) {
	// if (linkAuditReportData.getDateCreated() != null
	// &&
	// DigiUtil.checkIfDateOlderThan24hrs(linkAuditReportData.getDateCreated()))
	// {
	//
	// LOGGER.info("request is older than 24 hrs");
	//
	// processingCodeUpdateResponse.getResultStatus().setStatusCode(OCSServicesConstants.RESPONSE_FAILURE);
	//
	// processingCodeUpdateResponse
	// .setTextMessage("The link has expired after 24 hrs, please try again from
	// the start");
	//
	// } else if (linkAuditReportData.getDateUpdated() != null) {
	//
	// processingCodeUpdateResponse.getResultStatus().setStatusCode(OCSServicesConstants.RESPONSE_FAILURE);
	//
	// processingCodeUpdateResponse.setTextMessage("The link has expired as it's
	// already been used");
	//
	// } else if
	// (linkAuditReportData.getOperation().equalsIgnoreCase(OCSServicesConstants.IR_SERVICE))
	// {
	//
	// accountHelper.processEnableIRThroughLinkRequest(linkAuditReportData);
	//
	// Integer status =
	// linkProcessingAuditService.updateLinkAuditRecord(processingCode);
	//
	// processingCodeUpdateResponse.getResultStatus().setStatusCode(OCSServicesConstants.RESPONSE_SUCCESS);
	//
	// processingCodeUpdateResponse.setTextMessage("IR setting completed
	// successfully.");
	//
	// } else if
	// (linkAuditReportData.getOperation().equalsIgnoreCase(OCSServicesConstants.LINK_A_MSISDN))
	// {
	//
	// LOGGER.info("Request came for linking a msisdn");
	//
	// // invoke actual metho to do the processing
	//
	// MsisdnAddResponse addResponse =
	// accountHelper.processAddMsisdnRequest(linkAuditReportData);
	//
	// if (addResponse.getResultStatus().getStatusCode()
	// .equalsIgnoreCase(OCSServicesConstants.RESPONSE_SUCCESS)) {
	//
	// Integer status =
	// linkProcessingAuditService.updateLinkAuditRecord(processingCode);
	//
	// processingCodeUpdateResponse.getResultStatus()
	// .setStatusCode(OCSServicesConstants.RESPONSE_SUCCESS);
	//
	// processingCodeUpdateResponse.setTextMessage("Linking of a plan/msisdn
	// successfully completed.");
	//
	// } else {
	//
	// LOGGER.info("Linking a msisdn failed");
	//
	// processingCodeUpdateResponse.getResultStatus()
	// .setStatusCode(OCSServicesConstants.RESPONSE_FAILURE);
	//
	// processingCodeUpdateResponse.setTextMessage(addResponse.getTextMessage());
	//
	// accountHelper.sendFailureEmail(linkAuditReportData,
	// processingCodeUpdateResponse.getTextMessage());
	//
	// accountHelper.sendFailureSms(linkAuditReportData,
	// processingCodeUpdateResponse.getTextMessage());
	//
	// }
	//
	// } else if (linkAuditReportData.getOperation()
	// .equalsIgnoreCase(OCSServicesConstants.ADD_TELENOR_CRM_EMAIL)) {
	//
	// LOGGER.info("Request came for adding Telenor and CRM Email");
	//
	// // invoke actual metho to do the processing
	//
	// EmailAddResponse emailAddResponse = accountHelper
	// .processAddTelenorCrmEmailRequest(linkAuditReportData);
	//
	// if (emailAddResponse.getResultStatus().getStatusCode()
	// .equalsIgnoreCase(OCSServicesConstants.RESPONSE_SUCCESS)) {
	//
	// Integer status =
	// linkProcessingAuditService.updateLinkAuditRecord(processingCode);
	//
	// processingCodeUpdateResponse.getResultStatus()
	// .setStatusCode(OCSServicesConstants.RESPONSE_SUCCESS);
	//
	// processingCodeUpdateResponse
	// .setTextMessage("Login and billing updation request successfully
	// completed.");
	//
	// } else {
	//
	// LOGGER.info("Login and billing updation request failed.");
	//
	// processingCodeUpdateResponse.getResultStatus()
	// .setStatusCode(OCSServicesConstants.RESPONSE_FAILURE);
	//
	// processingCodeUpdateResponse.setTextMessage(emailAddResponse.getTextMessage());
	//
	// accountHelper.sendFailureEmail(linkAuditReportData,
	// processingCodeUpdateResponse.getTextMessage());
	//
	// accountHelper.sendFailureSms(linkAuditReportData,
	// processingCodeUpdateResponse.getTextMessage());
	// }
	//
	// } else if
	// (linkAuditReportData.getOperation().equalsIgnoreCase(OCSServicesConstants.UPDATE_CRM_EMAIL))
	// {
	//
	// LOGGER.info("Request came for updating CRM email");
	//
	// // invoke actual metho to do the processing
	//
	// accountHelper.processUpdateCrmEmailRequest(linkAuditReportData);
	//
	// Integer status =
	// linkProcessingAuditService.updateLinkAuditRecord(processingCode);
	//
	// processingCodeUpdateResponse.getResultStatus().setStatusCode(OCSServicesConstants.RESPONSE_SUCCESS);
	//
	// processingCodeUpdateResponse
	// .setTextMessage("Billing email address updation successfully
	// completed.");
	//
	// } else if (linkAuditReportData.getOperation()
	// .equalsIgnoreCase(OCSServicesConstants.ADD_TELENOR_EMAIL)) {
	//
	// LOGGER.info("Request came for adding Telenor Email");
	//
	// // invoke actual metho to do the processing
	//
	// EmailAddResponse emailAddResponse = accountHelper
	// .processAddTelenorEmailRequest(linkAuditReportData);
	//
	// if (emailAddResponse.getResultStatus().getStatusCode()
	// .equalsIgnoreCase(OCSServicesConstants.RESPONSE_SUCCESS)) {
	//
	// Integer status =
	// linkProcessingAuditService.updateLinkAuditRecord(processingCode);
	//
	// processingCodeUpdateResponse.getResultStatus()
	// .setStatusCode(OCSServicesConstants.RESPONSE_SUCCESS);
	//
	// processingCodeUpdateResponse.setTextMessage(
	// "The request for updating login address has been successfully
	// completed.");
	//
	// } else {
	//
	// LOGGER.info("The request for updating login address has been failed.");
	//
	// processingCodeUpdateResponse.getResultStatus()
	// .setStatusCode(OCSServicesConstants.RESPONSE_FAILURE);
	//
	// processingCodeUpdateResponse.setTextMessage(emailAddResponse.getTextMessage());
	//
	// accountHelper.sendFailureEmail(linkAuditReportData,
	// processingCodeUpdateResponse.getTextMessage());
	//
	// accountHelper.sendFailureSms(linkAuditReportData,
	// processingCodeUpdateResponse.getTextMessage());
	//
	// }
	//
	// }
	//
	// } else {
	//
	// processingCodeUpdateResponse.getResultStatus().setStatusCode(OCSServicesConstants.RESPONSE_FAILURE);
	//
	// processingCodeUpdateResponse.setTextMessage("Could not fetch the data,
	// thus operation failed");
	//
	// LOGGER.info("couldn't find processing code in the database for this
	// operation");
	//
	// }
	//
	// } catch (final Exception ex) {
	//
	// LOGGER.error("Error while processing code.", ex);
	//
	// processingCodeUpdateResponse.getResultStatus().setStatusCode(OCSServicesConstants.RESPONSE_FAILURE);
	//
	// processingCodeUpdateResponse.setTextMessage("Could not fetch the data,
	// thus operation failed");
	//
	// LOGGER.info("couldn't find processing code in the database for this
	// operation");
	// }
	//
	// String response = createResponse(processingCodeUpdateResponse,
	// linkAuditReportData);
	//
	// LOGGER.debug("processingRequest response: " + response);
	//
	// return response;
	//
	// }

	private String checkNullBeforeTypeCastingToString(Object obj) {
		if (obj != null) {
			return obj.toString();
		} else {
			return "";
		}
	}

	private void validateRequest(final String processingCode) {
		if (isNullOrEmptyString(processingCode)) {
			throw new IllegalArgumentException("ProcessingCode cannot be blank");
		}
	}

	private boolean isNullOrEmptyString(final String str) {
		return str == null || "".equals(str);
	}

	private void initializeMessages() {
		// messages.put(OCSServicesConstants.ADD_TELENOR_CRM_EMAIL, "Updating
		// login and billing address");
		//
		// messages.put(OCSServicesConstants.ADD_TELENOR_EMAIL, "Updating login
		// address");
		//
		// messages.put(OCSServicesConstants.LINK_A_MSISDN, "Linking a plan");
		//
		// messages.put(OCSServicesConstants.IR_SERVICE, "Enabling internation
		// roaming ");
		//
		// messages.put(OCSServicesConstants.UPDATE_CRM_EMAIL, "Updating billing
		// address");

	}

	/**
	 * Returns the message to be returned after processing the processing code.
	 *
	 * @param processingCodeUpdateResponse
	 * @param data
	 * @return
	 */
	// private String createResponse(final ProcessingCodeUpdateResponse
	// processingCodeUpdateResponse,
	// final LinkAuditReportData data) {
	// StringBuffer message = new StringBuffer();
	//
	// message.append("<i>Dear Valued Customer, your request for <b>" +
	// messages.get(data.getOperation()) + "</b>");
	// if (processingCodeUpdateResponse.getResultStatus() != null &
	// processingCodeUpdateResponse.getResultStatus()
	// .getStatusCode().equalsIgnoreCase(OCSServicesConstants.RESPONSE_SUCCESS))
	// {
	// message.append(" has been successfully completed");
	// } else {
	// message.append(" has been failed, due to the following reason :");
	// message.append(processingCodeUpdateResponse.getTextMessage());
	// }
	// message.append("<br />");
	// message.append("<br />");
	// message.append("Thank you for your support.");
	// message.append("<br />");
	// message.append("<br />");
	// message.append("Best regards,");
	// message.append("<br />");
	// message.append("Digi Telecommunications</i>");
	//
	// return message.toString();
	// }

	@Override
	public String pcodeUpdate(String pCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pcodeUpdateNew(String pCode) {
		// TODO Auto-generated method stub
		return null;
	}

}

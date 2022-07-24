package com.troika.domain.view;

import java.io.Serializable;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class EmailNotificationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String[] toEmail;

	private String[] ccEmail;

	private String[] bccEmail;

	private String emailSubject;

	private String emailContent;

	private NotificationTypeEnum notificationTypeEnum;

	/**
	 * @return the toEmail
	 */
	public String[] getToEmail() {
		return toEmail;
	}

	/**
	 * @param toEmail
	 *            the toEmail to set
	 */
	public void setToEmail(String[] toEmail) {
		this.toEmail = toEmail;
	}

	/**
	 * @return the ccEmail
	 */
	public String[] getCcEmail() {
		return ccEmail;
	}

	/**
	 * @param ccEmail
	 *            the ccEmail to set
	 */
	public void setCcEmail(String[] ccEmail) {
		this.ccEmail = ccEmail;
	}

	/**
	 * @return the bccEmail
	 */
	public String[] getBccEmail() {
		return bccEmail;
	}

	/**
	 * @param bccEmail
	 *            the bccEmail to set
	 */
	public void setBccEmail(String[] bccEmail) {
		this.bccEmail = bccEmail;
	}

	/**
	 * @return the emailSubject
	 */
	public String getEmailSubject() {
		return emailSubject;
	}

	/**
	 * @param emailSubject
	 *            the emailSubject to set
	 */
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	/**
	 * @return the emailContent
	 */
	public String getEmailContent() {
		return emailContent;
	}

	/**
	 * @param emailContent
	 *            the emailContent to set
	 */
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	/**
	 * @return the notificationTypeEnum
	 */
	public NotificationTypeEnum getNotificationTypeEnum() {
		return notificationTypeEnum;
	}

	/**
	 * @param notificationTypeEnum
	 *            the notificationTypeEnum to set
	 */
	public void setNotificationTypeEnum(NotificationTypeEnum notificationTypeEnum) {
		this.notificationTypeEnum = notificationTypeEnum;
	}

	/**
	 * @return
	 */
	@Override
	public String toString() {
		return "EmailNotificationRequest [toEmail=" + Arrays.toString(toEmail) + ", ccEmail=" + Arrays.toString(ccEmail)
				+ ", bccEmail=" + Arrays.toString(bccEmail) + ", emailSubject=" + emailSubject + ", emailContent="
				+ emailContent + ", notificationTypeEnum=" + notificationTypeEnum + "]";
	}

}

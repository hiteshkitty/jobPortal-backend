package com.troika.exceptionhandling;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * TODO
 */
@XmlRootElement
public class ErrorResponse {
	private String errorCode;

	private String message;

	public ErrorResponse() {

	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public ErrorResponse(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}

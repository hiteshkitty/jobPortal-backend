package com.troika.helper;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author hiteshs
 *
 */
@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class GenericException extends Throwable implements Serializable {

	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatusCode;

	private final String errorCode;

	private final String errorMessage;

	public GenericException() {
		// TODO Auto-generated constructor stub
		httpStatusCode = null;

		errorCode = null;

		errorMessage = null;
	}

	/**
	 * Instantiates a new IpcentralAdapterException exception.
	 * 
	 * @param errorCode
	 *            - Constructor for IpcentralAdapterException to Instantiates a
	 *            new IpcentralAdapterException exception.
	 * @param errorMessage
	 *            - Constructor for IpcentralAdapterException to Instantiates a
	 *            new IpcentralAdapterException exception.
	 * @param httpStatusCode
	 *            - Constructor for IpcentralAdapterException to Instantiates a
	 *            new IpcentralAdapterException exception.
	 */
	public GenericException(final String errorCode, final String errorMessage, final HttpStatus httpStatusCode) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.httpStatusCode = httpStatusCode;
	}

	/**
	 * @return the httpStatusCode
	 */
	public HttpStatus getHttpStatusCode() {
		return httpStatusCode;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

}

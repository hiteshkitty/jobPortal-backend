package com.troika.domain.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class RestResponse implements Serializable {


	private static final long serialVersionUID = 1L;

	@XmlElement(name = "resultStatus", required = true)
	private ResultStatus resultStatus = new ResultStatus();

	@XmlElement(name = "responseEntity", required = true)
	private RestEntity<?> responseEntity;

	/**
	 * @return the resultStatus
	 */
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	/**
	 * @return the responseEntity
	 */
	public RestEntity<?> getResponseEntity() {
		return responseEntity;
	}

	/**
	 * @param responseEntity the responseEntity to set
	 */
	public void setResponseEntity(RestEntity<?> responseEntity) {
		this.responseEntity = responseEntity;
	}


}

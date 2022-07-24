package com.troika.domain.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author hiteshs
 *
 */
@XmlRootElement
public class ProcessingCodeUpdateResponse implements Serializable {

	private static final long serialVersionUID = 1676500371425611993L;

	private ResultStatus resultStatus;

	private String textMessage;

	/**
	 * @return the resultStatus
	 */
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	/**
	 * @param resultStatus
	 *            the resultStatus to set
	 */
	public void setResultStatus(final ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	/**
	 * @return the textMessage
	 */
	public String getTextMessage() {
		return textMessage;
	}

	/**
	 * @param textMessage
	 *            the textMessage to set
	 */
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

}

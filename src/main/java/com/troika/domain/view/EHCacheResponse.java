package com.troika.domain.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EHCacheResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1624971991420250325L;
	Boolean isSucessful;

	public Boolean getIsSucessful() {
		return isSucessful;
	}

	public void setIsSucessful(Boolean isSucessful) {
		this.isSucessful = isSucessful;
	}
	
	
}

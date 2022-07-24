package com.troika.domain.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)

public class RestEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private T t;

	public T getEntity() {
		return t;
	}

	public void setEntity(T t) {
		this.t = t;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return "RestEntity [t=" + t + "]";
	}

}

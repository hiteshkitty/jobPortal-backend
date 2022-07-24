package com.troika.domain.view;

import java.io.Serializable;

/**
 * TODO
 */
public class EHCacheRequest implements Serializable {

	private static final long serialVersionUID = 4817713805565149942L;

	String token;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return
	 */
	@Override
	public String toString() {
		return "EHCacheRequest [token=" + token + "]";
	}

}

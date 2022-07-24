package com.troika.domain.view;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class UserProfileListResponse implements Serializable {

	private static final long serialVersionUID = 1l;

	private List<UserProfileResponse> userProfileList;

	/**
	 * @return the userProfileList
	 */
	public List<UserProfileResponse> getUserProfileList() {
		return userProfileList;
	}

	/**
	 * @param userProfileList
	 *            the userProfileList to set
	 */
	public void setUserProfileList(List<UserProfileResponse> userProfileList) {
		this.userProfileList = userProfileList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserProfileListResponse [userProfileList=" + userProfileList + "]";
	}

}

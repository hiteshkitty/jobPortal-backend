package com.troika.domain.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class SeekerListResponse implements Serializable {

	private static final long serialVersionUID = 1l;

	private List<Seeker> seekerList = new ArrayList<>();

	/**
	 * @return the seekerList
	 */
	public List<Seeker> getSeekerList() {
		return seekerList;
	}

	/**
	 * @param seekerList
	 *            the seekerList to set
	 */
	public void setSeekerList(List<Seeker> seekerList) {
		this.seekerList = seekerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SeekerListResponse [seekerList=" + seekerList + "]";
	}

}

package com.troika.domain.view;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchRequest implements Serializable {

	private static final long serialVersionUID = 2366322632613032176L;

	private List<Integer> skillSetList;

	private int experience;

	private String Location;

	public SearchRequest() {

	}

	/**
	 * @return the skillSetList
	 */
	public List<Integer> getSkillSetList() {
		return skillSetList;
	}

	/**
	 * @param skillSetList
	 *            the skillSetList to set
	 */
	public void setSkillSetList(List<Integer> skillSetList) {
		this.skillSetList = skillSetList;
	}

	/**
	 * @return the experience
	 */
	public int getExperience() {
		return experience;
	}

	/**
	 * @param experience
	 *            the experience to set
	 */
	public void setExperience(int experience) {
		this.experience = experience;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return Location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		Location = location;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobSearchRequest [skillSetList=" + skillSetList + ", experience=" + experience + ", Location="
				+ Location + "]";
	}

}

package com.troika.domain.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SkillSetData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer skillSetId;

	private String skillName;

	/**
	 * @return the skillSetId
	 */
	public Integer getSkillSetId() {
		return skillSetId;
	}

	/**
	 * @param skillSetId
	 *            the skillSetId to set
	 */
	public void setSkillSetId(Integer skillSetId) {
		this.skillSetId = skillSetId;
	}

	/**
	 * @return the skillName
	 */
	public String getSkillName() {
		return skillName;
	}

	/**
	 * @param skillName
	 *            the skillName to set
	 */
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SkillSetData [skillSetId=" + skillSetId + ", skillName=" + skillName + "]";
	}

}

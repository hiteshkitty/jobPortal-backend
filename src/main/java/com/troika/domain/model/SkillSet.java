package com.troika.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the skill_set database table.
 * 
 */
@Entity
@Table(name = "SKILL_SET", uniqueConstraints = @UniqueConstraint(columnNames = { "skillSetName" }))
@NamedQuery(name = "SkillSet.findAll", query = "SELECT s FROM SkillSet s")
@XmlRootElement

public class SkillSet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String skillSetName;

	private Boolean isActive = Boolean.TRUE;

	// bi-directional many-to-one association to JobSkillSet
	// @OneToMany(mappedBy="skillSet")
	// private List<JobSkillSet> jobSkillSets;

	// bi-directional many-to-one association to SeekerSkillSet
	@OneToMany(mappedBy = "skillSet", fetch = FetchType.EAGER)
	private List<SeekerSkillSet> seekerSkillSets;

	public SkillSet() {
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the seekerSkillSets
	 */
	public List<SeekerSkillSet> getSeekerSkillSets() {
		return seekerSkillSets;
	}

	/**
	 * @param seekerSkillSets
	 *            the seekerSkillSets to set
	 */
	public void setSeekerSkillSets(List<SeekerSkillSet> seekerSkillSets) {
		this.seekerSkillSets = seekerSkillSets;
	}

	/**
	 * @return the skillSetName
	 */
	public String getSkillSetName() {
		return skillSetName;
	}

	/**
	 * @param skillSetName
	 *            the skillSetName to set
	 */
	public void setSkillSetName(String skillSetName) {
		this.skillSetName = skillSetName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SkillSet [id=" + id + ", skillSetName=" + skillSetName + " isActive=" + isActive + "]";
	}

}
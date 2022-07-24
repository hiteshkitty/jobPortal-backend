package com.troika.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the seeker_skill_set database table.
 * 
 */
@Entity
@Table(name = "SEEKER_SKILL_SET", uniqueConstraints = @UniqueConstraint(columnNames = { "SEEKER_SKILL_ID", "SEEKER_PROFILE_ID" }))
@NamedQuery(name = "SeekerSkillSet.findAll", query = "SELECT s FROM SeekerSkillSet s")
public class SeekerSkillSet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "SEEKER_SKILL_ID")
	private SkillSet skillSet;

	@ManyToOne
	@JoinColumn(name = "SEEKER_PROFILE_ID")
	private SeekerProfile seekerProfile;

	public SeekerSkillSet() {
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
	 * @return the seekerProfile
	 */
	public SeekerProfile getSeekerProfile() {
		return seekerProfile;
	}

	/**
	 * @return the skillSet
	 */
	public SkillSet getSkillSet() {
		return skillSet;
	}

	/**
	 * @param skillSet
	 *            the skillSet to set
	 */
	public void setSkillSet(SkillSet skillSet) {
		this.skillSet = skillSet;
	}

	/**
	 * @param seekerProfile
	 *            the seekerProfile to set
	 */
	public void setSeekerProfile(SeekerProfile seekerProfile) {
		this.seekerProfile = seekerProfile;
	}

}
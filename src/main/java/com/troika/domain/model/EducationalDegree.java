package com.troika.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the user_type database table.
 * 
 */
@Entity
@Table(name = "EducationalDegree")
@NamedQuery(name = "EducationalDegree.findAll", query = "SELECT u FROM EducationalDegree u")
public class EducationalDegree implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer degreeId;

	private String degreeName;

	private Boolean isActive = Boolean.TRUE;

	public EducationalDegree() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the degreeId
	 */
	public Integer getDegreeId() {
		return degreeId;
	}

	/**
	 * @param degreeId
	 *            the degreeId to set
	 */
	public void setDegreeId(Integer degreeId) {
		this.degreeId = degreeId;
	}

	/**
	 * @return the degreeName
	 */
	public String getDegreeName() {
		return degreeName;
	}

	/**
	 * @param degreeName
	 *            the degreeName to set
	 */
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EducationalDegree [degreeId=" + degreeId + ", degreeName=" + degreeName + ", isActive=" + isActive
				+ "]";
	}

}
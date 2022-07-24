package com.troika.domain.view;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.troika.domain.model.City;
import com.troika.domain.model.Country;
import com.troika.domain.model.EducationalDegree;
import com.troika.domain.model.Role;
import com.troika.domain.model.State;
import com.troika.domain.model.UserType;

@XmlRootElement
public class MasterData implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<UserType> userTypeList;

	private List<State> stateList;

	private List<City> cityList;

	private List<Country> countryList;

	private List<EducationalDegree> degreeList;

	private List<SkillSetData> skillSetList;

	private List<JobIndustryTypeData> jobIndustryTypeList;

	private List<Role> roleList;

	/**
	 * @return the userTypeList
	 */
	public List<UserType> getUserTypeList() {
		return userTypeList;
	}

	/**
	 * @param userTypeList
	 *            the userTypeList to set
	 */
	public void setUserTypeList(List<UserType> userTypeList) {
		this.userTypeList = userTypeList;
	}

	/**
	 * @return the stateList
	 */
	public List<State> getStateList() {
		return stateList;
	}

	/**
	 * @param stateList
	 *            the stateList to set
	 */
	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}

	/**
	 * @return the degreeList
	 */
	public List<EducationalDegree> getDegreeList() {
		return degreeList;
	}

	/**
	 * @param degreeList
	 *            the degreeList to set
	 */
	public void setDegreeList(List<EducationalDegree> degreeList) {
		this.degreeList = degreeList;
	}

	/**
	 * @return the cityList
	 */
	public List<City> getCityList() {
		return cityList;
	}

	/**
	 * @param cityList
	 *            the cityList to set
	 */
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	/**
	 * @return the jobTypeList
	 */
	public List<JobIndustryTypeData> getJobIndustryTypeList() {
		return jobIndustryTypeList;
	}

	/**
	 * @param jobTypeList
	 *            the jobTypeList to set
	 */
	public void setJobIndustryTypeList(List<JobIndustryTypeData> jobTypeList) {
		this.jobIndustryTypeList = jobTypeList;
	}

	/**
	 * @return the countryList
	 */
	public List<Country> getCountryList() {
		return countryList;
	}

	/**
	 * @param countryList
	 *            the countryList to set
	 */
	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	/**
	 * @return the skillSetList
	 */
	public List<SkillSetData> getSkillSetList() {
		return skillSetList;
	}

	/**
	 * @param skillSetList
	 *            the skillSetList to set
	 */
	public void setSkillSetList(List<SkillSetData> skillSetList) {
		this.skillSetList = skillSetList;
	}

	/**
	 * @return the roleList
	 */
	public List<Role> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList
	 *            the roleList to set
	 */
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MasterData [userTypeList=" + userTypeList + ", stateList=" + stateList + ", cityList=" + cityList
				+ ", countryList=" + countryList + ", degreeList=" + degreeList + ", skillSetList=" + skillSetList
				+ ", jobTypeList=" + jobIndustryTypeList + "]";
	}

}

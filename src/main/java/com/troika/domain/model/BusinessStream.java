package com.troika.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the business_stream database table.
 * 
 */
@Entity
@Table(name = "BUSINESS_STREAM")
@NamedQuery(name = "BusinessStream.findAll", query = "SELECT b FROM BusinessStream b")
public class BusinessStream implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String businessStreamName;

	@OneToMany(mappedBy = "businessStream", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Company> companies;

	public BusinessStream() {
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
	 * @return the businessStreamName
	 */
	public String getBusinessStreamName() {
		return businessStreamName;
	}

	/**
	 * @param businessStreamName
	 *            the businessStreamName to set
	 */
	public void setBusinessStreamName(String businessStreamName) {
		this.businessStreamName = businessStreamName;
	}

	/**
	 * @return the companies
	 */
	public List<Company> getCompanies() {
		return companies;
	}

	/**
	 * @param companies
	 *            the companies to set
	 */
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BusinessStream [id=" + id + ", businessStreamName=" + businessStreamName 
				+ "]";
	}

}
package com.troika.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the user_type database table.
 * 
 */
@Entity
@Table(name = "user_type", uniqueConstraints = @UniqueConstraint(columnNames = { "userTypeName" }))
@NamedQuery(name = "UserType.findAll", query = "SELECT u FROM UserType u")
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userTypeId;

	private String userTypeName;

	// bi-directional many-to-one association to UserAccount
	@OneToMany(mappedBy = "userType")
	private List<UserAccount> userAccounts;

	public UserType() {
	}

	/**
	 * @return the userTypeId
	 */
	public Integer getUserTypeId() {
		return userTypeId;
	}

	/**
	 * @param userTypeId
	 *            the userTypeId to set
	 */
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	/**
	 * @return the userTypeName
	 */
	public String getUserTypeName() {
		return userTypeName;
	}

	/**
	 * @param userTypeName
	 *            the userTypeName to set
	 */
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	/**
	 * @return the userAccounts
	 */
	public List<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	/**
	 * @param userAccounts
	 *            the userAccounts to set
	 */
	public void setUserAccounts(List<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

}
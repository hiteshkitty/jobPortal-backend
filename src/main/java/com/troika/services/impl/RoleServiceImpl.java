package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.troika.dao.RoleDao;
import com.troika.domain.model.Role;
import com.troika.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger LOGGER = Logger.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role retrieveRoleById(Integer roleId) {
		Role role = roleDao.retireveRoleById(roleId);

		return role;
	}

	@Override
	public Role postRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role deleteRoleById(Integer roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Cacheable("roles")
	public List<Role> findAll() {

		LOGGER.trace("fetching all roles");

		List<Role> roles = roleDao.findAll();

		LOGGER.trace("fetched roles: " + roles);

		return roles;

	}

}

package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.troika.dao.RoleDao;
import com.troika.domain.model.Role;
import com.troika.repo.RoleRepo;

@Component
public class RoleDaoImpl implements RoleDao {

	private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);

	@Autowired
	private RoleRepo roleRepo;

	@Override
	@Cacheable("role")
	public Role retireveRoleById(Integer roleId) {

		Role comp = roleRepo.findOne(roleId);

		LOGGER.trace("Role: " + comp);

		return comp;

	}

	@Override
	public Role createRole(Role role) {

		LOGGER.trace("creating role details: " + role);

		Role roleCreated = roleRepo.save(role);

		LOGGER.trace("created RoleDetails: " + roleCreated);

		return roleCreated;

	}

	@Override
	public void deleteRole(Role roleToDelete) {

		LOGGER.trace("deleting role details: " + roleToDelete);

		roleRepo.delete(roleToDelete);

		LOGGER.trace("created RoleDetails: " + roleToDelete);

	}

	@Override
	public Role updateRole(Role roleToUpdate) {

		LOGGER.trace("updating role details: " + roleToUpdate);

		Role roleUpdated = roleRepo.save(roleToUpdate);

		LOGGER.trace("roleUpdated RoleDetails: " + roleUpdated);

		return roleUpdated;
	}

	@Override
	public List<Role> findAll() {

		List<Role> role = null;

		LOGGER.trace("fetching all Role");

		role = (List) roleRepo.findAll();

		LOGGER.trace("fetch all Role: ");

		return role;
	}

}

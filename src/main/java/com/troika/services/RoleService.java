package com.troika.services;

import java.util.List;

import com.troika.domain.model.Role;

public interface RoleService {

	Role retrieveRoleById(final Integer roleId);

	Role postRole(Role role);

	Role deleteRoleById(Integer compId);

	Role updateRole(Role role);

	List<Role> findAll();

}

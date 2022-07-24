package com.troika.dao;

import java.util.List;

import com.troika.domain.model.Role;

public interface RoleDao {

	Role retireveRoleById(Integer roleId);

	Role createRole(Role role);

	void deleteRole(Role roleToDelete);

	Role updateRole(Role roleToUpdate);

	List<Role> findAll();
}

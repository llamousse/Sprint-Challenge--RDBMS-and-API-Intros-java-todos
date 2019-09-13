package com.vyue.todos.service;

import com.vyue.todos.model.Role;

import java.util.List;

public interface RoleService
{
	List<Role> findAll();

	Role findRoleById(long id);

	void delete(long id);

	Role save(Role role);
}
package com.vyue.todos.service;

import com.vyue.todos.model.Todo;

import java.util.List;

public interface TodoService
{
	List<Todo> findAll();

	Todo findTodoById(long id);

	List<Todo> findByUserName(String name);

	void delete(long id);

	Todo save(Todo todo);

	Todo update(Todo todo, long id);
}

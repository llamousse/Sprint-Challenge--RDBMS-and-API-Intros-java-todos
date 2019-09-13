package com.vyue.todos.repository;

import com.vyue.todos.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<Todo, Long>
{
}

package com.vyue.todos.controller;

import com.vyue.todos.model.Todo;
import com.vyue.todos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/todos")
public class TodoController
{
	@Autowired
	private TodoService todoService;

	// PUT - localhost:2019/todos/todoid/{todoid}
	@PutMapping(value = "/todoid/{id}")
	public ResponseEntity<?> updateTodo(@RequestBody Todo updateTodo, @PathVariable long id)
	{
		todoService.update(updateTodo, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

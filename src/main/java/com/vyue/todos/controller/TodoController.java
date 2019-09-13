package com.vyue.todos.controller;

import com.vyue.todos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todos")
public class TodoController
{
	@Autowired
	private TodoService todoService;

	// GET: localhost:2019/users/mine
//	@GetMapping(value = "/mine", produces = {"application/json"})
//	public ResponseEntity<?> listAllTodos()
//	{
//		return new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);
//	}
}
package com.vyue.todos.controller;

import com.vyue.todos.model.User;
import com.vyue.todos.service.TodoService;
import com.vyue.todos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
	@Autowired
	private UserService userService;

	// GET localhost:2019/users/mine
	@GetMapping(value = "/mine", produces = {"application/json"})
	public ResponseEntity<?> getTodoData()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User data = userService.findUserByName(authentication.getName());
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// POST - localhost:2019/users
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@PostMapping(value = "", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException
	{
		newuser =  userService.save(newuser);

		// set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newUserURI = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{userid}")
				.buildAndExpand(newuser.getUserid())
				.toUri();
		responseHeaders.setLocation(newUserURI);

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	// DELETE - localhost:2019/users/userid/{userid}
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@DeleteMapping("/userid/{userid}")
	public ResponseEntity<?> deleteUserById(@PathVariable long userid)
	{
		userService.delete(userid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
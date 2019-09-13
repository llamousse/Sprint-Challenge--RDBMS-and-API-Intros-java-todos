package com.vyue.todos.service;

import com.vyue.todos.model.Todo;
import com.vyue.todos.repository.ToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
	private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

	@Autowired
	private ToDoRepository todorepos;

	@Override
	public ArrayList<Todo> findAll()
	{
		ArrayList<Todo> list = new ArrayList<>();
		todorepos.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public Todo findTodoById(long id)
	{
		return todorepos.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
	}

//	@Override
//	public Todo findTodoByName(String name) throws EntityNotFoundException
//	{
//		Todo currentTodo = todorepos.findByToDoname(name);
//
//		if (currentTodo != null)
//		{
//			return currentTodo;
//		} else
//		{
//			throw new EntityNotFoundException(name);
//		}
//	}

	@Transactional
	@Override
	public void delete(long id) throws EntityNotFoundException
	{
		if (todorepos.findById(id).isPresent())
		{
			todorepos.deleteById(id);

			logger.info("Todo Deleted");
		}else
		{
			throw new EntityNotFoundException(Long.toString(id));
		}
	}


	@Transactional
	@Override
	public Todo save(Todo todo)
	{
//		Todo newTodo = new Todo();
//
//		newTodo.setTodoname(todo.getTodoname());
//
//		for (Telephone t:zoo.getTelephones())
//		{
//			newZoo.getTelephones().add(new Telephone(t.getPhonetype(),t.getPhonenumber(), newZoo));
//		}
//
//		logger.info("Updating a Zoo");
		return todorepos.save(todo);
//		return null;
	}


	@Transactional
	@Override
	public Todo update(Todo todo, long id)
	{
		Todo newTodo = todorepos.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

		if (todo.getDescription() != null)
		{
			newTodo.setDescription(todo.getDescription());
		}

		if (todo.getUser() != null)
		{
			newTodo.setUser(todo.getUser());
		}

		logger.info("Creating a Todo");
		return todorepos.save(newTodo);
//		return null;
	}

}

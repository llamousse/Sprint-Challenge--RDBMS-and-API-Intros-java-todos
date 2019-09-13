package com.vyue.todos.service;

import com.vyue.todos.model.Todo;
import com.vyue.todos.repository.ToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service(value = "todoService")
public class TodoServiceImpl
{
	private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

	@Autowired
	private ToDoRepository todorepos;

	@Override
	public Todo findTodoById(long id)
	{
		return todorepos.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
	}

	@Override
	public Todo findTodoByName(String name) throws EntityNotFoundException
	{
		Todo currentZoo = todorepos.findByTodoname(name);

		if (currentZoo != null)
		{
			return currentZoo;
		} else
		{
			throw new EntityNotFoundException(name);
		}
	}

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
//		return zoorepos.save(newZoo);
		return null;
	}


	@Transactional
	@Override
	public Todo update(Todo todo, long id)
	{
//		Zoo currentZoo = zoorepos.findById(id)
//				.orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
//
//		if (zoo.getZooname() != null)
//		{
//			currentZoo.setZooname(zoo.getZooname());
//		}
//
//		if (zoo.getTelephones().size() > 0)
//		{
//			// adds new phone numbers to list
//			for (Telephone t:zoo.getTelephones())
//			{
//				currentZoo.getTelephones().add(new Telephone(t.getPhonetype(),t.getPhonenumber(), currentZoo));
//			}
//		}
//
//		logger.info("Creating a Zoo");
//		return zoorepos.save(currentZoo);
		return null;
	}

}

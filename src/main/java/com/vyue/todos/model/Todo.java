package com.vyue.todos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "todos")
public class Todo extends Auditable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long todoid;

	@Column(nullable = false)
	private String description;

	private Date datestarted;
	private boolean completed;

	// many todos to one user
	@ManyToOne
	@JoinColumn(name = "userid",
				nullable = false)
	@JsonIgnoreProperties("todos")
	private User user;

	public Todo()
	{
	}

	public Todo(String description, Date datestarted, User user)
	{
		this.description = description;
		this.datestarted = datestarted;
		this.completed = false;
		this.user = user;
	}

	public Todo(Todo clone, User user){
		this.todoid = clone.getTodoid();
		this.description = clone.getDescription();
		this.completed = clone.isCompleted();
		this.datestarted = clone.getDatestarted();
		this.user = user;
	}

	// getters and setters
	public long getTodoid()
	{
		return todoid;
	}

	public void setTodoid(long todoid)
	{
		this.todoid = todoid;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Date getDatestarted()
	{
		return datestarted;
	}

	public void setDatestarted(Date datestarted)
	{
		this.datestarted = datestarted;
	}

	public boolean isCompleted()
	{
		return completed;
	}

	public void setCompleted(boolean completed)
	{
		this.completed = completed;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Override
	public String toString()
	{
		return "Todo{" +
				"todoid=" + todoid +
				", description='" + description + '\'' +
				", datestarted=" + datestarted +
				", completed=" + completed +
				", user=" + user + '}';
	}
}

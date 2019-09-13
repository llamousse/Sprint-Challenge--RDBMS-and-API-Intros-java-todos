package com.vyue.todos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "userroles")
public class UserRoles extends Auditable implements Serializable
		// when you have 2 IDs, you need to implement Serializable
		// allows Jackson to convert them into JSON or
		// Jackson to take the JSON and convert them to Java objects
		// *** if Serializable is added, you need to implement the
		// 'equals and hashCode methods' through Generate - can't use default ones ***
{
	@Id
	@ManyToOne
	@JsonIgnoreProperties({"userRoles", "hibernateLazyInitializer"})
	@JoinColumn(name = "userid")
	private User user;

	@Id
	@ManyToOne
	@JoinColumn(name = "roleid")
	@JsonIgnoreProperties({"userRoles", "hibernateLazyInitializer"})
	private Role role;

	public UserRoles()
	{
	}

	public UserRoles(User user, Role role)
	{
		this.user = user;
		this.role = role;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof UserRoles))
		{
			return false;
		}
		UserRoles userRoles = (UserRoles) o;
		return getUser().equals(userRoles.getUser()) && getRole().equals(userRoles.getRole());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getUser(), getRole());
	}
}
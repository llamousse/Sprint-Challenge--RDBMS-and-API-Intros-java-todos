package com.vyue.todos.service;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditing implements AuditorAware<String>
{
	@Override
	public Optional<String> getCurrentAuditor()
	{
		// if someone is signed on, to the system, use their name
		String uname;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// grab new copy of authentication
		if (authentication != null)
		{
			uname = authentication.getName();
		}
		else
		{
			uname = "SYSTEM";
		}
		return Optional.of(uname);
	}
}

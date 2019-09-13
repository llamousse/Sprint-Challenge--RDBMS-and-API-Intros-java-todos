package com.vyue.todos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer // which roles has access to which endpoints
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{

	private static final String RESOURCE_ID = "resource_id";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources)
	{
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		// http.anonymous().disable();
		http.authorizeRequests()
				.antMatchers("/",                       // h2
						"/h2-console/**",
						"/swagger-resources/**",
						"/swagger-resources/configuration/ui",
						"/swagger-resources/configuration/security",
						"/swagger-resource/**",
						"/swagger-ui.html",
						"/v2/api-docs",
						"/webjars/**").permitAll()
				.antMatchers("/users/mine").authenticated()
//				.antMatchers("/users/**", "/todos/**").authenticated()
				.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
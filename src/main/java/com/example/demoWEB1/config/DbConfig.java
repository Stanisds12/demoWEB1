package com.example.demoWEB1.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DbConfig {
	@Autowired

	
	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5433/test");
		dataSource.setUsername("postgres");
		dataSource.setPassword("qwerty12");
		return dataSource;
		
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate() throws ClassNotFoundException{
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	

}
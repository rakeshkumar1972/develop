package com.cardinal.schedular.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Rakesh.Kumar
 *
 */
@Configuration
public class DataConfig {

	@Autowired
	DatabaseProperties dbproperties;

	/**
	 * @return datasource
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbproperties.getDriver());
		dataSource.setUrl(dbproperties.getUrl());
		dataSource.setUsername(dbproperties.getUsername());
		dataSource.setPassword(dbproperties.getPassword());

		return dataSource;
	}

}

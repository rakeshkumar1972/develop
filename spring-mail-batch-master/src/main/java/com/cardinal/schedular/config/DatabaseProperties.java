package com.cardinal.schedular.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
/**
 * @author Rakesh.Kumar
 */
@ConfigurationProperties(prefix = "spring.datasource" ,locations = "classpath:application.properties")
public class DatabaseProperties {
	
	
	private String password;
	private String username;
	private String driver;
	private String url;
	private String showsql;
	public String getShowsql() {
		return showsql;
	}
	public void setShowsql(String showsql) {
		this.showsql = showsql;
	}
	public String getDialect() {
		return dialect;
	}
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
	private String dialect;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}

}

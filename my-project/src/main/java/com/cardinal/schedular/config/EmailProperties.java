package com.cardinal.schedular.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Rakesh.Kumar
 *
 */
@ConfigurationProperties(prefix = "spring.email" ,locations = "classpath:application.properties")
public class EmailProperties {

	private String sender;
	private String drive;
	private String data;
	private String notification;
	
	
	
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	private String attachement;
	public String getAttachement() {
		return attachement;
	}
	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getDrive() {
		return drive;
	}
	public void setDrive(String drive) {
		this.drive = drive;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}

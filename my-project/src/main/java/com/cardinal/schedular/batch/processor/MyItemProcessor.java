package com.cardinal.schedular.batch.processor;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.cardinal.schedular.model.Employee;
import com.cardinal.schedular.service.EmailService;

import freemarker.template.Configuration;

/**
 * @author Rakesh.Kumar
 *
 */
public class MyItemProcessor implements ItemProcessor<Employee, MimeMessage>{
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeDataProcessor.class);
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine engine;
	private String sender;
	private String attachment;
	private MimeMessage MimeMessage;
	@Autowired
	private Configuration config;
	
	
	@Autowired
	EmailService emailservice;
	
	public MyItemProcessor(String sender, String attachment) {
		this.sender = sender;
		this.attachment = attachment;
	}
	
	/**
	 *@param employee
	 */
	@Override
	public MimeMessage process(Employee employee) throws Exception {	
		
		emailservice.htmlEmail(employee);

		return MimeMessage;
	
	}
	
}

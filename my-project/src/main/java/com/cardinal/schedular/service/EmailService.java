package com.cardinal.schedular.service;

import com.cardinal.schedular.model.Employee;
/**
 * @author Rakesh Kumar
 *
 */
public interface  EmailService {
	
	public String sendmail(String mailtoSend,String message,String subject);
	
	public abstract void htmlEmail(Employee employee );
	
	public void sendAlertMail(String alert);	
	
}

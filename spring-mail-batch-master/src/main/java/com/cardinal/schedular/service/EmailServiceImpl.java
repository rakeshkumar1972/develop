package com.cardinal.schedular.service;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.cardinal.schedular.model.Employee;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author Rakesh Kumar
 *
 */
@Service
public class EmailServiceImpl implements EmailService{
	
	private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Configuration config;
	
	/**@param mailtoSend
	 * @param message
	 * @param subject
	 *
	 */
	@Override
	public String sendmail(String mailtoSend, String message, String subject) {

		// TODO mailSender-generated method stub
		MimeMessage mimessage = mailSender.createMimeMessage();
		MimeMessageHelper mimessagehelper;
		try {
			mimessagehelper = new MimeMessageHelper(mimessage, true);
			mimessagehelper.setSubject(subject);
			mimessagehelper.setTo(mailtoSend);
			mimessagehelper.setText(message);
			mailSender.send(mimessage);
		} catch (MessagingException e) {
		}
		return message;
	}
	
	/**
	 *@param employee
	 */
	@Override
	public void htmlEmail(Employee employee) {
		    MimeMessage htmlmsg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(htmlmsg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,StandardCharsets.UTF_8.name());
			Template htmtemplt = config.getTemplate(employee.getTemplate());
			Map<String, Object> model = new HashMap<>();
			model.put("name", employee.getName());
			model.put("location", "Lucknow");
			model.put("description", employee.getDescription());
			model.put("subject", employee.getSubject());
	    	String html = FreeMarkerTemplateUtils.processTemplateIntoString(htmtemplt, model);
			helper.setTo(employee.getEmail());
			helper.setText(html, true);
			helper.setSubject(employee.getSubject());
			html = FreeMarkerTemplateUtils.processTemplateIntoString(htmtemplt, model);
			helper.setText(html, true);
			mailSender.send(htmlmsg);

		    } catch (Exception e) { 
		    	log.error(e.getMessage(), e);     
		}
	}
	/**
	 * 
	 * @param alert
	 * This method is used to send the alert already prepared message.
	 * 
	 * **/
    public void sendAlertMail(String alert) {
        
        SimpleMailMessage alertMailMessage = null;
		SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
        mailMessage.setText(alert);
        mailSender.send(mailMessage);    
    }
 
}




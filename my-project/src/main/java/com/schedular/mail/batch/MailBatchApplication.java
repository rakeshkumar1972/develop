package com.schedular.mail.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MailBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailBatchConfiguration.class, args);
	}
}

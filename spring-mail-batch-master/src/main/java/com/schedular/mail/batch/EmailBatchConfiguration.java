package com.schedular.mail.batch;

import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.cardinal.schedular.batch.processor.EmployeeDataProcessor;
import com.cardinal.schedular.config.DataConfig;
import com.cardinal.schedular.config.DatabaseProperties;
import com.cardinal.schedular.config.EmailProperties;
import com.cardinal.schedular.model.Employee;
import com.cardinal.schedular.rowmapper.EmployeeRowMapper;
import com.cardinal.schedular.service.EmailService;
import com.cardinal.schedular.service.EmailServiceImpl;

/**
 * @author Rakesh.Kumar
 *
 */
@Configuration
@ComponentScan
@EnableBatchProcessing
@Import(DataConfig.class)
@EnableConfigurationProperties({DatabaseProperties.class,EmailProperties.class})
public class EmailBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	DatabaseProperties databaseProperties;
	
	@Autowired
	EmailProperties emailProperties;

	
	/**Method is used to get the number of  person who has the birthday or 
	 * joining date today.
	 * @return
	 */
	@Bean
	public JdbcCursorItemReader<Employee> reader(){
		JdbcCursorItemReader<Employee> cursorItemReader = new JdbcCursorItemReader<>();
		cursorItemReader.setDataSource(context.getBean(DataSource.class));
		cursorItemReader.setSql("select a.employee_id,a.name,a.email,a.birth_date,c.template,c.description,c.subject from  \n" + 
				"  employee a  inner join emp_event b   on a.id = b.employee_id  inner join eventcelebration c  on b.event_id = c.event_cl_id  where CAST(birth_date as date) = CAST(curdate() as date) or CAST(date_of_joining as date) = CAST(curdate() as date)\n" + 
				"  ");		
		cursorItemReader.setRowMapper(new EmployeeRowMapper());
		return cursorItemReader;
	}	
	

	@Bean
	public EmailService emailService() {
	        return new EmailServiceImpl();
	}
		
	@Bean
	public EmployeeDataProcessor processor() {
		return new EmployeeDataProcessor(emailProperties.getSender(), emailProperties.getAttachement());
	}
	
	@Bean
	public MailBatchItemWriter writer() {
		MailBatchItemWriter writer = new MailBatchItemWriter();
		return writer;
	}

	
	  @Bean public JobExecutionListener listener() { return new
	  JobCompletionNotificationListener(emailProperties.getNotification()); }
	 

    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
               // .listener(listener())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Employee, MimeMessage> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
  
}

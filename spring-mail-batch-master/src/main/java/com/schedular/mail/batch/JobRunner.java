package com.schedular.mail.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Rakesh.Kumar
 *  This is basically a schedular which schedule the batch activity to look for the any 
 *  person who has the birthday or joining day today.And schedule as per the @Schedulae 
 *  
 */
@Service
@EnableScheduling
public class JobRunner {

	private static final Logger log = LoggerFactory.getLogger(JobRunner.class);

	@Autowired
	public JobLauncher launcher;

	@Autowired
	Job job;

	@Scheduled(cron = "* 01 0 * * *")
	public void taskscheduling() {

		JobParameters JobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();

		try {
			JobExecution execution = launcher.run(job, JobParameters);
			log.info("Status of the job after execution" + execution.getStatus());

		} catch (JobExecutionAlreadyRunningException e) {

			log.error(e.getMessage(), e);

		} catch (JobRestartException e) {

			log.error(e.getMessage(), e);
		} catch (JobInstanceAlreadyCompleteException e) {

			log.error(e.getMessage(), e);
		} catch (JobParametersInvalidException e) {

			log.error(e.getMessage(), e);
		}

	}
}

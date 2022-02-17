package com.project.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@EnableAsync
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class Executor {

	@Value("${schedule.delay}")
	private final String delay="0 * * ? * *";
	private static boolean shootDown = false;

	@Autowired
	BaseTaskDefinitions taskDefinitions;
	
	
     /* dakikada bir 5 dakikada bir gibi db'de tanımlı joblar (30 dakikanın altındakiler) burada tanımlanmalı */
	//@Scheduled(cron = delay)
	//@Scheduled(fixedDelay = 3000)
	public void executeEvery1Minute() {

		try {
			if (!shootDown) {
				System.out.println("---------------------------------------------");
				System.out.println("Executor.execute() start ---- " + new Date());
				List<AJob> executableTasks = taskDefinitions.getAllTask();
				for (AJob job : executableTasks) {
					job.run();
				}
			}

		} catch (IllegalArgumentException e) {
			
		} catch (IllegalAccessException e) {
			
		}

	}
	
	/* yarım saat ve üzeri arayla çalışacak joblar burada tanımlanmalı */
	public void executeEvery30Minute() {

		try {
			if (!shootDown) {
				System.out.println("---------------------------------------------");
				System.out.println("Executor.execute() start ---- " + new Date());
				List<AJob> executableTasks = taskDefinitions.getAllTask();
				for (AJob job : executableTasks) {
					job.run();
				}
			}

		} catch (IllegalArgumentException e) {
			shootDown = true;
		} catch (IllegalAccessException e) {
			shootDown = true;
		}

	}
	
	/* 1 saat ve üzeri arayla çalışacak joblar burada tanımlanmalı */
	public void executeEvery60Minute() {

		try {
			if (!shootDown) {
				System.out.println("---------------------------------------------");
				System.out.println("Executor.execute() start ---- " + new Date());
				List<AJob> executableTasks = taskDefinitions.getAllTask();
				for (AJob job : executableTasks) {
					job.run();
				}
			}

		} catch (IllegalArgumentException e) {
			shootDown = true;
		} catch (IllegalAccessException e) {
			shootDown = true;
		}

	}

}

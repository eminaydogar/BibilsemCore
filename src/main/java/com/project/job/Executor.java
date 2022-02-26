package com.project.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@EnableScheduling
@EnableAsync
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
@Slf4j
public class Executor {

	private final String every1MinDelay = "0 * * ? * *";
	private final String every30MinDelay = "0 */30 * ? * *";
	private final String every60MinDelay = "0 0 * ? * *";

	@Setter
	@Getter
	private  boolean shootDown = false;

	@Autowired
	BaseTaskDefinitions taskDefinitions;

	/*
	 * dakikada bir 5 dakikada bir gibi db'de tanımlı joblar (30 dakikanın
	 * altındakiler) burada tanımlanmalı
	 */
	// @Scheduled(cron = every1MinDelay)

	//@Scheduled(fixedDelay = 6000L)
	public void test() {
		try {
			if (!shootDown) {
				log.info("executeEvery1Minute() start ---> " + new Date());
				List<AJob> executableTasks = taskDefinitions.getAllTask().getTask1Min();
				for (AJob job : executableTasks) {
					job.run();
				}
			}

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
		} catch (IllegalAccessException e) {
			log.error(e.getMessage());
		}
	

	}

	// @Scheduled(cron = every1MinDelay)
	public void executeEvery1Minute() {

		try {
			if (!shootDown) {
				log.info("executeEvery1Minute() start ---> " + new Date());
				List<AJob> executableTasks = taskDefinitions.getAllTask().getTask1Min();
				for (AJob job : executableTasks) {
					job.run();
				}
			}

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
		} catch (IllegalAccessException e) {
			log.error(e.getMessage());
		}

	}

	/* yarım saat ve üzeri ve <1 saat arayla çalışacak joblar burada tanımlanmalı */
	// @Scheduled(cron = every30MinDelay)
	public void executeEvery30Minute() {

		try {
			if (!shootDown) {
				log.info("executeEvery30Minute() start ---> " + new Date());
				List<AJob> executableTasks = taskDefinitions.getAllTask().getTask30Min();
				for (AJob job : executableTasks) {
					job.run();
				}
			}

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
		} catch (IllegalAccessException e) {
			log.error(e.getMessage());
		}

	}

	/* 1 saat ve üzeri arayla çalışacak joblar burada tanımlanmalı */
	// @Scheduled(cron = every60MinDelay)
	public void executeEvery60Minute() {

		try {
			if (!shootDown) {
				log.info("executeEvery60Minute() start ---> " + new Date());
				List<AJob> executableTasks = taskDefinitions.getAllTask().getTask60Min();
				for (AJob job : executableTasks) {
					job.run();
				}
			}

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
		} catch (IllegalAccessException e) {
			log.error(e.getMessage());
		}

	}

}

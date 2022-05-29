package com.project.mail;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Override
	public int sendMail(String to, String subject, String text) {
	
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			emailSender.send(message);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			return -1;
		}

		return 1;

	}

	@Override
	public int sendMail(String to, String subject, String text, String pathToAttachment) {
		MimeMessage message = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			/*
			 * message.setText("my text <img src='cid:myLogo'>", true);
			 * message.addInline("myLogo", new ClassPathResource("img/mylogo.gif"));
			 */
			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			helper.addAttachment(file.getFilename(), file);
			emailSender.send(message);
			Thread.sleep(500);
		} catch (MessagingException e) {
			log.error(e.getMessage());
			return -1;
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			return -1;
		}

		return 1;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
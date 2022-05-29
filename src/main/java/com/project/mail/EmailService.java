package com.project.mail;

public interface EmailService extends Runnable {

	public int sendMail(String to, String subject, String text);

	public int sendMail(String to, String subject, String text, String pathToAttachment);
}

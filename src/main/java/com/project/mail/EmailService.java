package com.project.mail;

public interface EmailService extends Runnable {

	public void sendMail(String to, String subject, String text);

	public void sendMail(String to, String subject, String text, String pathToAttachment);
}

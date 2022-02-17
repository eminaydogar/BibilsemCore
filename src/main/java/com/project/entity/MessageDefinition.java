package com.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "message_definition")
public class MessageDefinition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/*
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "user_id", referencedColumnName = "id") private
	 * UserDefinition user;
	 */
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "channel_type")
	private String channelType;
	
	@Column(name = "message_type")
	private String messageType;
	
	@Column(name = "message_content")
	private String messageContent;
	
	@Column(name = "mail_to")
	private String mailTo;
	
	@Column(name = "sms_to")
	private Long smsTo;
	
	@Column(name = "cdate")
	private Date cdate;
	
	@Column(name = "edate")
	private Date edate;

}

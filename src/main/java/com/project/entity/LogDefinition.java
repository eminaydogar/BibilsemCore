package com.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "log_definition")
public class LogDefinition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@Column(name = "class_name")
    private String className;
	
	@Column(name = "method_name")
    private String methodName;
	
	@Column(name = "message")
    private String message;
	
	@Column(name = "error_type")
    private String errorType;
	
	@Column(name = "error_message")
    private String errorMessage;
	
	@Column(name = "cdate")
    private Date cdate;
}

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
@Table(name = "question_definition")
public class QuestionDefinition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "question_text", nullable = false, length = 1024)
	private String questionText;

	@Column(name = "yes_price", nullable = false)
	private Double yesPrice;

	@Column(name = "no_price", nullable = false)
	private Double noPrice;

	@Column(name = "sdate", nullable = false)
	private Date sdate;

	@Column(name = "edate")
	private Date edate;

	@Column(name = "status", length = 1)
	private String status;
	
	@Column(name = "answer", length = 1)
	private String answer;

}

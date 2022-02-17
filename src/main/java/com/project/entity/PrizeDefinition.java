package com.project.entity;

import java.sql.Clob;
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
@Table(name = "prize_definition")
public class PrizeDefinition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "prize_name")
	private String prizeName;

	@Column(name = "prize_image")
	private Clob prizeImage;

	@Column(name = "prize_price")
	private Long price;
	
	@Column(name = "cdate")
	private Date cdate;
	
	@Column(name = "status")
	private String status;

}

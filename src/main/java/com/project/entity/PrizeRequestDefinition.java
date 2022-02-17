package com.project.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "prize_request_definition")
public class PrizeRequestDefinition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "prize_id", referencedColumnName = "id")
	PrizeDefinition prize;
	
	@Column(name = "cdate")
	private Date cdate;

	@Column(name = "edate")
	private Date edate;
	
	@Column(name = "request_status")
	private String requestStatus;
	
	@Column(name = "status_description")
	private String statusDescription;

}

package com.project.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "coupon_definition")
public class CouponDefinition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "coupon_name",nullable = false)
	private String name;

	@Column(name = "sdate")
	private Date sdate;

	@Column(name = "edate")
	private Date edate;
	
	@Column(name = "coupon_status")
	private String status;
	
	@Column(name = "coupon_price")
	private Double price;

	@Column(name = "coupon_amount")
	private Double amount;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "coupon_question_answer_list", joinColumns = { @JoinColumn(name = "coupon_id") }, inverseJoinColumns = {
			@JoinColumn(name = "question_answer_id") })
	private Set<QuestionAnswerDefinition> details;



}

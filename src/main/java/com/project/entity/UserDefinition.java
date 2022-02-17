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
@Table(name = "user_definition")
public class UserDefinition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username",nullable = false,length = 128,updatable = false)
	private String username;

	@Column(name = "password",nullable = false,length = 256)
	private String password;

	@Column(name = "email",nullable = false,length = 128)
	private String email;
	
	@Column(name = "phone_number",nullable = false,length = 128)
	private Long phoneNumber;
	
	@Column(name = "address",nullable = false,length = 1024)
	private String address;
	
	@Column(name="bbpoint",nullable = false,length = 100000000)
	private Long bbPoint;
	
	@Column(name="is_black_list")
	private String isBlackList;
	
	@Column(name="cdate")
	private Date cdate;
	
	@Column(name="last_login_date")
	private Date lastLoginDate;
	
	@Column(name="vertify")
	private String vertify;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role_list", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<RoleDefinition> roles;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_coupon_list", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "coupon_id") })
	private Set<CouponDefinition> coupons;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_prize_request_list", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "prize_request_id") })
	private Set<PrizeRequestDefinition> prizes;


}

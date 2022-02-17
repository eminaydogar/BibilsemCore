package com.project.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.project.entity.CouponDefinition;
import com.project.entity.PrizeRequestDefinition;
import com.project.entity.RoleDefinition;
import com.project.entity.UserDefinition;

import lombok.Data;

@Data
public class UserDto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String password;
	private String email;
	private Long phoneNumber;
	private Long bbPoint;
	private String isBlackList;
	private Set<RoleDto> roles;
	private Set<CouponDto> coupons;
	private Set<PrizeRequestDto> prizes;

	public UserDto() {
	}

	public UserDto(UserDefinition user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.bbPoint = user.getBbPoint();
		this.isBlackList = user.getIsBlackList();
		dtoConverter(user, true, true, true);
	}

	private UserDto(UserDefinition user, boolean fetchRoles, boolean fetchCoupons, boolean fetchprizes) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.bbPoint = user.getBbPoint();
		this.isBlackList = user.getIsBlackList();
		this.phoneNumber = user.getPhoneNumber();
		dtoConverter(user, fetchRoles, fetchCoupons, fetchprizes);
	}

	public UserDto single(UserDefinition user) {
		return new UserDto(user, false, false, false);
	}

	public UserDto withOnlyRoles(UserDefinition user) {
		return new UserDto(user, true, false, false);
	}

	public UserDto withOnlyCoupons(UserDefinition user) {
		return new UserDto(user, false, true, false);
	}

	public UserDto withOnlyPrizes(UserDefinition user) {
		return new UserDto(user, false, false, true);
	}



	private void dtoConverter(UserDefinition user, boolean fetchRoles, boolean fetchCoupons, boolean fetchprizes) {
		if (fetchRoles) {
			roles = roleDtoConverter(user.getRoles());
		}
		if (fetchCoupons)
			coupons = couponDtoConverter(user.getCoupons());
		if (fetchprizes)
			prizes = prizeRequestDtoConverter(user.getPrizes());
	}

	///////////////////////////// Set<DTO> Converters
	///////////////////////////// ////////////////////////////////////

	private Set<RoleDto> roleDtoConverter(Set<RoleDefinition> roles) {
		Set<RoleDto> roleDtoSet = new HashSet<RoleDto>();
		if (roles != null) {
			for (RoleDefinition role : roles) {
				RoleDto model = new RoleDto(role);
				roleDtoSet.add(model);
			}
		}
		return roleDtoSet;
	}

	private Set<CouponDto> couponDtoConverter(Set<CouponDefinition> coupons) {
		Set<CouponDto> couponDtoSet = new HashSet<CouponDto>();
		if (coupons != null) {
			for (CouponDefinition coupon : coupons) {
				CouponDto model = new CouponDto(coupon);
				couponDtoSet.add(model);
			}
		}
		return couponDtoSet;
	}

	private Set<PrizeRequestDto> prizeRequestDtoConverter(Set<PrizeRequestDefinition> prizeReqs) {
		Set<PrizeRequestDto> requestDtoSet = new HashSet<PrizeRequestDto>();
		if (prizeReqs != null) {
			for (PrizeRequestDefinition req : prizeReqs) {
				PrizeRequestDto model = new PrizeRequestDto(req);
				requestDtoSet.add(model);
			}
		}
		return requestDtoSet;
	}

	//////////////////////////// Set<Entity> Converters
	//////////////////////////// ////////////////////////////////

	/*
	 * private Set<CouponDefinition> couponEntityConverter(Set<CouponDto> coupons) {
	 * Set<CouponDefinition> couponDefinitionSet = new HashSet<CouponDefinition>();
	 * if (coupons != null) { for (CouponDto couponDto : coupons) {
	 * couponDefinitionSet.add(couponDto.toEntity()); } } return
	 * couponDefinitionSet; }
	 */
	/*
	 * private Set<RoleDefinition> roleEntityConverter(Set<RoleDto> roles) {
	 * Set<RoleDefinition> roleDefinitionSet = new HashSet<RoleDefinition>(); if
	 * (roles != null) { for (RoleDto roleDto : roles) {
	 * roleDefinitionSet.add(roleDto.toEntity()); } } return roleDefinitionSet; }
	 * 
	 * private Set<PrizeRequestDefinition>
	 * prizeRequestEntityConverter(Set<PrizeRequestDto> reqs) {
	 * Set<PrizeRequestDefinition> prizeRequestDefinitionSet = new
	 * HashSet<PrizeRequestDefinition>(); if (reqs != null) { for (PrizeRequestDto
	 * prizeRequestDto : reqs) {
	 * prizeRequestDefinitionSet.add(prizeRequestDto.toEntity()); } } return
	 * prizeRequestDefinitionSet; }
	 */
}

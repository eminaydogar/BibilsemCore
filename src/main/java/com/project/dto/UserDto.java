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
		fillSimpleDto(user);
		this.roles = roleDtoConverter(user.getRoles());
		this.coupons = couponDtoConverter(user.getCoupons());
		this.prizes = prizeRequestDtoConverter(user.getPrizes());
	}

	private void fillSimpleDto(UserDefinition user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.bbPoint = user.getBbPoint();
		this.isBlackList = user.getIsBlackList();
	}

	public UserDto single(UserDefinition user) {
		fillSimpleDto(user);
		return this;
	}

	public UserDto withRoles(UserDefinition user) {
		fillSimpleDto(user);
		this.roles = roleDtoConverter(user.getRoles());
		return this;
	}

	public UserDto withCoupons(UserDefinition user) {
		fillSimpleDto(user);
		this.coupons = couponDtoConverter(user.getCoupons());
		return this;
	}

	public UserDto withPrizes(UserDefinition user) {
		fillSimpleDto(user);
		this.prizes = prizeRequestDtoConverter(user.getPrizes());
		return this;
	}

	/*
	 * Set<DTO> Converters  FOR FECTH_TYPE = EAGER 
	 * 
	 */
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

}

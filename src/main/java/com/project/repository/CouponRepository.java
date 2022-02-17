package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.CouponDefinition;

@Repository
public interface CouponRepository extends JpaRepository<CouponDefinition, Long> {

}

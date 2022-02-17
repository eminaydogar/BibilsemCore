package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.PrizeDefinition;

@Repository
public interface PrizeRepository extends JpaRepository<PrizeDefinition, Long> {

	List<PrizeDefinition> findAllByStatus(String status);
}

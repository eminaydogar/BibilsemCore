package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.LogDefinition;

public interface LogRepository extends JpaRepository<LogDefinition, Long> {

}

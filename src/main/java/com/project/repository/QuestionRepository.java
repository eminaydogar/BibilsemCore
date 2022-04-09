package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.QuestionDefinition;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionDefinition, Long> {

	List<QuestionDefinition> findAllByStatus(String status);

	public List<QuestionDefinition> findTop3ByOrderBySdateDesc();

}

package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.UserDefinition;

@Repository
public interface UserRepository extends JpaRepository<UserDefinition, Long> {

	UserDefinition findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByUsernameOrEmail(String username, String email);

	UserDefinition findByUsernameOrEmail(String username, String email);

	List<UserDefinition> findTop3ByOrderByBbPointDesc();
}

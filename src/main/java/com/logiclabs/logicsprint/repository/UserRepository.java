package com.logiclabs.logicsprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logiclabs.logicsprint.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String login);
}

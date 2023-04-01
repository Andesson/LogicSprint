package com.logiclabs.logicsprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logiclabs.logicsprint.model.Person;

public interface PersonReposiroty extends JpaRepository<Person, Long> {

}

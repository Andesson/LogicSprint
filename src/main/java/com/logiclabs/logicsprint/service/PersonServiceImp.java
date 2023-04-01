package com.logiclabs.logicsprint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logiclabs.logicsprint.model.Person;
import com.logiclabs.logicsprint.repository.PersonReposiroty;

@Service
public class PersonServiceImp implements PersonService {

	@Autowired
	private PersonReposiroty personReposiroty;
	
	@Override
	public Person savePerson(Person person) {
		return personReposiroty.save(person);
	}

}

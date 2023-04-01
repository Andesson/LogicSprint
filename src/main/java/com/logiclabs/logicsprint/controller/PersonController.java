package com.logiclabs.logicsprint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logiclabs.logicsprint.model.Person;
import com.logiclabs.logicsprint.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@PostMapping("/add")
	public String add(@RequestBody Person person) {
		personService.savePerson(person);
		return "adicionado com sucesso!";
	}

}

package com.app.spring_rest_mongodb;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/person")
public class PersonController {
	@Autowired
	private PersonService service;

	@GetMapping("/getall")
	public Iterable<Person> getAllPerson() {
		return service.findAll();
	}

	@GetMapping("/getbyid/{id}")
	public Optional<Person> getPersonById(@PathVariable("id") String id) {
		return service.getById(id);
	}

	@PostMapping("/save")
	public void saveOrUpdatePerson(@RequestBody Person newPerson) {
		service.saveOrUpdate(newPerson);
	}

	@DeleteMapping("/{id}")
	public void deletePerson(@PathVariable("id") String id) {
		service.deleteById(id);
	}

	@GetMapping("/getbyname/{name}")
	public Person findByName(@PathVariable("name") String name) {
		return service.findByName(name);
	}

	@GetMapping("/getallorderbyname")
	public List<Person> findAllByOrderByNameAsc() {
		return service.findAllByOrderByNameAsc();
	}

}

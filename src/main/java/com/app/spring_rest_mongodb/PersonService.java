package com.app.spring_rest_mongodb;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private PersonRepository repo;

	@Override
	public List<Person> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Person> getById(String id) {
		return repo.findById(id);
	}

	@Override
	public Person saveOrUpdate(Person person) {
		return repo.save(person);
	}

	@Override
	public void deleteById(String id) {
		repo.deleteById(id);

	}

	@Override
	public List<Person> findAllByOrderByNameAsc() {
		return repo.findAllByOrderByNameAsc();
	}

	@Override
	public Person findByName(String name) {
		return repo.findByName(name);
	}

	public void deleteAll() {
		repo.deleteAll();
	}

}

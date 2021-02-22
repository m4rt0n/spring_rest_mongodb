package com.app.spring_rest_mongodb;

import java.util.List;
import java.util.Optional;

public interface IPersonService {
	List<Person> findAll();

	Optional<Person> getById(String id);

	Person saveOrUpdate(Person person);

	void deleteById(String id);

	Person findByName(String name);

	List<Person> findAllByOrderByNameAsc();
}

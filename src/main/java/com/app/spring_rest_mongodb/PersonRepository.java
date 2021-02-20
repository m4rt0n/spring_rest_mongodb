package com.app.spring_rest_mongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

	Person findByName(String name);

	List<Person> findAllOrderedByName();

	List<Person> findAllByHobby(String hobby);
}

package com.app.spring_rest_mongodb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {
	@Autowired
	private PersonService service;
	private List<Person> list = new ArrayList<>();

	@BeforeEach
	public void setup() {
		service.deleteAll();
		Person john = new Person("John");
		Person jane = new Person("Jane");
		Person jim = new Person("Jim");
		list.add(john);
		list.add(jane);
		list.add(jim);
		list.forEach(p -> service.saveOrUpdate(p));
	}

	@Test
	public void findAll() {
		Assertions.assertNotNull(list);
		Assertions.assertEquals(3, list.size());
	}

	@Test
	public void save() {
		Person newPerson = new Person("NewName");
		service.saveOrUpdate(newPerson);
		Assertions.assertNotNull(newPerson.getId());
		Assertions.assertTrue(newPerson.getId().length() > 0);
	}

	@Test
	public void update() {
		Person oldPerson = list.get(0);
		Person updatePerson = new Person("updateName");
		oldPerson.setName(updatePerson.getName());
		service.saveOrUpdate(oldPerson);
		Assertions.assertEquals("updateName", oldPerson.getName());
	}

	@Test
	public void getById() {
		Person p1 = list.get(0);
		Optional<Person> p2 = service.getById(p1.getId());
		Assertions.assertEquals(p1.getId(), p2.get().getId());
	}

	@Test
	public void deleteById() {
		Person p1 = list.get(0);
		service.deleteById(p1.getId());
		List<Person> newList = service.findAll();
		Assertions.assertNotEquals(newList.size(), list.size());
		Optional<Person> p2 = service.getById(p1.getId());
		Assertions.assertTrue(p2.isEmpty());
		Assertions.assertFalse(p2.isPresent());
	}

	@Test
	public void findAllOrderedByName() {
		List<Person> orderedByService = service.findAllByOrderByNameAsc();
		Assertions.assertNotNull(orderedByService);
		Assertions.assertNotSame(list.get(0).getName(), orderedByService.get(0).getName());

		Comparator<Person> compareByName = (Person p1, Person p2) -> p1.getName().compareTo(p2.getName());
		Collections.sort(list, compareByName);
		Assertions.assertEquals(list.get(0).getName(), orderedByService.get(0).getName());
		Assertions.assertEquals(list.get(0).getId(), orderedByService.get(0).getId());
		// ? Assertions.assertSame(list.get(0), orderedByService.get(0));
	}

	@Test
	public void findByName() {
		Person personToFind = new Person("PersonToFind");
		service.saveOrUpdate(personToFind);
		Person personFound = service.findByName("PersonToFind");
		Assertions.assertEquals(personToFind.getId(), personFound.getId());
		Assertions.assertEquals(personToFind.getName(), personFound.getName());
		// ? Assertions.assertSame(personFound, personToFind);
	}

	@Test
	public void deleteAll() {
		service.deleteAll();
		List<Person> listAfterDelete = service.findAll();
		Assertions.assertNotEquals(listAfterDelete.size(), list.size());
		Assertions.assertEquals(0, listAfterDelete.size());
	}
}

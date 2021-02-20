package com.app.spring_rest_mongodb;

public class PersonNotFoundException extends Exception {
	private String id;

	public PersonNotFoundException(String id) {
		super(String.format("person is not found with id: %s", id));
	}
}
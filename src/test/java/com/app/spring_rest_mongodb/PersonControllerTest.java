package com.app.spring_rest_mongodb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class PersonControllerTest {
	@Autowired
	private PersonController controller;
	
	@Test
	@BeforeAll
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}

package com.iag.interview.test;

import com.iag.interview.test.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
public class IagInterviewTestApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Test
	void testGetAgeDetails(){

		Mono<Person> ageDetails = Mono.just(new Person("70","male","US"));

		webTestClient.get().uri("/person/michael")
											.accept(MediaType.APPLICATION_JSON)
											.exchange()
											.expectStatus().isOk()
											.expectBody()
											.jsonPath("$.age").isEqualTo("70")
											.jsonPath("$.gender").isEqualTo("male");

	}


}

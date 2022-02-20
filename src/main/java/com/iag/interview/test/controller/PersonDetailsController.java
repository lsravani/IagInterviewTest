package com.iag.interview.test.controller;

import com.iag.interview.test.model.Agify;
import com.iag.interview.test.model.Builder;
import com.iag.interview.test.model.Gender;
import com.iag.interview.test.model.Nationality;
import com.iag.interview.test.model.Nationality.Country;
import com.iag.interview.test.model.Person;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.util.Arrays;
import javax.net.ssl.SSLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@RestController
@RequestMapping("/person")
public class PersonDetailsController {

 public WebClient webClient;


  @GetMapping("/{name}")
  private Mono<Person> getPersonDetail(@PathVariable String name) throws SSLException {

    Mono<String> age = callAgify(name);
    Mono<String> gender = callGenderize(name);
    Mono<String> nationality = callNationalize(name);

    return age
        .map(Builder::new)
        .zipWith(gender)
        .map(t -> t.getT1().withGender(t.getT2()))
        .zipWith(nationality)
        .map(t -> t.getT1().withNationality(t.getT2()))
        .map(Builder::build);

  }


  public Mono<String> callAgify(String name) throws SSLException {

    WebClient webClient = getSecureWebClient("https://api.agify.io");
    return  webClient.get()
        .uri("/?name="+name)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
          return Mono.error(new Exception("It's a 4xx client error"));
        })
        .bodyToMono(Agify.class).map(Agify::getAge);

  }

  public Mono<String> callGenderize(String name) throws SSLException {

    return getSecureWebClient("https://api.genderize.io/").get()
        .uri("/?name="+name)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
          return Mono.error(new Exception("It's a 4xx client error"));
        })
        .bodyToMono(Gender.class).map(Gender::getGender);

  }

  public Mono<String> callNationalize(String name) throws SSLException {

    return getSecureWebClient("https://api.nationalize.io/").get()
        .uri("/?name=" + name)
         .retrieve()
        .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
          return Mono.error(new Exception("It's a 4xx client error"));
        })
        .bodyToMono(Nationality.class).map(Nationality::getCountryList)
        .flatMapIterable(Arrays::asList).next()
        .map(Country::getCountry_id);

  }


  private WebClient getSecureWebClient(String url) throws SSLException {

    SslContext sslContext = SslContextBuilder
          .forClient()
          .trustManager(InsecureTrustManagerFactory.INSTANCE)
          .build();

    HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
    return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).baseUrl(url).build();

  }

}

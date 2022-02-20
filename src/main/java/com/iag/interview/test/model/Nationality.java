package com.iag.interview.test.model;

import org.springframework.stereotype.Component;

@Component
public class Nationality {

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Country[] getCountryList() {
    return country;
  }

  public void setCountryList(Country[] countryList) {
    this.country = countryList;
  }

  private String name;
  private Country[] country;


  public static class Country{
    private String country_id;

    public String getCountry_id() {
      return country_id;
    }

    public void setCountry_id(String country_id) {
      this.country_id = country_id;
    }

    public double getProbability() {
      return probability;
    }

    public void setProbability(double probability) {
      this.probability = probability;
    }

    private double probability;

  }
}


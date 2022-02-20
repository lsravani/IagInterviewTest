package com.iag.interview.test.model;

import org.springframework.stereotype.Component;

@Component
public class Gender {

  private String name;
  private String gender;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public short getProbability() {
    return probability;
  }

  public void setProbability(short probability) {
    this.probability = probability;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  private short probability;
  private int count;

}

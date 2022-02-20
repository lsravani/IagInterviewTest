package com.iag.interview.test.model;

import org.springframework.stereotype.Component;

@Component
public class Agify {

  private String name;
  private String age;
  private int count;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }


}


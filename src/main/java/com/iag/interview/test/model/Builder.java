package com.iag.interview.test.model;

public class Builder {
  private String age;
  private String gender;
  private Object nationality;

  public Builder(String age) {
    this.age = age;
  }

  public Builder withGender(String gender) {
    this.gender = gender;
    return this;
  }

  public Builder withNationality(Object t3) {
    this.nationality = t3;
    return this;
  }


  public Person build() {
    return new Person(age, gender, nationality);
  }
}
package com.example.chapter9;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

  private static final long serialVersionUID = 1L;

  private long id;
  private String firstName;
  private String middleInitial;
  private String lastName;
  private String address;
  private String city;
  private String state;
  private String zip;
}

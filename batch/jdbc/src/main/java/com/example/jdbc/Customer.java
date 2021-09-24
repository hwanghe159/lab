package com.example.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Customer {

  private Long id;
  private String firstName;
  private String middleInitial;
  private String lastName;
  private String address;
  private String city;
  private String state;
  private String zipCode;
}

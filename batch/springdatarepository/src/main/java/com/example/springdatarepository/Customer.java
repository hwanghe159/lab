package com.example.springdatarepository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "customer")
public class Customer {

  @Id
  private Long id;
  private String firstName;
  private String middleInitial;
  private String lastName;
  private String address;
  private String city;
  private String state;
  private String zipCode;
}
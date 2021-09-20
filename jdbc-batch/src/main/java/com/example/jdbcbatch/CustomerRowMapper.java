package com.example.jdbcbatch;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer> {

  @Override
  public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
    long id = rs.getLong("id");
    String firstName = rs.getString("firstName");
    String middleInitial = rs.getString("middleInitial");
    String lastName = rs.getString("lastName");
    String address = rs.getString("address");
    String city = rs.getString("city");
    String state = rs.getString("state");
    String zipCode = rs.getString("zipCode");

    return new Customer(id, firstName, middleInitial, lastName, address, city, state, zipCode);
  }
}

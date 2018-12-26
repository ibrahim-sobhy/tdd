package com.example.tdd.vehicle;

import java.util.Objects;

public class Vehicle {


  public String getId() {
    return id;
  }

  public Vehicle setId(String id) {
    this.id = id;
    return this;
  }

  private String id;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vehicle vehicle = (Vehicle) o;
    return Objects.equals(id, vehicle.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

package com.example.tdd.vehicle;

import java.util.Objects;

public class VehicleRadar {

  private VehicleRepository repo;

  public void listen(String vehicleId) {
    if (Objects.isNull(vehicleId))
      throw new IllegalArgumentException("VehicleId is empty");

    repo.findById(vehicleId);
    throw new IllegalArgumentException("VehicleId is not exist");
  }
}

package com.example.tdd;

import com.example.tdd.model.ShipModel;

import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.empty;

public class Ship {

  private static final int MAX_X = 1000;
  private int x;
  private int y;
  private ShipCommand command;
  private ShipRadar radar;
  private EmergencyAgency emergencyAgency;


  private Ship(int initialX, int initialY) {
    this.x = initialX;
    this.y = initialY;
  }

  public static Ship of(int initialX, int initialY) {
    return  new Ship(initialX, initialY);
  }

  public void move(int x, int y) {
    if( x > MAX_X)
    throw new IllegalArgumentException("X is invalid");

    if(command.shift(x,y)) {
      this.x = this.x + x;
      this.y = this.y + y;
    }
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Optional<Integer> findX(String shipId) {
    if(Objects.isNull(shipId))
      throw new IllegalArgumentException("ShipId is invalid");

    return radar.detectX(shipId);
  }

  public Optional<Integer> sos(String shipId) {
    if(Objects.isNull(shipId))
      throw new IllegalArgumentException("ShipId is invalid");

    return Optional.ofNullable(emergencyAgency.sos(shipId));
  }

  public Optional<ShipModel> find(String shipId) {
    throw new UnsupportedOperationException();
  }

  public ShipModel add(ShipModel expectedShip) {
    throw new UnsupportedOperationException();
  }
}

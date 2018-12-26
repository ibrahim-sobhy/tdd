package com.example.tdd;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/api/ship")
public class ShipApi {

  Ship ship;


  @GetMapping("/{shipId}")
  public ResponseEntity<Integer> find(
      @PathVariable("shipId") String shipId) {

    return ship.findX(shipId)
        .map(x -> ok(x))
        .orElse(notFound().build());
  }
}

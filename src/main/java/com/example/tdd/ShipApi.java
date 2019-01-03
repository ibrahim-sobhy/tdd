package com.example.tdd;

import com.example.tdd.model.ShipModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/api/ship")
public class ShipApi {

  Ship ship;

  @GetMapping("/{shipId}")
  public ResponseEntity<ShipModel> find(
      @PathVariable("shipId") String shipId) {

    return ship.find(shipId)
        .map(s -> ok(s))
        .orElse(notFound().build());
  }


  @GetMapping("/{shipId}/position")
  public ResponseEntity<Integer> findX(
      @PathVariable("shipId") String shipId) {

    return ship.findX(shipId)
        .map(x -> ok(x))
        .orElse(notFound().build());
  }

  @PostMapping
  public ResponseEntity<ShipModel> addNew(@RequestBody ShipModel shipModel) {
    ship.add(shipModel);
    return created(URI.create("/api/ship/"+shipModel.getId())).build();
  }
}

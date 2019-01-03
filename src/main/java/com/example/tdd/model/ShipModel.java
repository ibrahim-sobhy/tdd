package com.example.tdd.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Accessors(fluent = false)
public class ShipModel {
  private String id;
  private String name;
  private Integer xCoordinate;
  private Integer yCoordinate;
  @Singular
  private List<Integer> visitedPlaces;
}

package com.example.tdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ShipTest {

  int initialX = 10, initialY = 100;

  @InjectMocks
  private Ship ship = Ship.of(initialX, initialY);

  @Mock
  private ShipCommand command;

  @Mock
  private ShipRadar radar;

  @Mock
  private EmergencyAgency emergencyAgency;

  @Test
  public void shouldThrowIllegalArgumentExceptionIfXCoordinateExceedMax() {
    int x = 1500, y = 500;
    assertThatThrownBy( () -> ship.move(x,y))
        .isInstanceOf(IllegalArgumentException.class)
        .withFailMessage("X is invalid");

  }

  @Test
  public void shouldCreateShipWithInitialPosition() {

    // Call the testing target
    ship = Ship.of(initialX, initialY);

    assertThat(ship.getX())
        .isEqualTo(initialX);
    assertThat(ship.getY())
        .isEqualTo(initialY);
  }

  @Test
  public void shouldMoveShipToNewXandYCoordinates() {
    int x = 300, y = 500;

    // Set the assumptions
    when(command.shift(x,y)).thenReturn(true);

    // Call the testing target
    ship.move(x,y);

    // Assert the output
    assertThat(ship.getX())
        .isEqualTo(initialX + x);
    assertThat(ship.getY())
        .isEqualTo(initialY + y);

    verify(command, times(1)).shift(x,y);
  }

  @Test
  public void shouldDoNotMoveShipIfShiftFailed() {
    int x = 300, y = 500;

    // Set the assumptions
    when(command.shift(x,y)).thenReturn(false);

    // Call the testing target
    ship.move(x,y);

    // Call the testing target
    assertThat(ship.getX())
        .isEqualTo(initialX);
    assertThat(ship.getY())
        .isEqualTo(initialY);

    verify(command, times(1)).shift(x,y);
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionIfInquireForEmptyShipId() {

    String shipId = null;

    // Assumptions

    // Call the target
    assertThatThrownBy( () -> ship.findX(shipId))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("ShipId is invalid");

  }

  @Test
  public void shouldReturnEmptyIfInquireForNonExistShip() {
    String shipId = "1234";

    // Assumptions
    when(radar.detectX(shipId)).thenReturn(empty());

    // Call the target


    // Assertions
    assertThat(ship.findX(shipId))
        .isEmpty();

    verify(radar, times(1)).detectX(shipId);

  }

  @Test
  public void shouldReturnEmptyIfSOSForNonExistingShip() {
    String shipId = "1234";

    // Assumptions
    when(emergencyAgency.sos(shipId)).thenReturn(null);

    // Call the target

    // Assertions
    assertThat(ship.sos(shipId))
        .isEmpty();

    verify(emergencyAgency, times(1)).sos(shipId);



  }
}

package com.example.tdd.vehicle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class VehicleRadarTest {

  @InjectMocks
  private VehicleRadar vehicleRadar;

  @Mock
  private VehicleRepository repo;

  @Test
  public void shouldThrowIllegalArgumentExceptionIfListenToEmptyVehicleId() {
    String vehicleId = null;

    assertThatThrownBy( () -> vehicleRadar.listen(vehicleId) )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("VehicleId is empty");
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionIfListenNonExistVehicle() {
    String vehicleId = "1213RR";

    Vehicle vehicle = new Vehicle();
    vehicle.setId(vehicleId);

    when(repo.findById(vehicleId)).thenReturn(vehicle);

    assertThatThrownBy( () -> vehicleRadar.listen(vehicleId) )
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("VehicleId is not exist");

    verify(repo, times(1)).findById(vehicleId);

  }

}

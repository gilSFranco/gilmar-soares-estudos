package com.compasso.parking_management_spring_boot.service;

import com.compasso.parking_management_spring_boot.entities.ParkingSpot;
import com.compasso.parking_management_spring_boot.repository.ParkingSpotRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ParkingSpotService.class)
public class ParkingSpotServiceTest {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @MockBean
    private ParkingSpotRepository parkingSpotRepository;

    @Test
    public void getAllMonthlySpots_correctReturn() {
        ParkingSpot monthlySpot = new ParkingSpot(1L, true, true, null);
        ParkingSpot oneTimeSpot = new ParkingSpot(2L, false, true, null);
        when(parkingSpotRepository.findAll()).thenReturn(Arrays.asList(monthlySpot, oneTimeSpot));

        int result = parkingSpotService.getAllMonthlySpots();

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void getAllOneTimeSpots_correctReturn() {
        ParkingSpot monthlySpot = new ParkingSpot(1L, true, true, null);
        ParkingSpot oneTimeSpot = new ParkingSpot(2L, false, true, null);
        when(parkingSpotRepository.findAll()).thenReturn(Arrays.asList(monthlySpot, oneTimeSpot));

        int result = parkingSpotService.getAllOneTimeSpots();

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void getAllMonthlySpotsOccupied_correctReturn() {
        ParkingSpot monthlySpotOccupied = new ParkingSpot(1L, true, true, null);
        ParkingSpot monthlySpotFree = new ParkingSpot(2L, true, false, null);
        when(parkingSpotRepository.findAll()).thenReturn(Arrays.asList(monthlySpotOccupied, monthlySpotFree));

        int result = parkingSpotService.getAllMonthlySpotsOccupied();

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void getAllOneTimeSpotsOccupied_correctReturn() {
        ParkingSpot oneTimeSpotOccupied = new ParkingSpot(1L, false, true, null);
        ParkingSpot oneTimeSpotFree = new ParkingSpot(2L, false, false, null);
        when(parkingSpotRepository.findAll()).thenReturn(Arrays.asList(oneTimeSpotOccupied, oneTimeSpotFree));

        int result = parkingSpotService.getAllOneTimeSpotsOccupied();

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void getAvalibleSpotM_returnMonthlyVacanciesAvailable() {
        ParkingSpot availableMonthlySpot = new ParkingSpot(1L, true, false, null);
        ParkingSpot occupiedMonthlySpot = new ParkingSpot(2L, true, true, null);
        when(parkingSpotRepository.findAll()).thenReturn(Arrays.asList(availableMonthlySpot, occupiedMonthlySpot));

        List<ParkingSpot> result = parkingSpotService.getAvalibleSpotM();

        assertThat(result).hasSize(1);
    }

    @Test
    public void getAvalibleSpotO_returnIndividualVacanciesAvailable() {
        ParkingSpot availableOneTimeSpot = new ParkingSpot(1L, false, false, null);
        ParkingSpot occupiedOneTimeSpot = new ParkingSpot(2L, false, true, null);
        when(parkingSpotRepository.findAll()).thenReturn(Arrays.asList(availableOneTimeSpot, occupiedOneTimeSpot));

        List<ParkingSpot> result = parkingSpotService.getAvalibleSpotO();

        assertThat(result).hasSize(1);
    }

    @Test
    public void register_vacancySuccessfullyRegistered() {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setLicensePlate("AAA-1111");

        parkingSpotService.register(parkingSpot);

        verify(parkingSpotRepository, times(1)).save(parkingSpot);
    }
}

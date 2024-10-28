package com.compasso.parking_management_spring_boot.repository;

import com.compasso.parking_management_spring_boot.entities.ParkingSpot;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class ParkingSpotRepositoryTest {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void createSpots_WithValidData_ReturnsParkingSpots() {
        ParkingSpot spot = new ParkingSpot(null, true, true, "PPL-1234");

        ParkingSpot newSpot = parkingSpotRepository.save(spot);
        ParkingSpot sut = entityManager.find(ParkingSpot.class, newSpot.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getMonthly()).isEqualTo(spot.getMonthly());
        assertThat(sut.getOccupied()).isEqualTo(spot.getOccupied());
        assertThat(sut.getLicensePlate()).isEqualTo(spot.getLicensePlate());
    }

    @Test
    void createSpots_WithInvalidData_ThrowsException() {
        ParkingSpot emptySpot = new ParkingSpot();
        ParkingSpot invalidSpot = new ParkingSpot(null, null, null, null);

        assertThatThrownBy(() -> parkingSpotRepository.save(emptySpot)).isInstanceOf(DataIntegrityViolationException.class);
        assertThatThrownBy(() -> parkingSpotRepository.save(invalidSpot)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void listSpots_ReturnsEmpty() {
        List<ParkingSpot> parkingSpots = parkingSpotRepository.findAll();

        assertThat(parkingSpots).isEmpty();
    }

    @Test
    void getSpots_ByExistingPlate_ReturnsParkingSpot() {
        ParkingSpot spot = new ParkingSpot(null, true, true, "PPL-1234");

        ParkingSpot newSpot = entityManager.persistFlushFind(spot);

        List<ParkingSpot> spots = parkingSpotRepository.findByLicensePlate(newSpot.getLicensePlate());

        assertThat(spots).isNotEmpty();
        assertThat(spots.get(0)).isEqualTo(newSpot);
    }

    @Test
    void getSpots_ByUnexistingPlate_ReturnsParkingSpot() {
        List<ParkingSpot> spots = parkingSpotRepository.findByLicensePlate("ABC-1234");

        assertThat(spots).isEmpty();
    }

    @Test
    void getSpots_ByExistingId_ReturnsParkingSpot() {
        ParkingSpot spot = new ParkingSpot(null, true, true, "PPL-1234");

        ParkingSpot newSpot = entityManager.persistFlushFind(spot);
        Optional<ParkingSpot> spots = parkingSpotRepository.findById(newSpot.getId());

        assertThat(spots).isPresent();
        assertThat(spots.get().getLicensePlate()).isEqualTo(spot.getLicensePlate());
        assertThat(spots.get().getMonthly()).isTrue();
        assertThat(spots.get().getOccupied()).isTrue();
    }

    @Test
    void getSpots_ByUnexistingId_ReturnsParkingSpot() {
        Optional<ParkingSpot> spots = parkingSpotRepository.findById(1L);

        assertThat(spots).isEmpty();
    }

    @Test
    void updateSpots_ReturnsParkingSpot() {
        ParkingSpot spot = new ParkingSpot(null, true, true, "PPL-1234");
        ParkingSpot newSpot = entityManager.persistFlushFind(spot);

        ParkingSpot sut = entityManager.find(ParkingSpot.class, newSpot.getId());

        sut.setOccupied(false);
        parkingSpotRepository.save(sut);

        assertThat(sut).isNotNull();
        assertThat(sut.getOccupied()).isFalse();
    }
}

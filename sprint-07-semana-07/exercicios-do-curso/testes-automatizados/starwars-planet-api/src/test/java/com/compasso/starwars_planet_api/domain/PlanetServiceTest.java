package com.compasso.starwars_planet_api.domain;

import static com.compasso.starwars_planet_api.common.PlanetConstants.PLANET;
import static com.compasso.starwars_planet_api.common.PlanetConstants.INVALID_PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;

    @Mock
    private PlanetRepository planetRepository;

    @Test
    void createPlanet_WithValidData_ReturnsPlanet() {
        when(planetRepository.save(PLANET)).thenReturn(PLANET);
        Planet sut = planetService.create(PLANET);

        assertThat(sut).isEqualTo(PLANET);
    }

    @Test
    void createPlanet_WithInvalidData_ThrowsException() {
        when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void findPlanetById_ByExistingId_ReturnsPlanet() {
        when(planetRepository.findById(1L)).thenReturn(Optional.of(PLANET));

        Optional<Planet> sut = planetService.findById(1L);

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANET);
    }

    @Test
    void findPlanetById_ByUnexistingId_ReturnsEmpty() {
        when(planetRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Planet> sut = planetService.findById(1L);

        assertThat(sut).isEmpty();
    }

    @Test
    void findPlanetByName_ByExistingName_ReturnsPlanet() {
        when(planetRepository.findByName(PLANET.getName())).thenReturn(Optional.of(PLANET));

        Optional<Planet> sut = planetService.findByName(PLANET.getName());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANET);
    }

    @Test
    void findPlanetByName_ByUnexistingName_ReturnsEmpty() {
        final String name = "Unexisting Name";
        when(planetRepository.findByName(name)).thenReturn(Optional.empty());

        Optional<Planet> sut = planetService.findByName(name);

        assertThat(sut).isEmpty();
    }

    @Test
    void listPlanets_ReturnsAllPlanets() {
        List<Planet> planets = new ArrayList<>() {
            {
                add(PLANET);
            }
        };

        Example<Planet> query = QueryBuilder.makeQuery(new Planet(PLANET.getClimate(), PLANET.getTerrain()));
        when(planetRepository.findAll(query)).thenReturn(planets);
        List<Planet> sut = planetService.list(PLANET.getTerrain(), PLANET.getClimate());

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);
        assertThat(sut.get(0)).isEqualTo(PLANET);
    }

    @Test
    void listPlanets_ReturnsNoPlanets() {
        when(planetRepository.findAll(any())).thenReturn(Collections.emptyList());
        List<Planet> sut = planetService.list(PLANET.getTerrain(), PLANET.getClimate());

        assertThat(sut).isEmpty();
    }

    @Test
    void deletePlanet_WithExistingId_doesNotThrowAnyException() {
        assertThatCode(() -> planetService.delete(1L)).doesNotThrowAnyException();
    }

    @Test
    void deletePlanet_WithUnexistingId_ThrowsException() {
        doThrow(new RuntimeException()).when(planetRepository).deleteById(99L);
        assertThatThrownBy(() -> planetService.delete(99L)).isInstanceOf(RuntimeException.class);
    }
}

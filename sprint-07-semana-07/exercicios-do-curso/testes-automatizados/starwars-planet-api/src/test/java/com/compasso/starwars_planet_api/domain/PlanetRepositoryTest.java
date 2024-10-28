package com.compasso.starwars_planet_api.domain;

import static com.compasso.starwars_planet_api.common.PlanetConstants.PLANET;
import static com.compasso.starwars_planet_api.common.PlanetConstants.TATOOINE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@DataJpaTest
class PlanetRepositoryTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private TestEntityManager entityManager;

    @AfterEach
    public void afterEach() {
        PLANET.setId(null);
    }

    @Test
    void createPlanet_WithValidData_ReturnsPlanet() {
        Planet planet = planetRepository.save(PLANET);

        Planet sut = entityManager.find(Planet.class, planet.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(planet.getName());
        assertThat(sut.getTerrain()).isEqualTo(planet.getTerrain());
        assertThat(sut.getClimate()).isEqualTo(planet.getClimate());
    }

    @ParameterizedTest
    @MethodSource("providesInvalidPlanets")
    void createPlanet_WithInvalidData_ThrowsException(Planet planet) {
        assertThatThrownBy(() -> planetRepository.save(planet)).isInstanceOf(RuntimeException.class);
    }

    private static Stream<Arguments> providesInvalidPlanets() {
        return Stream.of(
                Arguments.of(new Planet(null, "climate", "terrain")),
                Arguments.of(new Planet("name", null, "terrain")),
                Arguments.of(new Planet("name", "climate", null)),
                Arguments.of(new Planet(null, null, "terrain")),
                Arguments.of(new Planet(null, "climate", null)),
                Arguments.of(new Planet("name", null, null)),
                Arguments.of(new Planet(null, null, null)),
                Arguments.of(new Planet("", "climate", "terrain")),
                Arguments.of(new Planet("name", "", "terrain")),
                Arguments.of(new Planet("name", "climate", "")),
                Arguments.of(new Planet("", "", "terrain")),
                Arguments.of(new Planet("", "climate", "")),
                Arguments.of(new Planet("name", "", "")),
                Arguments.of(new Planet("", "", ""))
        );
    }

    @Test
    void createPlanet_WithExistingName_ThrowsException() {
        Planet planet = entityManager.persistFlushFind(PLANET);
        entityManager.detach(planet);
        planet.setId(null);

        assertThatThrownBy(() -> planetRepository.save(planet)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void getPlanet_ByExistingId_ReturnsPlanet() {
        Planet planet = entityManager.persistFlushFind(PLANET);
        Optional<Planet> planetOpt = planetRepository.findById(planet.getId());

        assertThat(planetOpt).isNotEmpty();
        assertThat(planetOpt.get()).isEqualTo(planet);
    }

    @Test
    void getPlanet_ByUnexistingId_ReturnsEmpty() {
        Optional<Planet> planetOpt = planetRepository.findById(1L);

        assertThat(planetOpt).isEmpty();
    }

    @Test
    void getPlanet_ByExistingName_ReturnsPlanet() {
        Planet planet = entityManager.persistFlushFind(PLANET);
        Optional<Planet> planetOpt = planetRepository.findByName(planet.getName());

        assertThat(planetOpt).isNotEmpty();
        assertThat(planetOpt.get()).isEqualTo(planet);
    }

    @Test
    void getPlanet_ByUnexistingName_ReturnsNotFound() throws Exception {
        Optional<Planet> planetOpt = planetRepository.findByName("name");

        assertThat(planetOpt).isEmpty();
    }

    @Sql(scripts = "/import_planets.sql")
    @Test
    void listPlanets_ReturnsFilteredPlanets() {
        Example<Planet> queryWithoutFilters = QueryBuilder.makeQuery(new Planet());
        Example<Planet> queryWithFilters = QueryBuilder.makeQuery(new Planet(TATOOINE.getClimate(), TATOOINE.getTerrain()));

        List<Planet> responseWithoutFilters = planetRepository.findAll(queryWithoutFilters);
        List<Planet> responseWithFilters = planetRepository.findAll(queryWithFilters);

        assertThat(responseWithoutFilters).isNotEmpty();
        assertThat(responseWithoutFilters).hasSize(3);
        assertThat(responseWithFilters).isNotEmpty();
        assertThat(responseWithFilters).hasSize(1);
        assertThat(responseWithFilters.get(0)).isEqualTo(TATOOINE);
    }

    @Test
    void listPlanets_ReturnsNoPlanets() {
        Example<Planet> query = QueryBuilder.makeQuery(new Planet());

        List<Planet> response = planetRepository.findAll(query);

        assertThat(response).isEmpty();
    }

    @Test
    void removePlanet_WithExistingId_RemovesPlanetFromDatabase() {
        Planet planet = entityManager.persistFlushFind(PLANET);

        planetRepository.deleteById(planet.getId());

        Planet removedPlanet = entityManager.find(Planet.class, planet.getId());
        assertThat(removedPlanet).isNull();
    }

    @Test @Sql(scripts = "/import_planets.sql")
    void removePlanet_WithNonExistingId_DoestNotChangePlanetList() {
        planetRepository.deleteById(4L);
        assertThat(entityManager.find(Planet.class, 1L)).isInstanceOf(Planet.class);
        assertThat(entityManager.find(Planet.class, 2L)).isInstanceOf(Planet.class);
        assertThat(entityManager.find(Planet.class, 3L)).isInstanceOf(Planet.class);
    }
}

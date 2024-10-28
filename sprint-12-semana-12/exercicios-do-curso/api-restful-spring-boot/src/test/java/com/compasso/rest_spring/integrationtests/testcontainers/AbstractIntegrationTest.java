package com.compasso.rest_spring.integrationtests.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;
import java.util.stream.Stream;

import static com.compasso.rest_spring.integrationtests.testcontainers.AbstractIntegrationTest.Initializer.mysql;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:8.4.2"));

        private static void startContainers() {
            Startables.deepStart(Stream.of(mysql)).join();
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();

            ConfigurableEnvironment environment = applicationContext.getEnvironment();

            MapPropertySource testContainers = new MapPropertySource(
                    "testcontainers",
                    (Map) createConnectionConfiguration()
            );

            environment.getPropertySources().addFirst(testContainers);
        }
    }

    private static Map<String, String> createConnectionConfiguration() {
        return Map.of(
                "spring.datasource.url:", mysql.getJdbcUrl(),
                "spring.datasource.username:", mysql.getUsername(),
                "spring.datasource.password:", mysql.getPassword()
        );
    }
}

package br.com.erudio.springproject.integrationtests.testcontainer;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static PostgreSQLContainer<?> sqlContainer = new PostgreSQLContainer<>("postgres:15.3");
        
        private static void startContainers() {
            Startables.deepStart(Stream.of(sqlContainer)).join();
        }

        @Contract(" -> new")
        private static @NotNull Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url = ", sqlContainer.getJdbcUrl(),
                    "spring.datasource.username = ", sqlContainer.getUsername(),
                    "spring.datasource.password = ", sqlContainer.getPassword()
            );
        }

        @SuppressWarnings({"ratypes", "unchecked"})
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testcontainers = new MapPropertySource(
                    "testcontainers",
                    (Map) createConnectionConfiguration()
            );
            environment.getPropertySources().addFirst(testcontainers);
        }
    }
}

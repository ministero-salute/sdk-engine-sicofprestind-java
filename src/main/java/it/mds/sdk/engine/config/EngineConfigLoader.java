/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.engine.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.ResourcePropertySource;

//import lombok.extern.slf4j.Slf4j;

//@Slf4j
public class EngineConfigLoader implements EnvironmentPostProcessor {

    private static final String CONFIG_RUN_PROPERTIES = "config";
    private static final String PROPERTIES_FILENAME = "config-flusso.properties";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        addInternalConfig(environment);
        addArgumentsConfig(environment);
    }

    private void addInternalConfig(ConfigurableEnvironment environment) {
        try {
            add(new ResourcePropertySource(new ClassPathResource(PROPERTIES_FILENAME)), environment);
        } catch (IOException e) {
            throw new EngineConfigException(e.getMessage(), e);
        }
    }

    private void addArgumentsConfig(ConfigurableEnvironment environment) {
        Optional.ofNullable(environment.getProperty(CONFIG_RUN_PROPERTIES))
                .map(Paths::get)
                .filter(Files::exists)
                .map(this::resourcePropertySource)
                .ifPresent(rps ->
                        add(rps, environment)
                );
    }

    private void add(ResourcePropertySource propertySource, ConfigurableEnvironment environment) {
        environment.getPropertySources().addFirst(propertySource);
    }

    private ResourcePropertySource resourcePropertySource(Path path) {
        try {
            return new ResourcePropertySource(new FileSystemResource(path));
        } catch (IOException io) {
            throw new EngineConfigException(io.getMessage(), io);
        }
    }

}

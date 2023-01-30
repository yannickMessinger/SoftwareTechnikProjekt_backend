package de.hsrm.mi.swt02.backend.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// https://springdoc.org/

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api () {
        return GroupedOpenApi.builder()
                .group("backend-api")
                .pathsToMatch("/api/**")
                .build();
    }
}

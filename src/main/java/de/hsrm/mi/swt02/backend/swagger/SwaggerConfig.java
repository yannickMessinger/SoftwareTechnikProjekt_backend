package de.hsrm.mi.swt02.backend.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// https://springdoc.org/

@Configuration
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api () {
        return GroupedOpenApi.builder()
                .group("backend-api")
                .pathsToMatch("/api/**")
                .build();
    }
}

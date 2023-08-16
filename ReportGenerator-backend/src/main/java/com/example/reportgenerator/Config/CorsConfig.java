package com.example.reportgenerator.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for setting up Cross-Origin Resource Sharing (CORS) configuration.
 */
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configure CORS settings for the application.
     *
     * @param registry The CORS registry to configure.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Map the CORS configuration to all endpoints.
                .allowedOrigins("http://localhost:3000")  // Allow requests from this origin.
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow these HTTP methods.
                .allowedHeaders("*")  // Allow all headers in requests.
                .allowCredentials(true);  // Allow including cookies and other credentials in requests.
    }
}

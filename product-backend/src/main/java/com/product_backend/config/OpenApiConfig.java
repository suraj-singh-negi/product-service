package com.product_backend.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                    .info( new Info()
                    .title("Product")
                    .version("1.0")
                    .description("Here you can find all the required API documented")
                    ).servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Server")
                    ));
    }

}

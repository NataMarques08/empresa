package com.nata.empresa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api for manage company employees")
                        .version("v1")
                        .description("Api Documentation for more understanding")
                        .license(new License()
                                .name("GNU General Pubic License v3.0")
                                .url("https://www.gnu.org/licenses/gpl-3.0.en.html")));
    }

}

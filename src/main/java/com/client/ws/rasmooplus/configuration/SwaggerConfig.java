package com.client.ws.rasmooplus.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // bean de config
@EnableSwagger2 // faz parte de um swagger
public class SwaggerConfig {

    // criando bean
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).groupName("v0").select()
                .apis(RequestHandlerSelectors.basePackage("com.client.ws.rasmooplus.controller"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        return apiInfoBuilder
                .title("Rasmoo Plus")
                .description("API to serve the Rasmoo Plus client")
                .version("0.0.1")
                .license("Rasmoo technology courses")
                .build();
    }
}

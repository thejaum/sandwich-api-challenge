package com.thejaum.challenge.sandwich.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.thejaum.challenge.sandwich.controller"))
                    .paths(regex("/v1.*"))
                    .build()
                .apiInfo(metaData());
    }
	
	private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Sandwich Rest API")
                .description("challenge")
                .version("1.0")
                .contact(new Contact("João Castro", "http://github.com/thejaum", "v.jcastro@outlook.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/license/LICENSE-2.0")
                .build();
    }
}

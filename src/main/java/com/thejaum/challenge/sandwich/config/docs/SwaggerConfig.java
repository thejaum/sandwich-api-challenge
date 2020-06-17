package com.thejaum.challenge.sandwich.config.docs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket apiDoc() {
		List<Parameter> params = new ArrayList();
		params.add(getGlobalParameters());
		return new Docket(DocumentationType.SWAGGER_2)
	        .select()
		        .apis(RequestHandlerSelectors.basePackage("com.thejaum.challenge.sandwich.controller"))
		        .paths(PathSelectors.any())
		        .build()
	        .apiInfo(metaData())
	        .globalOperationParameters(params);
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
	private Parameter getGlobalParameters() {
		return new ParameterBuilder()
			    .name("Authorization")
			    .description("Access Token")
			    .modelRef(new ModelRef("string"))
			    .parameterType("header")
			    .required(true)
			    .build();
	}
	
}

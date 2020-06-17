package com.thejaum.challenge.sandwich.config.docs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.fasterxml.classmate.TypeResolver;
import com.thejaum.challenge.sandwich.dto.AppToken;
import com.thejaum.challenge.sandwich.dto.UserLoginDTO;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
@Slf4j
public class SwaggerLoginListingScanner implements ApiListingScannerPlugin {

    private final CachingOperationNameGenerator operationNames;
    
    public SwaggerLoginListingScanner(CachingOperationNameGenerator operationNames) {//<9>
        this.operationNames = operationNames;
    }

    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        Set<String> tags = new HashSet<>();
        tags.add("security");
        context.getAdditionalModels().add(new TypeResolver().resolve(UserLoginDTO.class));
        context.getAdditionalModels().add(new TypeResolver().resolve(AppToken.class));
    	return new ArrayList<>(
                Arrays.asList(
                        new ApiDescription(null, "/login", "login", Collections.singletonList(
                                new OperationBuilder(operationNames)
                                        .summary("login")
                                        .tags(tags)
                                        .authorizations(new ArrayList<>())
                                        .position(1)
                                        .codegenMethodNameStem("loginPost")
                                        .method(HttpMethod.POST)
                                        .notes("Endpoint to check credentials and get new Token.")
                                        .parameters(
                                                Arrays.asList(
                                                        new ParameterBuilder()
                                                        		.name("Credentials")
                                                                .description("Inform an username and password do attempt login.")
                                                                .type(new TypeResolver().resolve(UserLoginDTO.class))
                                                                .parameterType("body")
                                                                .parameterAccess("access")
                                                                .required(true)
                                                                .modelRef(new ModelRef("UserLoginDTO"))
                                                                .build()
                                                )
                                        ).responseMessages(responseMessages())
                                        .responseModel(new ModelRef(("AppToken")))
                                        .build()
                        ), false)));
    }

    private Set<ResponseMessage> responseMessages() { //<8>
    	Set<ResponseMessage> responses = new HashSet<>();
    	responses.add(new ResponseMessageBuilder()
                .code(200)
                .responseModel(new ModelRef("AppToken"))
                .message("OK")
                .build());
    	/*responses.add(new ResponseMessageBuilder()
                .code(401)
                //.responseModel(new ModelRef("ApiError"))
                .message("Unauthorized")
                .build());
    	responses.add(new ResponseMessageBuilder()
                .code(403)
                .message("Forbidden")
                //.responseModel(new ModelRef("ApiError"))
                .build());
    	responses.add(new ResponseMessageBuilder()
                .code(404)
                //.responseModel(new ModelRef("ApiError"))
                .build());*/
        return responses;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return DocumentationType.SWAGGER_2.equals(delimiter);
    }
}

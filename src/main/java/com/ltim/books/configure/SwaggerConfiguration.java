package com.ltim.books.configure;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfiguration {
	
	
	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
	    return openApi -> {
	        openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
	            ApiResponses apiResponses = operation.getResponses();
	            ApiResponse apiResponse = new ApiResponse().description("Custom Error")
	                    .content(new Content()
	                            .addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));
	            apiResponses.addApiResponse("400", apiResponse);
	        }));
	    };
	}


}

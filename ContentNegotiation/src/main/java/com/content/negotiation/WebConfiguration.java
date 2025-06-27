package com.content.negotiation;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	 @Override
	    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		 configurer
		 .favorParameter(true) // Allow format via query param like ?format=json
		 .parameterName("format") // Mention paramater name
        .defaultContentType(MediaType.APPLICATION_JSON)
        .mediaType("json", MediaType.APPLICATION_JSON)
        .mediaType("xml", MediaType.APPLICATION_XML);
	 }
}

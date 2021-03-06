package br.com.eleflow.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwagguerConfig {

	@Primary
	@Bean
	public LinkDiscoverers discoverers() {
	    List<LinkDiscoverer> plugins = new ArrayList<>();
	    plugins.add(new CollectionJsonLinkDiscoverer());
	    return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()        
          .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
          .apis(RequestHandlerSelectors.basePackage("br.com.eleflow.controller"))
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {
		return new ApiInfo(
			      "EleFlow", 
			      "API eleflow selective process", 
			      "1.0.0", 
			      "", 
			      new Contact("EleFlow", "https://eleflow.gupy.io/", ""), 
			      "", "", Collections.emptyList());
	}
}

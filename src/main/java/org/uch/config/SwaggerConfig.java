package org.uch.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	/**
	 * ResponseEntity<?> 이런 식으로 사용하면 response 객체를 인식 못함.
	 * throws
	 * https://github.com/springfox/springfox/issues/1812
	 * .antMatchers("/configuration/ui","/webjars/**","/swagger-ui.html","/swagger-resources","/configuration/security","/v2/api-docs").permitAll()
	 */
	@Bean
	public Docket swaggerApi(){
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.select()
			.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
			.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
			.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.data.rest.webmvc")))
			.paths(Predicates.not(PathSelectors.regex("/error")))
			.build()
			;
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
			"Swagger API Test",
			"Uch Test Project",
			"1.0.0",
			null,
			null,
			null, null, Collections.emptyList());
	}
}
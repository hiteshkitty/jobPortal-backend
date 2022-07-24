package com.troika;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.troika")
@EnableCaching
public class JobportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobportalApplication.class, args);
	}

	/**
	 * Message converter used for converting json to objects and vice versa.
	 * 
	 * @return MappingJackson2HttpMessageConverter
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}

	@Bean
	public MarshallingHttpMessageConverter marshallingMessageConverter() {
		MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
		converter.setMarshaller(jaxbMarshaller());
		converter.setUnmarshaller(jaxbMarshaller());
		return converter;
	}

	@Bean
	public Jaxb2Marshaller jaxbMarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.troika.domain.view");// you need to
																// specify the
																// package where
																// your
																// @XmlRootElement
																// s reside
		Map<String, Object> props = new HashMap<String, Object>();
		props.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setMarshallerProperties(props);
		return marshaller;
	}

	/**
	 * This initiates the swagger application to generate the API documentation.
	 *
	 * @return Docket
	 */
	// @Bean
	// public Docket initSwagger() {
	// return new
	// Docket(DocumentationType.SWAGGER_2).groupName("rest/doc").apiInfo(apiInfo()).select().build();
	// }
	//
	// /**
	// * Sets the default values.
	// *
	// * @return
	// */
	// private ApiInfo apiInfo() {
	// return new ApiInfoBuilder().title("JOB").description("Job
	// portal").termsOfServiceUrl("Terms and Conditions")
	// .version("1.0").build();
	// }

	// @Bean
	// public Docket rsApi() {
	// return new
	// Docket(DocumentationType.SWAGGER_12).apiInfo(apiInfo()).select()
	// .apis(RequestHandlerSelectors.basePackage("com.troika.aggregator.services")).paths(PathSelectors.any())
	// .build().pathMapping("/").useDefaultResponseMessages(false);
	// }
	//
	// private ApiInfo apiInfo() {
	// return new ApiInfoBuilder().title("Test API").description("Test
	// API").version("0.0.10.SNAPSHOT")
	// .termsOfServiceUrl("").contact("Test
	// company").license("Public").licenseUrl("http://example.com/")
	// .build();
	// }

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setShared(true);
		return cmfb;
	}

	/**
	 * This initiates the swagger application to generate the API documentation.
	 * 
	 * @return Docket
	 */
	@Bean
	public Docket initSwagger() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("rest/doc").apiInfo(apiInfo()).select().build();
	}

	/**
	 * Sets the default values.
	 * 
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Troika Systems").description("Troika Systems").termsOfServiceUrl("Terms and conditions")
				.version("1.0").build();
	}
}

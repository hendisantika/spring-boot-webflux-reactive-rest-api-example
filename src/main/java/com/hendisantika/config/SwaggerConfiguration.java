package com.hendisantika.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-reactive-rest-api-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/10/21
 * Time: 19.23
 */
@Configuration
public class SwaggerConfiguration implements WebFluxConfigurer {
    //    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .enable(true)
//                //.genericModelSubstitutes(Mono.class, Flux.class, Publisher.class)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("Reactive Stream Starter Demo")
//                .description("Reactive Stream Starter Demo")
//                .version("1.0").build();
//    }
    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDescription, @Value("${application" +
            "-version}") String appVersion) {
        Contact contact = new Contact();
        contact.setEmail("hendisantika@yahoo.co.id");
        contact.setName("HENDI SANTIKA");
        contact.setUrl("https://s.id/hendisantika");

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(new Info()
                        .title("Reactive Stream Starter Demo")
                        .version(appVersion)
                        .description(appDescription)
                        .contact(contact)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));

    }
}

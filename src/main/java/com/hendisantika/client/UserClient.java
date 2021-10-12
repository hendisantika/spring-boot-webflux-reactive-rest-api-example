package com.hendisantika.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-reactive-rest-api-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/10/21
 * Time: 19.24
 */
@Component
@Slf4j
public class UserClient {
    private final WebClient client = WebClient.create("http://localhost:8080");
}

package com.hendisantika.client;

import com.hendisantika.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    public Mono<User> getUser(String userId) {
        return client.get()
                .uri("/users/{userId}", userId)
                .retrieve()
                .bodyToMono(User.class).log(" User fetched ");
    }

    public Flux<User> getAllUsers() {
        return client.get()
                .uri("/users")
                .exchange().flatMapMany(clientResponse -> clientResponse.bodyToFlux(User.class)).log("Users Fetched :" +
                        " ");
    }
}

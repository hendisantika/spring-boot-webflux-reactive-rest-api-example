package com.hendisantika.repository;

import com.hendisantika.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-reactive-rest-api-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/10/21
 * Time: 19.18
 */
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
    @Query("select * from users where age >= $1")
    Flux<User> findByAge(int age);
}

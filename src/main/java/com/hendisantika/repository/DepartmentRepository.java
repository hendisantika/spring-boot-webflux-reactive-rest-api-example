package com.hendisantika.repository;

import com.hendisantika.model.Department;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-reactive-rest-api-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/10/21
 * Time: 19.17
 */
public interface DepartmentRepository extends ReactiveCrudRepository<Department, Integer> {
    Mono<Department> findByUserId(Integer userId);
}

package com.hendisantika.controller;

import com.hendisantika.dto.UserDepartmentDTO;
import com.hendisantika.model.User;
import com.hendisantika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-reactive-rest-api-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/10/21
 * Time: 19.27
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable Integer userId) {
        Mono<User> user = userService.findById(userId);
        return user.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public Mono<ResponseEntity<User>> updateUserById(@PathVariable Integer userId, @RequestBody User user) {
        return userService.updateUser(userId, user)
                .map(updatedUser -> ResponseEntity.ok(updatedUser))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{userId}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable Integer userId) {
        return userService.deleteUser(userId)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/age/{age}")
    public Flux<User> getUsersByAge(@PathVariable int age) {
        return userService.findUsersByAge(age);
    }

    @PostMapping("/search/id")
    public Flux<User> fetchUsersByIds(@RequestBody List<Integer> ids) {
        return userService.fetchUsers(ids);
    }

    @GetMapping("/{userId}/department")
    public Mono<UserDepartmentDTO> fetchUserAndDepartment(@PathVariable Integer userId) {
        return userService.fetchUserAndDepartment(userId);
    }
}

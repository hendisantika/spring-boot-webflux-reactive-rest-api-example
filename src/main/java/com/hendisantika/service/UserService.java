package com.hendisantika.service;

import com.hendisantika.dto.UserDepartmentDTO;
import com.hendisantika.model.Department;
import com.hendisantika.model.User;
import com.hendisantika.repository.DepartmentRepository;
import com.hendisantika.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-reactive-rest-api-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/10/21
 * Time: 19.18
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService {

    private final UserRepository userRepository;

    private final DepartmentRepository departmentRepository;

    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    public Mono<User> updateUser(Integer userId, User user) {
        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setAge(user.getAge());
                    dbUser.setSalary(user.getSalary());
                    return userRepository.save(dbUser);
                });
    }

    public Mono<User> deleteUser(Integer userId) {
        return userRepository.findById(userId)
                .flatMap(existingUser -> userRepository.delete(existingUser)
                        .then(Mono.just(existingUser)));
    }

    public Flux<User> findUsersByAge(int age) {
        return userRepository.findByAge(age);
    }

    public Flux<User> fetchUsers(List<Integer> userIds) {
        return Flux.fromIterable(userIds)
                .parallel()
                .runOn(Schedulers.parallel())
                .flatMap(i -> findById(i))
                .ordered((u1, u2) -> u2.getId() - u1.getId());
    }

    private Mono<Department> getDepartmentByUserId(Integer userId) {
        return departmentRepository.findByUserId(userId);
    }

    public Mono<UserDepartmentDTO> fetchUserAndDepartment(Integer userId) {
        Mono<User> user = findById(userId).subscribeOn(Schedulers.boundedElastic());
        Mono<Department> department = getDepartmentByUserId(userId).subscribeOn(Schedulers.boundedElastic());
        return Mono.zip(user, department, userDepartmentDTOBiFunction);
    }

    private final BiFunction<User, Department, UserDepartmentDTO> userDepartmentDTOBiFunction =
            (x1, x2) -> UserDepartmentDTO.builder()
            .age(x1.getAge())
            .departmentId(x2.getId())
            .departmentName(x2.getName())
            .userName(x1.getName())
            .userId(x1.getId())
            .loc(x2.getLoc())
            .salary(x1.getSalary()).build();

}

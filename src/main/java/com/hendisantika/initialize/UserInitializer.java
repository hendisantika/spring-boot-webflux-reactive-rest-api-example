package com.hendisantika.initialize;

import com.hendisantika.model.User;
import com.hendisantika.repository.DepartmentRepository;
import com.hendisantika.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-reactive-rest-api-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/10/21
 * Time: 19.30
 */
@Component
@Profile("!test")
@Slf4j
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) {
        initialDataSetup();
    }

    private List<User> getData() {
        return Arrays.asList(new User(null, "Uzumaki Naruto", 30, 10000),
                new User(null, "Uchiha Sasuke", 35, 1000),
                new User(null, "Haruno Sakura", 30, 1000000));
    }
}

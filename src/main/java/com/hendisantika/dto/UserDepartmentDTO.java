package com.hendisantika.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webflux-reactive-rest-api-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/10/21
 * Time: 19.21
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDepartmentDTO {
    private Integer userId;
    private String userName;
    private int age;
    private double salary;
    private Integer departmentId;
    private String departmentName;
    private String loc;
}

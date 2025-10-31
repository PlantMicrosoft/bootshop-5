package org.yiqixue.bootshop.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * MyBatis用户实体类
 * 对应数据库中的 mybatis_users 表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MybatisUser {

    private Long id;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须在3-20个字符之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码长度不能少于6个字符")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "电话号码不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "请输入正确的手机号码")
    private String phone;

    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @NotNull(message = "请选择性别")
    private String gender;

    @Past(message = "出生日期必须是过去的日期")
    private LocalDate birthDate;

    @Min(value = 18, message = "年龄必须大于等于18岁")
    @Max(value = 120, message = "年龄不能超过120岁")
    private Integer age;

    @NotBlank(message = "请选择国家")
    private String country;

    @NotBlank(message = "请选择城市")
    private String city;

    private String bio;
    private String hobbies;
    private Boolean newsletterSubscription = false;
    private Boolean termsAccepted = false;
    private LocalDate registerDate;
}
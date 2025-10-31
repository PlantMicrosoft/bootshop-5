package org.yiqixue.bootshop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Set;

/**
 * 用户实体类
 * 对应数据库中的 user 表
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RealUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须在3-20个字符之间")
    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码长度不能少于6个字符")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "电话号码不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "请输入正确的手机号码")
    @Column(length = 20)
    private String phone;

    @NotBlank(message = "真实姓名不能为空")
    @Column(name = "real_name", length = 50)
    private String realName;

    @NotNull(message = "请选择性别")
    @Column(nullable = false)
    private String gender; // male, female, other

    @Past(message = "出生日期必须是过去的日期")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Min(value = 18, message = "年龄必须大于等于18岁")
    @Max(value = 120, message = "年龄不能超过120岁")
    private Integer age;

    @NotBlank(message = "请选择国家")
    @Column(length = 50)
    private String country;

    @NotBlank(message = "请选择城市")
    @Column(length = 50)
    private String city;

    @Column(length = 500)
    private String bio; // 个人简介

    // 兴趣爱好（多选）存储为逗号分隔的字符串
    @Column(length = 200)
    private String hobbies;

    @Column(name = "newsletter_subscription")
    private Boolean newsletterSubscription = false;

    @Column(name = "terms_accepted")
    private Boolean termsAccepted = false;

    @Column(name = "register_date")
    private LocalDate registerDate;

    /**
     * JPA需要在实体初始化时设置默认值
     */
    @PrePersist
    protected void onCreate() {
        if (registerDate == null) {
            registerDate = LocalDate.now();
        }
        if (newsletterSubscription == null) {
            newsletterSubscription = false;
        }
        if (termsAccepted == null) {
            termsAccepted = false;
        }
    }
}

package org.yiqixue.bootshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yiqixue.bootshop.entity.RealUser;

import java.util.Optional;

public interface RealUserRepository extends JpaRepository<RealUser, Integer> {


    /**
     * 根据用户名查找用户
     */
    Optional<RealUser> findByUsername(String username);

    /**
     * 根据邮箱查找用户
     */
    Optional<RealUser> findByEmail(String email);

    /**
     * 根据手机号码查找用户
     */
    Optional<RealUser> findByPhone(String phone);

    /**
     * 检查用户名是否存在
     */
    boolean existsByUsername(String username);

    /**
     * 检查邮箱是否存在
     */
    boolean existsByEmail(String email);

    /**
     * 检查手机号码是否存在
     */
    boolean existsByPhone(String phone);

}

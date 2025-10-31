package org.yiqixue.bootshop.service;

import org.yiqixue.bootshop.entity.RealUser;
import org.yiqixue.bootshop.repository.RealUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yiqixue.bootshop.entity.RealUser;
import org.yiqixue.bootshop.repository.RealUserRepository;

import java.util.List;
import java.util.Optional;

/**
 * 用户服务类
 * 处理用户相关的业务逻辑
 */
@Service
@Transactional
public class RealUserService {

    @Autowired
    private RealUserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder; // 用于密码加密

    /**
     * 注册新用户
     */
    public RealUser registerUser(RealUser user) {
        // 检查用户名是否已存在
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }

        // 检查邮箱是否已存在
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("邮箱已被注册");
        }

        // 检查手机号码是否已存在
        if (userRepository.existsByPhone(user.getPhone())) {
            throw new RuntimeException("手机号码已被注册");
        }

//        // 加密密码
//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 保存用户
        return userRepository.save(user);
    }

    /**
     * 根据用户名查找用户
     */
    public Optional<RealUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 根据ID查找用户
     */
    public Optional<RealUser> findById(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * 获取所有用户
     */
    public List<RealUser> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 更新用户信息
     */
    public RealUser updateUser(RealUser user) {
        return userRepository.save(user);
    }

    /**
     * 删除用户
     */
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * 检查用户名是否可用
     */
    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    /**
     * 检查邮箱是否可用
     */
    public boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    /**
     * 检查手机号码是否可用
     */
    public boolean isPhoneAvailable(String phone) {
        return !userRepository.existsByPhone(phone);
    }
}
package org.yiqixue.bootshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yiqixue.bootshop.entity.MybatisUser;
import org.yiqixue.bootshop.mapper.MybatisUserMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * MyBatis用户服务类
 * 处理用户相关的业务逻辑
 */
@Service
@Transactional
public class MybatisUserService {

    @Autowired
    private MybatisUserMapper userMapper;

    /**
     * 注册新用户
     */
    public MybatisUser registerUser(MybatisUser user) {
        // 检查用户名是否已存在
        if (isUsernameExists(user.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }

        // 检查邮箱是否已存在
        if (isEmailExists(user.getEmail())) {
            throw new RuntimeException("邮箱已被注册");
        }

        // 检查手机号码是否已存在
        if (isPhoneExists(user.getPhone())) {
            throw new RuntimeException("手机号码已被注册");
        }

        // 设置注册日期
        if (user.getRegisterDate() == null) {
            user.setRegisterDate(LocalDate.now());
        }

        // 设置默认值
        if (user.getNewsletterSubscription() == null) {
            user.setNewsletterSubscription(false);
        }

        // 保存用户
        int result = userMapper.insertUser(user);
        if (result > 0) {
            return user;
        } else {
            throw new RuntimeException("用户注册失败");
        }
    }

    /**
     * 根据用户名查找用户
     */
    public Optional<MybatisUser> findByUsername(String username) {
        MybatisUser user = userMapper.findByUsername(username);
        return Optional.ofNullable(user);
    }

    /**
     * 根据ID查找用户
     */
    public Optional<MybatisUser> findById(Long id) {
        MybatisUser user = userMapper.findById(id);
        return Optional.ofNullable(user);
    }

    /**
     * 获取所有用户
     */
    public List<MybatisUser> findAllUsers() {
        return userMapper.findAll();
    }

    /**
     * 更新用户信息
     */
    public MybatisUser updateUser(MybatisUser user) {
        int result = userMapper.updateUser(user);
        if (result > 0) {
            return user;
        } else {
            throw new RuntimeException("用户更新失败");
        }
    }

    /**
     * 删除用户
     */
    public void deleteUser(Long id) {
        int result = userMapper.deleteById(id);
        if (result == 0) {
            throw new RuntimeException("用户删除失败，用户不存在");
        }
    }

    /**
     * 检查用户名是否可用
     */
    public boolean isUsernameAvailable(String username) {
        return !isUsernameExists(username);
    }

    /**
     * 检查邮箱是否可用
     */
    public boolean isEmailAvailable(String email) {
        return !isEmailExists(email);
    }

    /**
     * 检查手机号码是否可用
     */
    public boolean isPhoneAvailable(String phone) {
        return !isPhoneExists(phone);
    }

    // 私有辅助方法
    private boolean isUsernameExists(String username) {
        return userMapper.countByUsername(username) > 0;
    }

    private boolean isEmailExists(String email) {
        return userMapper.countByEmail(email) > 0;
    }

    private boolean isPhoneExists(String phone) {
        return userMapper.countByPhone(phone) > 0;
    }
}
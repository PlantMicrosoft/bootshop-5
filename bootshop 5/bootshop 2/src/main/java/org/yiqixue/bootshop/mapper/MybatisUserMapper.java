package org.yiqixue.bootshop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.yiqixue.bootshop.entity.MybatisUser;

import java.util.List;
import java.util.Optional;

/**
 * MyBatis用户数据访问层
 */
@Mapper
public interface MybatisUserMapper {

    /**
     * 插入新用户
     */
    int insertUser(MybatisUser user);

    /**
     * 根据ID查找用户
     */
    MybatisUser findById(@Param("id") Long id);

    /**
     * 根据用户名查找用户
     */
    MybatisUser findByUsername(@Param("username") String username);

    /**
     * 根据邮箱查找用户
     */
    MybatisUser findByEmail(@Param("email") String email);

    /**
     * 根据手机号查找用户
     */
    MybatisUser findByPhone(@Param("phone") String phone);

    /**
     * 查找所有用户
     */
    List<MybatisUser> findAll();

    /**
     * 更新用户信息
     */
    int updateUser(MybatisUser user);

    /**
     * 删除用户
     */
    int deleteById(@Param("id") Long id);

    /**
     * 检查用户名是否存在
     */
    int countByUsername(@Param("username") String username);

    /**
     * 检查邮箱是否存在
     */
    int countByEmail(@Param("email") String email);

    /**
     * 检查手机号是否存在
     */
    int countByPhone(@Param("phone") String phone);
}
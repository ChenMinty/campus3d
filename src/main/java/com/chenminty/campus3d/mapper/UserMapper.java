package com.chenminty.campus3d.mapper;

import com.chenminty.campus3d.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;

@Mapper
public interface UserMapper {
    User findByUserId(Long id);

    @Select("SELECT COUNT(*) FROM user WHERE user_id = #{userId}")
    boolean existsByUserId(Long id);

    @Select("SELECT COUNT(*) FROM user WHERE email = #{email}")
    boolean existsByEmail(String email);

    @Insert("INSERT INTO user(user_id, username, password_hash, email, role) " +
            "VALUES(#{userId}, #{username}, #{passwordHash}, #{email}, #{role})")
    int insertUser(User user);

    @Update("UPDATE user SET last_login_at = #{lastLoginAt} WHERE user_id = #{userId}")
    void updateLastLogin(@Param("userId") Long userId, @Param("lastLoginAt") Date lastLoginAt);
}

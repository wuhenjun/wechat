package com.cloud.user.mapper;

import com.cloud.user.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMaper {
    /**
     * 注册新用户
     * @param user
     * @return
     */
    public int insertUser(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user);
}

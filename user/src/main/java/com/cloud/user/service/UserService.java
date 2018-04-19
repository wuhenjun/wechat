package com.cloud.user.service;

import com.cloud.user.model.User;

public interface UserService {
    /**
     * 注册
     * @param user
     * @return
     */
    public int inserUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);
}

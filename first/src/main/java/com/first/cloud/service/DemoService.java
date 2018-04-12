package com.first.cloud.service;

import com.first.cloud.model.User;

public interface DemoService {
    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    public User findUserById(Integer id);
}

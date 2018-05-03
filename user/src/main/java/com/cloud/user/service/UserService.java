package com.cloud.user.service;

import com.cloud.user.model.User;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     *更新用户常用信息
     * @param user
     * @return
     */
    public User updateUserInfo(User user);

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    public User findUserById(Integer userId);
}

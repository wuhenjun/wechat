package com.first.cloud.mapper;

import com.first.cloud.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoMapper {
    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return
     */
    public User findUserById(Integer userId);
}

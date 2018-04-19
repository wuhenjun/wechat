package com.cloud.user.service.ServiceImpl;

import com.cloud.user.mapper.UserMaper;
import com.cloud.user.model.User;
import com.cloud.user.service.UserService;
import com.cloud.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMaper userMaper;

    @Override
    public int inserUser(User user) {
        return Optional.ofNullable(user).map(x ->{
            //校验用户信息
            checkUserInfo(user);
            return userMaper.insertUser(user);
        }).orElseThrow(()->new IllegalArgumentException("参数为空"));
    }

    @Override
    public User login(User user) {
        return Optional.ofNullable(user).map(x->{
            if(StringUtils.isBlank(user.getUserAccount()) || StringUtils.isBlank(user.getPassword())){
                throw new IllegalArgumentException("请填写用户名与密码");
            }
            return userMaper.login(user);
        }).orElseThrow(()->new IllegalArgumentException("请填写用户名与密码"));
    }

    private void checkUserInfo(User user) {
        //校验用户名是否重复,且符合邮箱规则
        //校验密码是否符合正则
        //校验邮箱是否正在使用，发送邮件校验码
    }
}

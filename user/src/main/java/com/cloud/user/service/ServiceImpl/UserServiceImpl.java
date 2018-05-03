package com.cloud.user.service.ServiceImpl;

import com.cloud.user.mapper.UserMaper;
import com.cloud.user.model.User;
import com.cloud.user.service.UserService;
import com.cloud.user.utils.RedisUtil;
import com.cloud.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMaper userMaper;
    @Autowired
    private RedisUtil redisUtil;

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
            User loginUser =  userMaper.login(user);
            //校验是否一致
            Objects.requireNonNull(loginUser,"用户名或密码不正确");
            return loginUser;
        }).orElseThrow(()->new IllegalArgumentException("请填写用户名与密码"));
    }

    @Override
    @Transactional
    public User updateUserInfo(User user) {
        return Optional.ofNullable(user).map(x->{
            Objects.requireNonNull(x.getUserId(),"用户登录信息有误，请重新登录！");
            userMaper.updateUserInfo(x);
            User newUser = userMaper.findUserById(x.getUserId());
            //更新缓存中的用户信息
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Cookie[] cookies = request.getCookies();
            if(Objects.isNull(cookies) || cookies.length == 0){
                throw new IllegalArgumentException("cookie错误，请重试!");
            }
            for(Cookie cookie : cookies){
                String cookieName = cookie.getName();
                if("token".equals(cookieName)){
                    String cookieValue = cookie.getValue();
                    redisUtil.set(cookieValue,newUser,1000*60*30L);
                }
            }
            return newUser;
        }).orElseThrow(()->new IllegalArgumentException("参数为空"));
    }

    @Override
    public User findUserById(Integer userId) {
        return Optional.ofNullable(userId).map(x->{
            return userMaper.findUserById(x);
        }).orElseThrow(()->new IllegalArgumentException("用户ID不能为空"));
    }

    private void checkUserInfo(User user) {
        //校验用户名是否重复,且符合邮箱规则
        //校验密码是否符合正则
        //校验邮箱是否正在使用，发送邮件校验码
    }
}

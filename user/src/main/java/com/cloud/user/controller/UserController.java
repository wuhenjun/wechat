package com.cloud.user.controller;



import com.cloud.aspect.LoginCheck;
import com.cloud.user.model.User;
import com.cloud.user.service.UserService;
import com.cloud.user.utils.RedisUtil;
import com.cloud.util.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    @ApiOperation(value = "insertUser")
    public ResultInfo findUserById(@RequestBody @ApiParam(value="user") User user){
        return ResultBuilder.build(userService.inserUser(user));
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "login")
    public ResultInfo<User> login(@RequestBody @ApiParam(value="user") User user, HttpServletResponse response) throws UnsupportedEncodingException {
        User loginUser = userService.login(user);
        //cookie增加token
        String token = CryptoUtils.makeToken(loginUser.getUserId()+loginUser.getUserAccount());
        Cookie cookie = new Cookie("token",token );
        response.addCookie(cookie);
        //redis中存储User对应信息
        redisUtil.set(token,loginUser,1000*60*30L);
        return ResultBuilder.build(loginUser);
    }

    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ApiOperation(value = "updateUserInfo")
    @LoginCheck
    public ResultInfo<User> updateUserInfo(@ApiParam(value="nickName") String nickName, @ApiParam(value="sign") String sign,
                                           @ApiParam(value="icon") MultipartFile icon){
         com.cloud.model.User user = UserUtil.getUser();
         user.setNickName(nickName);
         user.setSign(sign);
         user.setIcon(FileUploadUtils.fileUpload(icon, UserUtil.getUser().getUserId()));
        return ResultBuilder.build(user);
    }
}

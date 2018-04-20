package com.cloud.user.controller;

import com.cloud.user.model.User;
import com.cloud.user.service.UserService;
import com.cloud.user.utils.ResultBuilder;
import com.cloud.user.utils.ResultInfo;
import com.cloud.util.CryptoUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

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
        Cookie cookie = new Cookie("token", CryptoUtils.makeToken(loginUser.getUserId()+loginUser.getUserAccount()));
        response.addCookie(cookie);
        //redis中存储User对应信息
        return ResultBuilder.build(loginUser);
    }

    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ApiOperation(value = "updateUserInfo")
    public ResultInfo<User> updateUserInfo(@ApiParam(value="nickName") String nickName,@ApiParam(value="sign") String sign,
                                           @ApiParam(value="icon") MultipartFile icon){
      return null;
    }
}

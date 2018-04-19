package com.cloud.user.controller;

import com.cloud.user.model.User;
import com.cloud.user.service.UserService;
import com.cloud.user.utils.ResultBuilder;
import com.cloud.user.utils.ResultInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserControll {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    @ApiOperation(value = "insertUser")
    public ResultInfo findUserById(@RequestBody @ApiParam(value="user") User user){
        return ResultBuilder.build(userService.inserUser(user));
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "login")
    public ResultInfo<User> login(@RequestBody @ApiParam(value="user") User user, HttpServletResponse response){
        User loginUser = userService.login(user);
        Cookie cookie = new Cookie("token","");
        return ResultBuilder.build(loginUser);
    }
}

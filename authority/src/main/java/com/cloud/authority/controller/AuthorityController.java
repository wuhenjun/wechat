package com.cloud.authority.controller;


import com.cloud.authority.utils.RedisUtil;
import com.cloud.model.User;
import com.cloud.util.CryptoUtils;
import com.cloud.util.ResultBuilder;
import com.cloud.util.ResultInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@RestController
@RequestMapping("/api/authority")
public class AuthorityController {
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/loginCheck",method = RequestMethod.POST)
    @ApiOperation(value = "loginCheck")
    public ResultInfo<User> loginCheck(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies) || cookies.length == 0){
            throw new IllegalArgumentException("cookie错误，请重试!");
        }
        for(Cookie cookie : cookies){
            String cookieName = cookie.getName();
            if("token".equals(cookieName)){
                //获取token的值，去redis中进行校验
                String cookieValue = cookie.getValue();
                User user = (User) redisUtil.get(cookieValue);
                //判断是否为null，如果为空，则验证失败，重新登录，验证成功则刷新token存在时间
                if(Objects.isNull(user)){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    return ResultBuilder.build(null,-1,"error");
                }else{
                    //验证成功
                    redisUtil.set(cookieValue,user,1000*60*30L);
                    return ResultBuilder.build(user,1,"success");
                }
            }
        }
        return ResultBuilder.build(null,-1,"error");
    }


}

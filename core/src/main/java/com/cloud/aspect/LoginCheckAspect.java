package com.cloud.aspect;

import com.cloud.feign.LoginCheckClient;
import com.cloud.model.User;
import com.cloud.util.ResultInfo;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Aspect
@Component
public class LoginCheckAspect {

    @Autowired
    private LoginCheckClient loginCheckClient;

    @Pointcut(value = "execution(* com.cloud.aspect.*.*(..))")
    private void loginPointCut() {

    }
    @Before("loginPointCut()")
    public void before(){
       HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        /*//获取cookie
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies) || cookies.length == 0){
            throw new IllegalArgumentException("cookie错误，请重试!");
        }
        for(Cookie cookie : cookies){
            String cookieName = cookie.getName();
            if("token".equals(cookieName)){
                //获取token的值，去redis中进行校验
                String cookieValue = cookie.getValue();

            }
        }
        ServletWebRequest servletWebRequest=new ServletWebRequest(request);
        HttpServletResponse response=servletWebRequest.getResponse();*/
       //到权限模块校验
        ResultInfo<User> resultInfo = loginCheckClient.loginCheck();
        if(Objects.isNull(resultInfo) || resultInfo.getResultCode() < 0){
            //身份校验异常，请重新登录
            throw new IllegalArgumentException("身份校验异常，请重新登录");
        }
        //校验成功，获取user对象，存入当前session中
        request.getSession().setAttribute("user",resultInfo.getResult());
    }
}

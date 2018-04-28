package com.cloud.aspect;

import com.cloud.util.StringUtils;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCheck {
    //检查是否有登录
    boolean checkIsLogin() default true;

}

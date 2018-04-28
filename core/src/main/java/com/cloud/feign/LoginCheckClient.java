package com.cloud.feign;

import com.cloud.model.User;
import com.cloud.util.ResultInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("authority")
public interface LoginCheckClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/authority/loginCheck")
    public ResultInfo<User> loginCheck();
}

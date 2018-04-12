package com.first.cloud.controller;

import com.first.cloud.model.User;
import com.first.cloud.service.DemoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    @ApiOperation(value = "findUserById")
    public User findUserById(@ApiParam(value="userId")@PathVariable Integer userId){
        return demoService.findUserById(userId);
    }
}

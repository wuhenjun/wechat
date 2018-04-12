package com.first.cloud.service.serviceImpl;

import com.first.cloud.mapper.DemoMapper;
import com.first.cloud.model.User;
import com.first.cloud.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public User findUserById(Integer id) {
        return Optional.ofNullable(id).map(x->{
            return demoMapper.findUserById(id);
        }).orElseThrow(()->new IllegalArgumentException("id为空"));
    }
}

package com.nick.demo.service;

import com.nick.demo.model.User;
import com.nick.demo.service2.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    @Autowired
    IRoleService roleService;

    public String createUser(){

        Random random=new Random(System.currentTimeMillis());
        int r=random.nextInt(3);
        int size=2+r;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<size;i++){
            User user=User.of("nick"+i,i);
            sb.append(user.toString()).append(",");
        }
        roleService.checkAccess();
        return sb.toString();

    }

}

package com.nick.demo.api;

import com.nick.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestApi {
    @Autowired
    UserService userService;
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name){

      return   userService.createUser();
    }
}

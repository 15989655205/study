package com.example.study.controller;

import com.example.study.mapper.UserMapper;
import com.example.study.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
//@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String test22(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (name.equals("token")) {
                String value = cookie.getValue();
                User user = userMapper.findByToken(value);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }

        }

        return "index";
    }



}

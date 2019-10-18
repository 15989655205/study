package com.example.study.controller;

import com.example.study.dto.PaginationDTO;
import com.example.study.dto.QuestionDTO;
import com.example.study.mapper.QuestionMapper;
import com.example.study.mapper.UserMapper;
import com.example.study.model.Question;
import com.example.study.model.User;
import com.example.study.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
//@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    
    @GetMapping("/")
    public String test22(HttpServletRequest request,
                         Model model,
                         @RequestParam(name = "page",defaultValue = "1") Integer  page,
                         @RequestParam(name = "size",defaultValue = "5") Integer  size) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
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
        }

        PaginationDTO paginationDTO = questionService.list(page,size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "index";
    }



}

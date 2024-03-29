package com.example.study.controller;

import com.example.study.dto.PaginationDTO;
import com.example.study.mapper.UserMapper;
import com.example.study.model.User;
import com.example.study.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page",defaultValue = "1") Integer  page,
                          @RequestParam(name = "size",defaultValue = "5") Integer  size) {
        User user = (User) request.getSession().getAttribute("user");

        if (user==null){
            return "redirect:/";
        }

        if ("question".equals(action)){
            model.addAttribute("section","question");
            model.addAttribute("action","question");
            model.addAttribute("sectionName","我的提问");
        }else if("repiles".equals(action)){
            model.addAttribute("section","repiles");
            model.addAttribute("sectionName","最新回复");
            model.addAttribute("action","repiles");
        }

        PaginationDTO paginationDTO = questionService.listByUserId(user.getId(), page, size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "profile";
    }
}

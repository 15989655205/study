package com.example.study.controller;

import com.example.study.mapper.QuestionMapper;
import com.example.study.mapper.UserMapper;
import com.example.study.model.Question;
import com.example.study.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;


    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String tag,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        User user=null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (name.equals("token")) {
                    String value = cookie.getValue();
                    user = userMapper.findByToken(value);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if (user == null) {
            model.addAttribute("error", "用户未登陆。");
            return "publish";
        }

        if (title == "") {
            model.addAttribute("error", "标题不能为空。");
            return "publish";
        }
        if (description == "") {
            model.addAttribute("error", "内容不能为空。");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());

        questionMapper.create(question);
        return "redirect:/";
    }
}

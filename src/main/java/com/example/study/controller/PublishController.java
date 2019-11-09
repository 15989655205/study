package com.example.study.controller;

import com.example.study.dto.QuestionDTO;
import com.example.study.mapper.QuestionMapper;
import com.example.study.mapper.UserMapper;
import com.example.study.model.Question;
import com.example.study.model.User;
import com.example.study.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model) {
        QuestionDTO byId = questionService.getById(id);
        model.addAttribute("title", byId.getTitle());
        model.addAttribute("description", byId.getDescription());
        model.addAttribute("tag", byId.getTag());
        model.addAttribute("id", byId.getID());
        return "publish";
    }


    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String tag,
            @RequestParam Long ID,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("ID", ID);
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        User user = (User) request.getSession().getAttribute("user");

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

        if (ID == null) {
            questionMapper.create(question);
        } else {
            question.setID(ID);

            questionMapper.update(question);
        }
        return "redirect:/";
    }

    public void createOrUpdate(Question question) {
        if (question.getID() == null) {
            questionMapper.create(question);
        }
    }
}

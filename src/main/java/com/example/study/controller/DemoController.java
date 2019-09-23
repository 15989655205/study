package com.example.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
//@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/hellow")
    public String test22(@RequestParam(name = "name",required = false) String name, Model model) {
        model.addAttribute("name",name);
        return "hellow";
    }

}

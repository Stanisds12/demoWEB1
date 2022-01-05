package com.example.demoWEB1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class generalController {
    
    @RequestMapping("/blog")
    @ResponseBody
    public ModelAndView getBlog() {
        ModelAndView model = new ModelAndView();
        model.setViewName("blog");
        return model;
    }
    
}

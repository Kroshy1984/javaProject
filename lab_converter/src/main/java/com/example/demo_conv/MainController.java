package com.example.demo_conv;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @RequestMapping("/")
    public ModelAndView index()
    {
        return new ModelAndView("redirect:" + "/index.html");
    }

}

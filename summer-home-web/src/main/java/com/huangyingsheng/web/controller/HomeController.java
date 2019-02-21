package com.huangyingsheng.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String summer(Model model) {
        model.addAttribute("name", "dear");
        return "summer";
    }

    @GetMapping("/get")
    @ResponseBody
    public String get() {
        return "123";
    }

}

package com.innowise.cargo_transportation.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/about")
public class AboutController {
    @RequestMapping(method = RequestMethod.GET)
    public String getAbout() {
        return "";
    }

}

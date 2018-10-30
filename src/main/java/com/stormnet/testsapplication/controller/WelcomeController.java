package com.stormnet.testsapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class WelcomeController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(ModelMap modelMap) {
//        byte[] array = Base64.getEncoder().encode((SALT + "admin").getBytes());
//        String hash = new String(array);
        modelMap.addAttribute("time", new Date());
        modelMap.addAttribute("message", "hello world");
//        List<UserModel> allUsers = service.getAllUsers();
//        modelMap.addAttribute("allUsers", allUsers);
        return "welcome";
    }
}

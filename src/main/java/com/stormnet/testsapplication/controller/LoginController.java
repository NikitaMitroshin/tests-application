package com.stormnet.testsapplication.controller;

import com.stormnet.testsapplication.model.User;
import com.stormnet.testsapplication.model.UserLogin;
import com.stormnet.testsapplication.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request,
                        ModelMap modelMap) {
        boolean isValid = true;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (StringUtils.isBlank(login)) {
            modelMap.put("loginError", "name is not valid");
            isValid = false;
        }

        if (StringUtils.isBlank(password)) {
            modelMap.put("passwordError", "password is not valid");
            isValid = false;
        }

        if (isValid) {
            UserLogin userLogin = new UserLogin(login, password);
            User user = userService.getUserFromUserLogin(userLogin);
        }
        String referer = request.getHeader("referer")
                .replaceAll("http://", "")
                .replace(request.getServerName() + ":", "")
                .replace(String.valueOf(request.getServerPort()), "");

        return request.getPathInfo();
    }

}

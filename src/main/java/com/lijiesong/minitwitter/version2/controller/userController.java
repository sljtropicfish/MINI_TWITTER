package com.lijiesong.minitwitter.version2.controller;

import com.lijiesong.minitwitter.version2.domain.User;
import com.lijiesong.minitwitter.version2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/users")
public class userController {
    private UserService userService;

    @Autowired
    public userController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String getAllRooms(Model model, HttpSession session){
        //System.out.println("######### session id from user Controller " + session.getAttribute("userId"));
        model.addAttribute("users", this.userService.getAllUsers());
        return "users";
    }
}

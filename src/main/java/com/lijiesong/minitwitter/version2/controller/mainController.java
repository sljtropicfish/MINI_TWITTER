package com.lijiesong.minitwitter.version2.controller;

import com.lijiesong.minitwitter.version2.service.TweetService;
import com.lijiesong.minitwitter.version2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class mainController {
    private TweetService tweetService;
    private UserService userService;

    @Autowired
    public mainController(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    /*public void setUserIdInSession(Principal principal, HttpSession session){
        if(session.getAttribute("userId") == null && principal != null){
            session.setAttribute("userId", this.userService.getUserIdByEmail(principal.getName()));
            System.out.println("************* Set user ID by Email in mainController: " + session.getAttribute("userId"));
        }
    }*/

    @GetMapping
    public String getHomePage(Model model, Principal principal, HttpSession session){
        //setUserIdInSession(principal, session);
        //System.out.println("######### session id from tweet Controller" + session.getAttribute("userId"));
        model.addAttribute("userInfo", "All Tweets");
        model.addAttribute("tweets", this.tweetService.getAllTweetsWithName());
        return "tweets";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, HttpSession session){
        int id = (int)session.getAttribute("userId");
        this.userService.getUser(id).ifPresent(o -> model.addAttribute("user", o));
        model.addAttribute("tweets", this.tweetService.getOneUserTweetDetails(id));
        return "profile";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        return "login";
    }

    @GetMapping("/logout-success")
    public String getLogoutPage(Model model){
        return "logout";
    }
}

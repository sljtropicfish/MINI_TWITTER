package com.lijiesong.minitwitter.version2.controller;

import com.lijiesong.minitwitter.version2.domain.User;
import com.lijiesong.minitwitter.version2.service.TweetService;
import com.lijiesong.minitwitter.version2.service.UserService;
import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/tweets")
public class tweetController {
    private TweetService tweetService;
    private UserService userService;

    @Autowired
    public tweetController(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllTweets(Model model, HttpSession session){
        System.out.println("######### session id from tweet Controller" + session.getAttribute("userId"));
        model.addAttribute("userInfo", "All Tweets");
        model.addAttribute("tweets", this.tweetService.getAllTweetsWithName());
        return "tweets";
    }

    @GetMapping("/{id}")
    //@ResponseBody
    public String getTweetById(Model model, @PathVariable int id) {
        StringBuilder sb = new StringBuilder();
        User user = this.userService.getUser(id).orElseThrow(() -> new NoSuchEntityException(String.valueOf(id)));
        sb.append(user.getId()).append(" ").append(user.getFirstName())
                .append(" ").append(user.getLastName()).append("'s Tweets");
        model.addAttribute("userInfo", sb.toString());
        model.addAttribute("tweets", this.tweetService.getAllTweetsByUserId(id));
        return "tweets";
    }
}

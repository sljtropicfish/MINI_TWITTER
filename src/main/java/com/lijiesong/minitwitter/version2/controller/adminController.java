package com.lijiesong.minitwitter.version2.controller;

import com.lijiesong.minitwitter.version2.domain.User;
import com.lijiesong.minitwitter.version2.service.FriendshipService;
import com.lijiesong.minitwitter.version2.service.TweetService;
import com.lijiesong.minitwitter.version2.service.UserService;
import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class adminController {
    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private TweetService tweetService;

    public adminController(UserService userService, FriendshipService friendshipService, TweetService tweetService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
        this.tweetService = tweetService;
    }

    @GetMapping
    public String getAdmin(Model model, HttpSession session, Principal principal){
        System.out.println("######### session id from admin Controller " + session.getAttribute("userId"));
        return "adminHome";
    }

    @GetMapping("/users")
    public String getAllRooms(Model model, Principal principal){
        model.addAttribute("users", this.userService.getAllUsers());
        return "adminUsers";
    }

    @GetMapping("/friendships")
    public String getFollowees(Model model) {
        model.addAttribute("friendships", this.friendshipService.getAllFriendship());
        return "adminFriendships";
    }

    @GetMapping("/tweets")
    public String getAllTweets(Model model){
        model.addAttribute("userInfo", "All Tweets");
        model.addAttribute("tweets", this.tweetService.getAllTweetsWithName());
        return "adminTweets";
    }
}

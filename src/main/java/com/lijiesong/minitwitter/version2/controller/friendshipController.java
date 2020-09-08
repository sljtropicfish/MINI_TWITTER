package com.lijiesong.minitwitter.version2.controller;

import com.lijiesong.minitwitter.version2.domain.User;
import com.lijiesong.minitwitter.version2.service.FriendshipService;
import com.lijiesong.minitwitter.version2.service.UserService;
import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.function.Consumer;

@Controller
@RequestMapping()
public class friendshipController {
    private FriendshipService friendshipService;
    private UserService userService;

    @Autowired
    public friendshipController(FriendshipService friendshipService, UserService userService) {
        this.friendshipService = friendshipService;
        this.userService = userService;
    }

    /*@GetMapping
    //@ResponseBody
    public String getFollowees(Model model, @RequestParam int id) {
        StringBuilder sb = new StringBuilder();
        User follower = this.userService.getUser(id).orElseThrow(() -> new NoSuchEntityException(String.valueOf(id)));
        sb.append(follower.getId()).append(" ").append(follower.getFirstName()).append(" ").append(follower.getLastName());
        model.addAttribute("followerInfo", sb.toString());
        model.addAttribute("users", this.friendshipService.getUserFollowees(id));
        return "friendships";
    }*/

    /*@GetMapping("/followers")
    //@ResponseBody
    public String getUserFollowers(Model model, HttpSession session) {
        StringBuilder sb = new StringBuilder();
        System.out.println("******** session id: " + session.getAttribute("userId"));
        int id = (int) session.getAttribute("userId");
        System.out.println("******** id: " + id);
        User follower = this.userService.getUser(id).orElseThrow(() -> new NoSuchEntityException(String.valueOf(id)));
        sb.append(follower.getId()).append(" ").append(follower.getFirstName()).append(" ").append(follower.getLastName());
        model.addAttribute("followerInfo", sb.toString());
        model.addAttribute("users", this.friendshipService.getUserFollowees(id));
        return "friendships";
    }*/

    @GetMapping("followers")
    public String getUserFollowers(Model model, HttpSession session){
        model.addAttribute("followerInfo", "Your followers");
        int id = (int) session.getAttribute("userId");
        model.addAttribute("users", this.friendshipService.getFriendshipFollowees(id));
        return "friendships";
    }

    @GetMapping("/following")
    public String getUserFollowing(Model model, HttpSession session){
        model.addAttribute("followerInfo", "You are following");
        int id = (int) session.getAttribute("userId");
        model.addAttribute("users", this.friendshipService.getFriendshipFollowers(id));
        return "friendships";
    }

}

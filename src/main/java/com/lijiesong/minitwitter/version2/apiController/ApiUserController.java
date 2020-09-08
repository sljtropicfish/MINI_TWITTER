package com.lijiesong.minitwitter.version2.apiController;


import com.lijiesong.minitwitter.version2.domain.User;
import com.lijiesong.minitwitter.version2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiUserController {
    private UserService userService;

    @Autowired
    public ApiUserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    /*@GetMapping("/followees")
    public Set<User> getAllFollowees(){
        Set<User> set = this.userService.getFollowees(3);
        List<User> followees = new ArrayList<>();
        for(User user : set){
            followees.add(user);
        }
        return followees;
        return userService.getAllFollowees();
    }*/
    /*@GetMapping("/users/id3")
    public Optional<User> getUser(){
        return this.userService.getUser(3);
    }*/

    @GetMapping("/user/3")
    public Optional<User> getUserById(){
      return this.userService.getUser(3);
    }


    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User newUser){
        userService.createUser(newUser.getFirstName(), newUser.getLastName());
    }
}

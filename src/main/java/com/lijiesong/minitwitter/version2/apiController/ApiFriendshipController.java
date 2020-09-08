package com.lijiesong.minitwitter.version2.apiController;

import com.lijiesong.minitwitter.version2.domain.Friendship;
import com.lijiesong.minitwitter.version2.domain.User;
import com.lijiesong.minitwitter.version2.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiFriendshipController {
    @Autowired
    private FriendshipService friendshipService;

    public ApiFriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @GetMapping("/friendships")
    public List<Friendship> getAllFriendships(){
        return this.friendshipService.getAllFriendship();
    }

    /*@GetMapping("/friendships/3")
    public List<Friendship> getFriendshipFollowees(){
        return this.friendshipService.getFriendshipFollowees(3);
    }*/

    /*@GetMapping("/friendships/followees")
    @ResponseBody
    public List<User> getFollowees(@RequestParam int id){
        return this.friendshipService.getUserFollowees(id);
    }*/

    @PostMapping("/friendships")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody Friendship friendship){
        friendshipService.createFriendship(friendship.getId().getFollowerId(), friendship.getId().getFolloweeId()
        );
    }

    @GetMapping("/following")
    public List<Object> getUserFollowing(HttpSession session){
        int id = (int) session.getAttribute("userId");
        return this.friendshipService.getFriendshipFollowers(id);
    }

    @DeleteMapping("/friendship/delete/{followerId}/{followeeId}")
    public void deleteTweet(@PathVariable int followerId, @PathVariable int followeeId){
        this.friendshipService.deleteById(followerId, followeeId);
    }

}

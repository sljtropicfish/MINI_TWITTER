package com.lijiesong.minitwitter.version2.service;

import com.lijiesong.minitwitter.version2.domain.Friendship;
import com.lijiesong.minitwitter.version2.domain.User;
import com.lijiesong.minitwitter.version2.repository.FriendshipRepository;
import com.lijiesong.minitwitter.version2.repository.UserRepository;
import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private UserRepository userRepository;

    public FriendshipService(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    //check if the input id is exist
    public boolean isUserExist(int id){
        if(this.userRepository.existsById(id)){
            return true;
        }
        return false;
    }

    public Friendship createFriendship(int followerId, int followeeId){
        if(!isUserExist((followeeId)) || !isUserExist(followerId)){
            return null;
        }
        return friendshipRepository.save(new Friendship(new Friendship.FriendshipKey(followerId,followeeId)));
    }

    /*public List<User> getFollowees(int followerId){
        List<User> followees = new ArrayList<>();
        return friendshipRepository.findFollowees(followerId);

    }*/

    public List<Object> getFriendshipFollowees(int followerId){
        List<Object> friendships = new ArrayList<>();
        this.friendshipRepository.findFriendshipByFollowerId(followerId).forEach(friendships::add);
        return friendships;
    }

    public List<Object> getFriendshipFollowers(int followeeId){
        List<Object> friendships = new ArrayList<>();
        this.friendshipRepository.findFriendshipByFolloweeId(followeeId).forEach(friendships::add);
        return friendships;
    }

    /*public List<Optional<User>> getUserFollowees(int followerId){
        List<Friendship> friendshipFollowees = getFriendshipFollowees(followerId);
        List<Optional<User>> followees = new ArrayList<>();
        for(Friendship fs : friendshipFollowees){
            Optional<User> user = this.userRepository.findById(fs.getId().getFolloweeId());
            followees.add(user);

        }
        return followees;
    }*/

    /*public List<User> getUserFollowees(int followerId){
        List<Friendship> friendshipFollowees = getFriendshipFollowees(followerId);
        List<User> followees = new ArrayList<>();
        for(Friendship fs : friendshipFollowees){
            Optional<User> optionalUser = this.userRepository.findById(fs.getId().getFolloweeId());
            User followee = this.userRepository.findById(fs.getId().getFolloweeId()).orElseThrow(() -> new NoSuchEntityException(String.valueOf(followerId)));
            followees.add(followee);
        }
        return followees;
    }*/

    public List<Friendship> getAllFriendship(){
        List<Friendship> friendships = new ArrayList<>();
        this.friendshipRepository.findAll().forEach(friendships::add);
        return friendships;
    }

    public void deleteById(int followerId, int followeeId){
        this.friendshipRepository.deleteById(followerId, followeeId);
    }
}

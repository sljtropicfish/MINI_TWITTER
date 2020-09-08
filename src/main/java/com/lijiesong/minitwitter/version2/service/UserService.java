package com.lijiesong.minitwitter.version2.service;



import com.lijiesong.minitwitter.version2.domain.User;
import com.lijiesong.minitwitter.version2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String firstName, String lastName){
        return userRepository.save(new User(firstName, lastName));
    }

    //check if the input id is exist
    public boolean isUserExist(int id){
        if(this.userRepository.existsById(id)){
            return true;
        }
        return false;
    }

    public int getUserIdByEmail(String email){
        return this.userRepository.findByEmail(email).getId();
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        this.userRepository.findAll().forEach(users::add);
        return users;
    }

    public Optional<User> getUser(int id){
        return userRepository.findById(id);
    }

    /*public Set<User> getAllFollowees(){
        Optional<User> user = getUser(3);
        Set<User> followees = user.getFollowees();
        for(User u : followees){
            System.out.println("*****Followees*****: " + u);
        }
        return followees;
        User user = new User("follower", "test");
        User user2 = new User("followee", "test");

        user.getFollowees().add(user2);
        userRepository.save(user);
        return user.getFollowees();
    }*/

    public long total(){return userRepository.count();}
}

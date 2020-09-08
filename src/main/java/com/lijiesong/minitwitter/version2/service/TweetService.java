package com.lijiesong.minitwitter.version2.service;


import com.lijiesong.minitwitter.version2.domain.Tweet;
import com.lijiesong.minitwitter.version2.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private UserService userService;

    public TweetService(TweetRepository tweetRepository, UserService userService) {
        this.tweetRepository = tweetRepository;
        this.userService = userService;
    }

    public Tweet createTweet(int userId, String content){
        if(this.userService.isUserExist(userId)){
            return tweetRepository.save(new Tweet(userId, content));
        }
        return null;
    }

    public List<Tweet> getAllTweets(){
        List<Tweet> tweets = new ArrayList<>();
        this.tweetRepository.findAll().forEach(tweets::add);
        return tweets;
    }

    public Optional<Tweet> gettweetById(int id){
        Optional<Tweet> tweet = tweetRepository.findById(id);
        if(tweet != null) return tweet;
        return null;
    }

    public List<Tweet> getAllTweetsByUserId(int userId){
        return this.tweetRepository.findTweetsByUserId(userId);
    }

    public void deleteById(int id, int userId) {
        this.tweetRepository.deleteById(id, userId);
    }

    //return all tweetdetails with (user name + tweets)
    public List<Object> getAllTweetsWithName(){
        List<Object> tweets = new ArrayList<>();
        this.tweetRepository.findAllTweetDetails().forEach(tweets::add);

        return tweets;

    }

    //return one user's tweetdetail (user name + tweet)
    public List<Object> getOneUserTweetDetails(int userId){
        List<Object> tweets = new ArrayList<>();
        this.tweetRepository.findOneUserTweetDetails(userId).forEach(tweets::add);
        return tweets;
    }

}

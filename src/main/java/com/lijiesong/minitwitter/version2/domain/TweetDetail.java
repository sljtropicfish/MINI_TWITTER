package com.lijiesong.minitwitter.version2.domain;

public class TweetDetail {
    public User user;
    public Tweet tweet;

    public TweetDetail(User user, Tweet tweet) {
        this.user = user;
        this.tweet = tweet;
    }
}

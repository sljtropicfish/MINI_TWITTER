package com.lijiesong.minitwitter.version2.domain;

public class FriendshipDetail {

    public int userId;
    public String userFirstName;
    public String userLastName;

    public FriendshipDetail(int userId, String userFirstName, String userLastName) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }
}

package com.lijiesong.minitwitter.version2.auth;

import javax.persistence.*;

@Entity
@Table(name="Auth_User_Group")
public class AuthGroup {
    @Id
    @Column(name="auth_user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="user_email")
    private String userEmail;
    @Column(name="auth_group")
    private String authGroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }
}

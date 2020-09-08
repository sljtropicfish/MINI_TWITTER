package com.lijiesong.minitwitter.version2.domain;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int id;
    @Column(name="first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name="email", nullable = false, unique = true)
    private String email;
    @Column(name="password")
    private String password;


    /*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="Friendship",
            joinColumns={@JoinColumn(name="follower_id")},
            inverseJoinColumns={@JoinColumn(name="followee_id")})
    private Set<User> followees = new HashSet<User>();

    @ManyToMany(mappedBy="followees")
    private Set<User> followers = new HashSet<User>();

    @ManyToMany
    @JoinTable(name="Friendship",
            joinColumns={@JoinColumn(name="followee_id")},
            inverseJoinColumns={@JoinColumn(name="follower_id")})
    private Set<User> followers = new HashSet<User>();*/

    protected User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName(){
        return getFirstName() + " " + getLastName();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public Set<User> getFollowees() {
        return followees;
    }

    public void setFollowees(Set<User> followees) {
        this.followees = followees;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }*/

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

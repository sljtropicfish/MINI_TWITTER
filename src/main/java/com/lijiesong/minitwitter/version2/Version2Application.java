package com.lijiesong.minitwitter.version2;

import com.lijiesong.minitwitter.version2.domain.Friendship;
import com.lijiesong.minitwitter.version2.repository.FriendshipRepository;
import com.lijiesong.minitwitter.version2.repository.UserRepository;
import com.lijiesong.minitwitter.version2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityListeners;

@SpringBootApplication
@EnableJpaAuditing
public class Version2Application implements CommandLineRunner{
    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        /*User user1 = new User("user5", "E");
        User user2 = new User("user6", "F");

        user1.getFollowees().add(user2);
        userRepository.save(user1);*/
        //Friendship fs = new Friendship(new Friendship.FriendshipKey(27, 28));
        //this.friendshipRepository.save(fs);

    }



    public static void main(String[] args) {

        SpringApplication.run(Version2Application.class, args);
    }
}

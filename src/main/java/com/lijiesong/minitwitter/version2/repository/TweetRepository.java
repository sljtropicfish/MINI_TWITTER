package com.lijiesong.minitwitter.version2.repository;


import com.lijiesong.minitwitter.version2.domain.Friendship;
import com.lijiesong.minitwitter.version2.domain.Tweet;
import com.lijiesong.minitwitter.version2.domain.TweetDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Integer> {

    /*@Query("select u.firstName, t from User u JOIN Tweet t ON u.id = t.userId)
    List<Friendship> findFriendshipByFollowerId(int id);*/

    @Query("SELECT NEW com.lijiesong.minitwitter.version2.domain.TweetDetail(u, t) FROM User u INNER JOIN Tweet t ON u.id = t.userId ORDER BY t.updateAt DESC ")
    List<Object> findAllTweetDetails();

    @Query("SELECT NEW com.lijiesong.minitwitter.version2.domain.TweetDetail(u, t) FROM User u INNER JOIN Tweet t ON u.id = t.userId WHERE u.id = ?1 ORDER BY t.updateAt DESC")
    List<Object> findOneUserTweetDetails(int userId);

    @Query("select t from Tweet t where t.userId = ?1")
    List<Tweet> findTweetsByUserId(int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Tweet t WHERE t.id = ?1 AND t.userId = ?2")
    void deleteById(int id, int userId);
}

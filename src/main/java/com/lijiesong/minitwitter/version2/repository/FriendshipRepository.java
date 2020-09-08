package com.lijiesong.minitwitter.version2.repository;

import com.lijiesong.minitwitter.version2.domain.Friendship;
import com.lijiesong.minitwitter.version2.domain.User;
import com.lijiesong.minitwitter.version2.service.UserService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FriendshipRepository extends CrudRepository<Friendship, Friendship.FriendshipKey> {
    /*@Query("Select u.id, u.firstName, u.lastName from User u INNER JOIN Friendship f ON u.id = f.followee_id WHERE f.follower_id = ?1")
    List<User> findFollowees(int id);*/

    @Query("SELECT NEW com.lijiesong.minitwitter.version2.domain.FriendshipDetail(u.id, u.firstName, u.lastName) FROM User u INNER JOIN Friendship f ON u.id = f.id.followeeId WHERE f.id.followerId = ?1")
    List<Object> findFriendshipByFollowerId(int id);

    @Query("SELECT NEW com.lijiesong.minitwitter.version2.domain.FriendshipDetail(u.id, u.firstName, u.lastName) FROM User u INNER JOIN Friendship f ON u.id = f.id.followerId WHERE f.id.followeeId = ?1")
    List<Object> findFriendshipByFolloweeId(int id);


    @Transactional
    @Modifying
    @Query("DELETE FROM Friendship f WHERE f.id.followerId = ?1 AND f.id.followeeId = ?2")
    void deleteById(int followerId, int followeeId);

}

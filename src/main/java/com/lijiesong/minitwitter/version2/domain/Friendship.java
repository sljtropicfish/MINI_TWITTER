package com.lijiesong.minitwitter.version2.domain;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Friendship")
public class Friendship {

    @Embeddable
    public static
    class FriendshipKey implements Serializable {
        @Column(name = "follower_id")
        private int followerId;

        @Column(name = "followee_id")
        private int followeeId;

        public FriendshipKey() {
        }

        public FriendshipKey(int followerId, int followeeId) {
            this.followerId = followerId;
            this.followeeId = followeeId;
        }


        public int getFollowerId() {
            return followerId;
        }

        public void setFollowerId(int followerId) {
            this.followerId = followerId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FriendshipKey)) return false;
            FriendshipKey that = (FriendshipKey) o;
            return getFollowerId() == that.getFollowerId() &&
                    getFolloweeId() == that.getFolloweeId();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getFollowerId(), getFolloweeId());
        }

        public int getFolloweeId() {
            return followeeId;
        }

        public void setFolloweeId(int followeeId) {
            this.followeeId = followeeId;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("FriendshipKey{");
            sb.append("followerId=").append(followerId);
            sb.append(", followeeId=").append(followeeId);
            sb.append('}');
            return sb.toString();
        }
    }

    @EmbeddedId
    FriendshipKey id;

    public Friendship() {
    }

    public Friendship(FriendshipKey id) {
        this.id = id;
    }

    public FriendshipKey getId() {
        return id;
    }

    public void setId(FriendshipKey id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Friendship{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

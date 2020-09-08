package com.lijiesong.minitwitter.version2.repository;

import com.lijiesong.minitwitter.version2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByFirstName(String firstName);
    User findByEmail(String email);
}

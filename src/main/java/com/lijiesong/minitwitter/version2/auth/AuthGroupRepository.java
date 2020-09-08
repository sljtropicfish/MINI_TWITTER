package com.lijiesong.minitwitter.version2.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthGroupRepository extends JpaRepository<AuthGroup, Integer> {

    List<AuthGroup> findByUserEmail(String userEmail);
}

package com.example.livechat.repository;

import com.example.livechat.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "SELECT * FROM USER U WHERE U.USERNAME = username", nativeQuery = true)
    UserEntity findByUsername(@Param("username") String username);
}
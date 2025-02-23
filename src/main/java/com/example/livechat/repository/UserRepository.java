package com.example.livechat.repository;

import com.example.livechat.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "SELECT * FROM \"user\" U WHERE U.username = ?1", nativeQuery = true)
    UserEntity findByUsername(String username);
}
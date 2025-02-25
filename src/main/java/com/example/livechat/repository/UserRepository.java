package com.example.livechat.repository;

import com.example.livechat.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "SELECT * FROM \"user\" U WHERE U.username = ?1", nativeQuery = true)
    UserEntity findByUsername(String username);

    List<UserEntity> findByUsernameContainingIgnoreCase(String keyword);
}
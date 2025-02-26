package com.example.livechat.repository;

import com.example.livechat.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    @Query(value = "SELECT * FROM \"message\" M WHERE (M.from_user = ?1 AND M.to_user = ?2) OR (M.from_user = ?2 AND M.to_user = ?1)", nativeQuery = true)
    List<MessageEntity> findAllByUserIDAndFriendID(Integer userID, Integer friendID);
}
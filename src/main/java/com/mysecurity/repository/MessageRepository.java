package com.mysecurity.repository;

import com.mysecurity.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends org.springframework.data.jpa.repository.JpaRepository<Message, Integer>{
    Message save(Message message);

    Optional<Message> findById(int id);
}

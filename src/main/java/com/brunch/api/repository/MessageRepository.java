package com.brunch.api.repository;

import com.brunch.api.entity.Message;
import com.brunch.api.utils.MessageType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAll(Sort sort);
    Message findByMessageType(MessageType messageType);
}

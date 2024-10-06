package com.folautech.graphql.entities.message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {

//    List<Message> findByChatId(Long chatId);

    Page<Message> findByChatId(Long chatId, Pageable pageable);
}

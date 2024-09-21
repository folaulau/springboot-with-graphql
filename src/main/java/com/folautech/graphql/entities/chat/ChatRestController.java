package com.folautech.graphql.entities.chat;

import com.folautech.graphql.dto.ChatDTO;
import com.folautech.graphql.entities.message.Message;
import com.folautech.graphql.entities.message.MessageCreateDTO;
import com.folautech.graphql.entities.message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequestMapping("/chats")
@RestController
public class ChatRestController {

    @Autowired
    ChatService chatService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/{id}")
    public ResponseEntity<ChatDTO> getById(@PathVariable Long id) {
        log.info("getById id={}", id);
        ChatDTO chat = chatService.getById(id);

        return new ResponseEntity<>(chat, OK);
    }

    @PostMapping(value = "/messages")
    public ResponseEntity<Message> addMessage(
            @RequestBody MessageCreateDTO msg) {
        log.info("addMessage msg={}", msg);
        Message message = messageService.save(msg);

        return new ResponseEntity<>(message, OK);
    }
}

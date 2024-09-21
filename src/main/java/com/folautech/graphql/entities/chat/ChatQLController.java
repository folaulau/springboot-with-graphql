package com.folautech.graphql.entities.chat;

import com.folautech.graphql.dto.ChatDTO;
import com.folautech.graphql.entities.message.Message;
import com.folautech.graphql.entities.message.MessageCreateDTO;
import com.folautech.graphql.entities.message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Controller
public class ChatQLController {

    @Autowired
    ChatService chatService;

    @Autowired
    private MessageService messageService;

    @QueryMapping
    public ChatDTO getByIdff() {
        log.info("getById id={}");
        ChatDTO chat = chatService.getById(1L);

        return chat;
    }

    @QueryMapping
    public Chat getById(String id) {
        return Chat.builder()
                .id(Long.valueOf(id))
                .title("chat-"+id)
                .build();
    }

    @SchemaMapping(typeName = "Chat", field = "id")
    public Long resolveId(Chat chat) {
        return chat.getId();
    }

}

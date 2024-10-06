package com.folautech.graphql.entities.chat;

import com.folautech.graphql.dto.ChatDTO;
import com.folautech.graphql.entities.message.Message;
import com.folautech.graphql.entities.message.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChatServiceImp implements ChatService{

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public ChatDTO getById(Long id) {
        Chat chat = chatRepository.findById(id).orElse(new Chat());

        System.out.println(chat);

        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setId(chat.getId());
        chatDTO.setTitle(chat.getTitle());
        chatDTO.setUuid(chat.getUuid());
//        chatDTO.setMessages(chat.getMessages());

        PageRequest pageRequest = PageRequest.of(0, 10, org.springframework.data.domain.Sort.by("id").descending());

        List<Message> messages = messageRepository.findByChatId(id, pageRequest).getContent();

        chatDTO.setMessages(messages);

        return chatDTO;
    }

}

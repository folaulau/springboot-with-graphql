package com.folautech.graphql.dataloader;

import com.folautech.graphql.entities.chat.Chat;
import com.folautech.graphql.entities.chat.ChatRepository;
import com.folautech.graphql.entities.message.Message;
import com.folautech.graphql.entities.message.MessageRepository;
import com.folautech.graphql.entities.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Random;

@DependsOn(value = {"userDataLoaderService"})
@Slf4j
@Component
public class ChatDataLoaderService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    Random random = new Random();

    public void load(){
        loadChats();

        // load messages
        for (int i=1;i<=20;i++){
            Message message = new Message();
            message.setMessage("say this asdf"+ random.nextInt(1000));

            message.setUser(User.builder()
                    .id(Long.valueOf(""+random.nextInt(1,3)))
                    .build());

            message.setChat(Chat.builder()
                    .id(Long.valueOf(""+random.nextInt(1,3)))
                    .build());
            messageRepository.saveAndFlush(message);
        }

        log.info("chat messages loaded!!!");
    }

    private void loadChats(){

        for (int i=1;i<=3;i++){
            Chat chat = Chat.builder()
                    .id(Long.valueOf(""+i))
                    .title("chat-"+i)
                    .build();
            chatRepository.saveAndFlush(chat);
        }
    }

}

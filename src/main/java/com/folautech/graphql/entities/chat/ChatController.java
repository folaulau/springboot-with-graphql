package com.folautech.graphql.entities.chat;

import com.folautech.graphql.entities.message.Message;
import com.folautech.graphql.entities.message.MessageRepository;
import com.folautech.graphql.entities.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Slf4j
@Controller
public class ChatController {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageRepository messageRepository;

    Random random = new Random();

    @QueryMapping
    public List<Chat> getAllChats() {
        log.info("getAllChats");
        return chatRepository.findAll();
    }

    @QueryMapping
    public List<Message> getMessagesByChatId(@Argument Long id) {
        log.info("getMessagesByChatId, id={}",id);
        PageRequest pageRequest = PageRequest.of(0, 10, org.springframework.data.domain.Sort.by("id").descending());
        return messageRepository.findByChatId(id,pageRequest).getContent();
    }

    @SubscriptionMapping("streamMessagesForChat")
    public Flux<List<Message>> streamMessagesForChat(@Argument Long id) {
        log.info("streamMessagesForChat, id={}",id);
//        return Flux.fromStream(Stream.generate(
//                new Supplier<Message>() {
//                    @Override
//                    public Message get() {
//                        return new Message("heyhey",new User(1L));
//                    }
//                }
//        )).delayElements(Duration.ofSeconds(1))
//                .take(10);

//        return Flux.fromStream(
//                Stream.generate(() -> {
//
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    Message message = new Message();
//                    message.setId(1L); // Ensure 'id' is set properly. Replace with a proper ID if necessary.
//                    message.setUuid("some-uuid");
//                    message.setMessage("heyhey");
//                    message.setUser(new User(1L));
//                    return message;
//                }));

        return generateMessageStream(id);
    }

    private Flux<List<Message>> generateMessageStream(Long chatId) {
        return Flux.interval(Duration.ofSeconds(10))
                .map(tick -> {
                    PageRequest pageRequest = PageRequest.of(0, 10, org.springframework.data.domain.Sort.by("id").descending());
                    return messageRepository.findByChatId(chatId,pageRequest).getContent();
                });
    }


}
